/**
 * Your implementation of a circular singly linked list.
 *
 * @author Kedar Rao
 * @userid krao9
 * @GTID 902842074
 * @version 1.0
 */

public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Error - "
                    + "The index cannot be below zero");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Error - "
                    + "The index cannot be larger than the size");
        }
        if (data == null) {
            throw new IllegalArgumentException("Error - "
                    + "The provided data is null");
        }

        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> node = head;
            for (int i = 0; i < index; i++) {
                if (i == index - 1) {
                    LinkedListNode<T> newNode = new LinkedListNode<>(data,
                            node.getNext());
                    node.setNext(newNode);
                } else {
                    node = node.getNext();
                }
            }
            size += 1;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error - "
                    + "The provided data is null");
        }

        if (isEmpty()) {
            head = new LinkedListNode<>(data);
            head.setNext(head);
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<>(head.getData(),
                    head.getNext());
            head.setNext(newNode);
            head.setData(data);

        }
        size += 1;
    }

    @Override
    public void addToBack(T data) {
        if (isEmpty()) {
            addToFront(data);
        } else {
            addToFront(data);
            head = head.getNext();
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Error - "
                    + "The index cannot be below zero");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Error - "
                    + "The index cannot be larger than the size");
        }
        T data = null;
        if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> node = head;
            for (int i = 0; i < index; i++) {
                if (i == index - 1) {
                    data = node.getNext().getData();
                    node.setNext(node.getNext().getNext());
                } else {
                    node = node.getNext();
                }
            }
            size -= 1;
        }
        return data;
    }

    @Override
    public T removeFromFront() {
        T data = null;

        if (!isEmpty()) {
            data = head.getData();
            size -= 1;
            LinkedListNode<T> node = head;
            if (size == 0) {
                clear();
            } else {
                head.setData(node.getNext().getData());
                head.setNext(node.getNext().getNext());
            }
        }
        return data;
    }

    @Override
    public T removeFromBack() {

        if (size == 1) {
            return removeFromFront();
        } else if (head != null) {
            LinkedListNode<T> node = head;
            T data = head.getData();
            for (int i = 0; i < size - 1; i++) {
                if (i == size - 2) {
                    data = node.getNext().getData();
                    node.setNext(head);
                } else {
                    node = node.getNext();
                }
            }
            size -= 1;
            return data;
        }
        return null;
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error - "
                    + "The provided data is null");
        }
        if (isEmpty()) {
            return null;
        }
        LinkedListNode<T> curr = head;
        LinkedListNode<T> remove = null;
        LinkedListNode<T> prev = null;
        if (head.getData().equals(data)) {
            remove = head;
        }
        for (int i = 0; i < size - 1; i++) {
            if (curr.getNext().getData().equals(data)) {
                remove = curr.getNext();
                prev = curr;
            }
            curr = curr.getNext();
        }
        if (remove == null) {
            return null;
        } else if (prev == null) {
            return removeFromFront();
        } else {
            prev.setNext(remove.getNext());
            T dataRet = remove.getData();
            remove.setNext(null);
            remove.setData(null);
            size--;
            return dataRet;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Error - "
                    + "The index cannot be below zero");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Error - "
                    + "The index cannot be larger than the size");
        }

        if (index == 0) {
            return head.getData();
        } else {
            LinkedListNode<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node.getData();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        LinkedListNode<T> node = head;
        for (int i = 0; i < size; i++) {
            arr[i] = node.getData();
            node = node.getNext();
        }

        return arr;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}