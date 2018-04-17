import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Kedar Rao
 * @userid krao9
 * @GTID 902842074
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array or the comparator "
                    + "is empty!");
        }
        if (arr.length < 2) {
            return;
        }
        boolean sorted = false;
        int i = 0;
        while (i < (arr.length - 1) && !sorted) {
            sorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    sorted = false;
                }
            }
            /*
            if (!sorted) {
                sorted = true;
                for (int k = arr.length - i - 1; k >= i; k++) {
                    if (arr[k-1] > arr[k]) {
                        swap;
                        sorted = false;
                    }
                }
            }
             */
            i++;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array or the comparator "
                    + "is empty!");
        }

        if (arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(arr, j - 1, j);
                j--;
            }
        }

    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array or the comparator "
                    + "is empty!");
        }
        if (arr.length < 2) {
            return;
        }
        for (int i =  0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = minIndex + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("The array, the comparator, or "
                    + "the random object is empty!");
        }
        if (arr.length < 2) {
            return;
        }
        quickSort(0, arr.length - 1, arr, comparator, rand);

    }

    /**
     * Helper method to implement quicksort
     *
     * @param <T> data type to sort
     * @param a the left most index in the array
     * @param b the right most index in the array
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    private static <T> void quickSort(int a, int b, T[] arr,
                                      Comparator<T> comparator, Random rand) {

        if (a >= b) {
            return;
        }

        int left = a + 1;
        int right = b;
        int pivotIndex = rand.nextInt(b + 1 - a) + a;
        T pivot = arr[pivotIndex];
        // T temp = arr[pivotIndex];
        //arr[pivotIndex] = arr[a];

        swap(arr, a, pivotIndex);
        while (left <= right) {
            while (left <= right
                    && comparator.compare(arr[left], pivot) <= 0) {
                left++;
            }
            while (left <= right
                    && comparator.compare(arr[right], pivot) >= 0) {
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        swap(arr, a, right);
        quickSort(a, right - 1, arr, comparator, rand);
        quickSort(right + 1, b, arr, comparator, rand);
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array or the comparator "
                    + "is empty!");
        }
        if (arr.length < 2) {
            return;
        }
        int midIndex = arr.length / 2;
        T[] leftArray = (T[]) new Object[midIndex];
        T[] rightArray = (T[]) new Object[arr.length - midIndex];
        for (int i = 0; i < midIndex; i++) {
            leftArray[i] = arr[i];
        }
        for (int i = 0; i < arr.length - midIndex; i++) {
            rightArray[i] = arr[i + midIndex];
        }
        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);
        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;
        while (leftIndex < midIndex && rightIndex < (arr.length - midIndex)) {
            if (comparator.compare(leftArray[leftIndex],
                    rightArray[rightIndex]) <= 0) {
                arr[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                arr[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }
        while (leftIndex < midIndex) {
            arr[currentIndex] = leftArray[leftIndex];
            leftIndex++;
            currentIndex++;
        }
        while (rightIndex < arr.length - midIndex) {
            arr[currentIndex] = rightArray[rightIndex];
            rightIndex++;
            currentIndex++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("The array or the comparator "
                    + "is empty!");
        }

        if (arr.length < 2) {
            return;
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        int divisor = 1;
        boolean complete = false;
        while (!complete) {
            complete = true;
            for (int i = 0; i < arr.length; i++) {
                int digit = arr[i] / divisor;
                if (Math.abs(digit / 10) != 0) {
                    complete = false;
                }
                buckets[(digit % 10) + 9].add(arr[i]);
            }
            for (int i = 0, j = 0; i < buckets.length; i++) {
                while (!buckets[i].isEmpty()) {
                    arr[j] = buckets[i].remove();
                    j++;
                }
            }
            divisor *= 10;
        }
    }

    /**
     * Helper method to swap array elements
     *
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param index1 index of one element to swap
     * @param index2 index of other element to swap
     */
    private static <T> void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
