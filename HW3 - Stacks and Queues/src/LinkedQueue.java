/**
 * Your implementation of a linked queue. It should NOT be circular.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (e.g. gburdell3)
 * @GTID YOUR GT ID HERE (e.g. 900000000)
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The queue is empty!");
        }
        T data = head.getData();
        size--;
        if (size == 0) {
            head = null;
            tail = null;
        } else {
            LinkedNode<T> newHead = head.getNext();
            head.setNext(null);
            head = newHead;
        }
        return data;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is empty");
        }
        if (size == 0) {
            head = new LinkedNode<>(data);
            tail = head;
        } else {
            LinkedNode<T> newTail = new LinkedNode<>(data);
            tail.setNext(newTail);
            tail = newTail;
        }
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return head.getData();
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
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }
}