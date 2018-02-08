import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Sample JUnit tests for Homework 1.
 * @version 1
 */
public class ArrayListTests {

    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testExceptions() {
        // Testing addAtIndex
        Exception indexException = null;
        try {
            list.addAtIndex(1, "1a"); //0a 1a
        } catch(IndexOutOfBoundsException e) {
            indexException = e;
        }
        assertNotNull(indexException);
        assertSame(IndexOutOfBoundsException.class, indexException.getClass());

        indexException = null;
        try {
            list.addAtIndex(-1, "1a"); //0a 1a
        } catch(IndexOutOfBoundsException e) {
            indexException = e;
        }
        assertNotNull(indexException);
        assertSame(IndexOutOfBoundsException.class, indexException.getClass());

        Exception illegalArgException = null;
        try {
            list.addAtIndex(0, null); //0a 1a
        } catch(IllegalArgumentException e) {
            illegalArgException = e;
        }
        assertNotNull(indexException);
        assertSame(IllegalArgumentException.class, illegalArgException.getClass());

        // Testing addToFront

        illegalArgException = null;
        try {
            list.addToFront(null); //0a 1a
        } catch(IllegalArgumentException e) {
            illegalArgException = e;
        }
        assertNotNull(indexException);
        assertSame(IllegalArgumentException.class, illegalArgException.getClass());

        // Testing addToBack

        illegalArgException = null;
        try {
            list.addToBack(null); //0a 1a
        } catch(IllegalArgumentException e) {
            illegalArgException = e;
        }
        assertNotNull(indexException);
        assertSame(IllegalArgumentException.class, illegalArgException.getClass());

        // Testing removeAtIndex

        indexException = null;
        try {
            list.removeAtIndex(1); //0a 1a
        } catch(IndexOutOfBoundsException e) {
            indexException = e;
        }
        assertNotNull(indexException);
        assertSame(IndexOutOfBoundsException.class, indexException.getClass());

        indexException = null;
        try {
            list.removeAtIndex(-1); //0a 1a
        } catch(IndexOutOfBoundsException e) {
            indexException = e;
        }
        assertNotNull(indexException);
        assertSame(IndexOutOfBoundsException.class, indexException.getClass());

        // Testing get

        indexException = null;
        try {
            list.get(1); //0a 1a
        } catch(IndexOutOfBoundsException e) {
            indexException = e;
        }
        assertNotNull(indexException);
        assertSame(IndexOutOfBoundsException.class, indexException.getClass());

        indexException = null;
        try {
            list.get(-1); //0a 1a
        } catch(IndexOutOfBoundsException e) {
            indexException = e;
        }
        assertNotNull(indexException);
        assertSame(IndexOutOfBoundsException.class, indexException.getClass());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a
        list.addAtIndex(1, "4a"); //0a 1a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "4a";
        expected[2] = "1a";
        expected[3] = "2a";
        expected[4] = "3a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a"); //4a 3a 2a 1a 0a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "4a";
        expected[1] = "3a";
        expected[2] = "2a";
        expected[3] = "1a";
        expected[4] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a"); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }
}
