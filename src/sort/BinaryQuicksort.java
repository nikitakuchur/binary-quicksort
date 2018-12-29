package sort;

public class BinaryQuicksort {

    /**
     * Sorts the given array.
     *
     * @param array the array
     * @param bSize the number of bits
     * @param digitizer the digitizer that will be used in this sort
     * @param <T> the type of elements in the array
     */
    public static <T> void sort(T[] array, int bSize, Digitizer<T> digitizer) {
        sort(array, bSize, 0, array.length - 1, 0, digitizer);
    }

    private static <T> void sort(T[] array, int bSize, int left, int right, int d, Digitizer<T> digitizer) {
        if (right <= left || d > bSize)
            return;

        int i = left, j = right;
        while (i != j) {
            while (digitizer.getDigit(array[i], d) == 0 && (i < j))
                i++;
            while (digitizer.getDigit(array[j], d) == 1 && (i < j))
                j--;
            swap(array, i, j);
        }

        if (digitizer.getDigit(array[right], d) == 0)
            j++;

        sort(array, bSize, left, j - 1, d + 1, digitizer);
        sort(array, bSize, j, right, d + 1, digitizer);
    }

    /**
     * Swaps the elements at the specified positions in the array.
     *
     * @param array the array in which to swap elements
     * @param i the index of one element to be swapped
     * @param j the index of the other element to be swapped
     */
    private static void swap(Object[] array, int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
