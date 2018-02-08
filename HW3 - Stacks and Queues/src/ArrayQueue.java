/**
 * Your implementation of an array-backed queue.
 *
 * @author Keda Rao
 * @userid krao9
 * @GTID 902842074
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        size = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you should
     * explicitly reset front to 0.
     *
     * You should replace any spots that you dequeue from with null. Failure to
     * do so can result in a loss of points.
     *
     * See the homework pdf for more information on implementation details.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The queue is empty!");
        }
        T data = backingArray[front];
        backingArray[front] = null;
        size--;
        if (size == 0) {
            front = 0;
        } else {
            front = (front + 1) % backingArray.length;
        }
        return data;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null");
        }

        if (backingArray.length == size) {
            T[] temp = (T[]) new Object[size * 2];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[(front + i) % backingArray.length];
            }
            temp[size] = data;
            backingArray = temp;
            front = 0;
        } else {
            backingArray[(front + size) % backingArray.length] = data;
        }
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        } else {
            return backingArray[front];
        }
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
     * Returns the backing array of this queue.
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