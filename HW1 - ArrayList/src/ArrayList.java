/**
 * Your implementation of an ArrayList.
 *
 * @author Kedar Rao
 * @userid krao9
 * @GTID 902842074
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Error - "
                    + "The index is outside the bounds");
        }

        if (data == null) {
            throw new IllegalArgumentException("Error - "
                    + "The provided data is null");
        }

        if (size >= backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        } else {
            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
        }
        backingArray[index] = data;
        size += 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error - "
                    + "The index is outside the bounds");
        }

        if (size == 0) {
            return null;
        }

        T data = backingArray[index];

        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }

        backingArray[size - 1] = null;
        size -= 1;
        return data;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }

        T data = backingArray[0];

        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }

        backingArray[size - 1] = null;
        size -= 1;
        return data;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }

        T data = backingArray[size - 1];
        backingArray[size - 1] = null;
        size -= 1;
        return data;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error - "
                    + "The index is outside the bounds");
        }

        return backingArray[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
