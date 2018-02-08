/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (e.g. gburdell3)
 * @GTID YOUR GT ID HERE (e.g. 900000000)
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The stack is empty");
        }
        T data = head.getData();
        size--;
        if (size == 0) {
            head = null;
        } else {
            LinkedNode<T> newHead = head.getNext();
            head.setNext(null);
            head = newHead;
        }
        return data;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (size == 0) {
            head = new LinkedNode<>(data);
        } else {
            LinkedNode<T> newHead = new LinkedNode<>(data);
            newHead.setNext(head);
            head = newHead;
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
     * Returns the head of this stack.
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
}