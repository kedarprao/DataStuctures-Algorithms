import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class LinkedListAdditionalTests {
    private LinkedListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    // Tests that the list is circular with one element in list
    @Test(timeout = TIMEOUT)
    public void testCircularityOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals(1, list.size());
        assertNotNull(list.getHead());
        assertEquals(list.getHead(), list.getHead().getNext());
    }

    // Tests that the list is circular in more general case
    @Test(timeout = TIMEOUT)
    public void testCircularityGeneral() {
        for (int i = 0; i < 10; i++) {
            list.addAtIndex(i + "a", i);
        }
        assertEquals(10, list.size());
        LinkedListNode<String> head = list.getHead();
        LinkedListNode<String> nextAfterLast = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            nextAfterLast = nextAfterLast.getNext();
        }
        assertNotNull(nextAfterLast);
        assertEquals(head, nextAfterLast);
    }

    // Tests adding at specific index out of order
    @Test(timeout = TIMEOUT)
    public void testAddStringsIndexNotOrder() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("3a");

        assertEquals(3, list.size());
        list.addAtIndex("2a", 2);
        assertEquals(4, list.size());
        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < 4; i++) {
            assertNotNull(current);
            assertEquals(i + "a", current.getData());
            current = current.getNext();
        }
    }

    // Tests exception when index is negative
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIndex() {
        assertEquals(0, list.size());
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", -1);
    }

    // Tests exception when index is above size
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddTooHighIndex() {
        assertEquals(0, list.size());
        list.addAtIndex("0a", 0);
        list.addAtIndex("2a", 2);
    }

    // Tests adding strings to back
    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            assertNotNull(current);
            assertEquals(i + "a", current.getData());
            current = current.getNext();
        }

        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    // Tests get with no elements in list
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetEmptyList() {
        assertEquals(0, list.size());
        list.get(0);
    }

    // Tests get with one element
    @Test(timeout = TIMEOUT)
    public void testGetOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals("0a", list.get(0));
    }

    // Tests remove with no elements
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveNoElements() {
        assertEquals(0, list.size());
        list.removeAtIndex(0);
    }

    // Tests remove from back with no elements
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackNoElements() {
        assertEquals(0, list.size());
        assertNull(list.removeFromBack());
    }

    // Tests remove from front with no elements
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontNoElements() {
        assertEquals(0, list.size());
        assertNull(list.removeFromFront());
    }

    // Tests remove with one element
    @Test(timeout = TIMEOUT)
    public void testRemoveOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals(1, list.size());
        assertEquals("0a", list.removeAtIndex(0));
        assertEquals(null, list.getHead());
        assertEquals(0, list.size());
    }

    // Tests remove from back with one element
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals(1, list.size());
        assertEquals("0a", list.removeFromBack());
        assertEquals(null, list.getHead());
        assertEquals(0, list.size());
    }

    // Tests remove from front with one element
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals(1, list.size());
        assertEquals("0a", list.removeFromFront());
        assertEquals(null, list.getHead());
        assertEquals(0, list.size());
    }

    // Tests removing from back
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        assertEquals(5, list.size());
        String removedString = list.removeFromBack();
        assertNotNull(removedString);
        assertEquals("4a", removedString);
        assertEquals(4, list.size());
        assertEquals("0a", list.getHead().getData());
    }

    // Tests removing from front
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        assertEquals(5, list.size());
        String removedString = list.removeFromFront();
        assertNotNull(removedString);
        assertEquals("0a", removedString);
        assertEquals(4, list.size());
        assertEquals("1a", list.getHead().getData());
    }

    // Tests remove last occurrence with one copy of data in list
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceOneCopy() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        assertEquals(5, list.size());

        String removedStringFront = list.removeLastOccurrence("0a");
        assertNotNull(removedStringFront);
        assertEquals("0a", removedStringFront);
        assertEquals(4, list.size());

        String removedStringMiddle = list.removeLastOccurrence("2a");
        assertNotNull(removedStringMiddle);
        assertEquals("2a", removedStringMiddle);
        assertEquals(3, list.size());

        String removedStringBack = list.removeLastOccurrence("4a");
        assertNotNull(removedStringBack);
        assertEquals("4a", removedStringBack);
        assertEquals(2, list.size());

        LinkedListNode<String> head = list.getHead();
        assertNotNull(head);
        assertEquals("1a", head.getData());
        assertNotNull(head.getNext());
        assertEquals("3a", head.getNext().getData());
    }

    // Tests remove last occurrence with duplicated data in list
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceMultipleCopies() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("4a", 5);
        list.addAtIndex("2a", 6);
        list.addAtIndex("3a", 7);
        list.addAtIndex("0a", 8);
        list.addAtIndex("1a", 9);
        assertEquals(10, list.size());

        String removedString0 = list.removeLastOccurrence("0a");
        assertNotNull(removedString0);
        assertEquals("0a", removedString0);
        assertEquals(9, list.size());

        String[] expectedList0 = new String[]{"0a", "1a", "2a", "3a", "4a",
                                              "4a", "2a", "3a", "1a"};
        LinkedListNode<String> current0 = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(expectedList0[i], current0.getData());
            current0 = current0.getNext();
        }

        String removedString4 = list.removeLastOccurrence("4a");
        assertNotNull(removedString4);
        assertEquals("4a", removedString4);
        assertEquals(8, list.size());

        String[] expectedList4 = new String[]{"0a", "1a", "2a", "3a",
                                              "4a", "2a", "3a", "1a"};
        LinkedListNode<String> current4 = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(expectedList4[i], current4.getData());
            current4 = current4.getNext();
        }

        String removedString1 = list.removeLastOccurrence("1a");
        assertNotNull(removedString1);
        assertEquals("1a", removedString1);
        assertEquals(7, list.size());

        String[] expectedList = new String[]{"0a", "1a", "2a", "3a",
                                             "4a", "2a", "3a"};
        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(expectedList[i], current.getData());
            current = current.getNext();
        }

    }
}
