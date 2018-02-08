/**
 * Your implementation of an array-backed stack.
 *
 * @author Kedar Rao
 * @userid krao9
 * @GTID 902842074
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * You should replace any spots that you pop from with null. Failure to do
     * so can result in a loss of points.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The stack is empty");
        }
        T data = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return data;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The inputted data is null");
        }
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = backingArray[i];
            }
            temp[size] = data;
            backingArray = temp;
        } else {
            backingArray[size] = data;
        }
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return backingArray[size - 1];
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the backing array of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}