import java.util.ArrayList;
/**
 * Your implementation of a max heap.
 *
 * @author Kedar Rao
 * @userid krao9
 * @GTID 902842074
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        clear();
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data to build heap cannot "
                    + "be empty");
        }
        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("The data to build heap "
                    + "cannot be empty");
            }
            backingArray[i + 1] = data.get(i);
        }
        size = data.size();
        for (int j = size / 2; j > 0; j--) {
            downHeap(j);
        }
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("The item to be added cannot "
                + "be empty");
        }
        if (backingArray.length == size + 1) {
            T[] temp = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[++size] = item;
        upHeap(size);
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The heap is empty.");
        }
        T data = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return data;
    }

    /**
     * Method to check if the parent data at index is smaller than
     * children and if it is smaller than either child, swap the values at the
     * index so that the child moves up and the parent moves down
     *
     * @param parentIndex the current parent index of the backingArray
     *
     */
    private void downHeap(int parentIndex) {
        if (parentIndex <= size / 2) {
            int leftChildIndex = parentIndex * 2;
            int rightChildIndex = parentIndex * 2 + 1;
            if (backingArray[rightChildIndex] != null
                    && backingArray[rightChildIndex]
                        .compareTo(backingArray[leftChildIndex]) > 0
                    && backingArray[rightChildIndex]
                        .compareTo(backingArray[parentIndex]) > 0) {
                T temp = backingArray[rightChildIndex];
                backingArray[rightChildIndex] = backingArray[parentIndex];
                backingArray[parentIndex] = temp;
                downHeap(rightChildIndex);
            } else if (backingArray[leftChildIndex]
                    .compareTo(backingArray[parentIndex]) > 0) {
                T temp = backingArray[leftChildIndex];
                backingArray[leftChildIndex] = backingArray[parentIndex];
                backingArray[parentIndex] = temp;
                downHeap(leftChildIndex);
            }
        }
    }

    /**
     * Method to check if the child index is larger than
     * parent and if it is larger, swap the values at the index so that the
     * parent moves down and the child moves up
     *
     * @param childIndex the current child index of the backingArray
     *
     */
    private void upHeap(int childIndex) {
        if (childIndex > 1) {
            int parentIndex = childIndex / 2;
            if (backingArray[childIndex]
                    .compareTo(backingArray[parentIndex]) > 0) {
                T temp = backingArray[childIndex];
                backingArray[childIndex] = backingArray[parentIndex];
                backingArray[parentIndex] = temp;
                upHeap(parentIndex);
            }
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

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}
