import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This is a basic set of unit tests for SinglyLinkedList. Passing these does
 * NOT guarantee any grade on this assignment. This is only a sanity check to
 * help you get started on the homework and writing JUnits in general.
 * Good luck and have fun on the homework!
 *
 * @author Shishir Bhat
 * @version 1.0
 */
public class SinglyLinkedListTest5U {

    private LinkedListInterface<String> list;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    /**
     * Adds alphabet from "a" to "z" into list
     * Used to test remove...() methods
     */
    private void addAlphabet() {
        char c = 'a';
        for (int i = 0; i < 26; i++) {
            list.addAtIndex(c++ + "", i);
        }
    }
    @Test(timeout = TIMEOUT)
    public void addAtIndex() {
        list.addAtIndex("a", 0);
        assertEquals(list.getHead().getNext(), list.getHead());
        //Making sure head points back to itself
        list.addAtIndex("b", 1);
        list.addAtIndex("c", 2);
        list.addAtIndex("d", 3);
        String[] ideal = {"a", "b", "c", "d"};
        assertArrayEquals(ideal, list.toArray());
        list.addAtIndex("A", 0);
        list.addAtIndex("B", 2);
        list.addAtIndex("C", 4);
        list.addAtIndex("D", 6);
        String[] ideal2 = {"A", "a", "B", "b", "C", "c", "D", "d"};
        assertArrayEquals(ideal2, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void addToBack() {
        list.addToBack("To be");
        list.addToBack("fair");
        list.addToBack("you");
        list.addToBack("have");
        list.addToBack("to");
        list.addToBack("have a");
        list.addToBack("very");
        list.addToBack("high");
        list.addToBack("IQ to");
        list.addToBack("understand");
        list.addToBack("Rick and");
        list.addToBack("Morty");
        assertEquals(12, list.size());
        String[] ideal = {"To be", "fair", "you", "have", "to", "have a",
                          "very", "high", "IQ to", "understand", "Rick and",
                          "Morty"};
        assertArrayEquals(ideal, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void removeAtIndex() {
        list.addToBack("a");
        list.removeAtIndex(0);
        assertEquals(0, list.size());
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("b");
        list.addToBack("c");
        list.removeAtIndex(2);
        list.removeAtIndex(0);
        assertArrayEquals(new Object[]{"c", "c"}, list.toArray());
        list.clear();
        addAlphabet();
        assertEquals("e", list.removeAtIndex(4));
        assertEquals("k", list.removeAtIndex(9));
        assertEquals("z", list.removeAtIndex(23));
        list.removeAtIndex(0);
        assertEquals("b", list.removeAtIndex(0));
    }

    @Test(timeout = TIMEOUT)
    public void removeFromBack() {
        list.addToBack("a");
        assertEquals("a", list.removeFromBack());
        assertEquals(0, list.size());
        assertNull(list.getHead());
        addAlphabet();
        assertEquals("z", list.removeFromBack());
        assertEquals("y", list.removeFromBack());
        assertEquals(24, list.size());
        assertEquals("a", list.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void removeLastOccurrence() {
        list.addToBack("a");
        list.removeLastOccurrence("a");
        assertEquals(0, list.size());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("a");
        list.removeLastOccurrence("a");
        assertArrayEquals(new Object[]{"a", "b"}, list.toArray());
        list.clear();
        assertNull(list.removeLastOccurrence("a"));
        list.addToBack("a");
        //Checks if list uses "==" or ".equals()"
        assertEquals("a", list.removeLastOccurrence("A".toLowerCase()));
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.removeLastOccurrence("a");
        assertArrayEquals(new Object[]{"b", "c"}, list.toArray());
        assertEquals(2, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void get() {
        list.addToBack("To be");
        list.addToBack("fair");
        list.addToBack("you");
        list.addToBack("have");
        list.addToBack("to");
        list.addToBack("have a");
        list.addToBack("very");
        list.addToBack("high");
        list.addToBack("IQ to");
        list.addToBack("understand");
        list.addToBack("Rick and");
        list.addToBack("Morty");
        String[] ideal = {"To be", "fair", "you", "have", "to", "have a",
                          "very", "high", "IQ to", "understand", "Rick and",
                          "Morty"};
        Object[] originalList = list.toArray();
        int originalSize = list.size();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(ideal[i], list.get(i));
        }
        Object[] afterList = list.toArray();
        int afterSize = list.size();
        //making sure get() doesn't modify list
        assertEquals(originalSize, afterSize);
        assertArrayEquals(originalList, afterList);
    }

    @Test(timeout = TIMEOUT)
    public void isEmpty() {
        assertTrue(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        list.addToBack("a");
        assertFalse(list.isEmpty());
        list.removeFromFront();
        assertTrue(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void clear() {
        list.clear();
        assertEquals(0, list.size());
        assertArrayEquals(new Object[]{}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void size() {
        assertEquals(0, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.addToFront("2");
        list.addToFront("1");
        assertEquals(2, list.size());
        list.addAtIndex("3", 1);
        assertEquals(3, list.size());
        list.addToBack("4");
        assertEquals(4, list.size());
        list.removeAtIndex(2);
        assertEquals(3, list.size());
        list.removeFromFront();
        assertEquals(2, list.size());
        list.removeFromBack();
        assertEquals(1, list.size());
        list.clear();
        assertEquals(0, list.size());
    }
}