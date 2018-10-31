package sort;

public class BinaryQuicksort {

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

    private static void swap(Object[] array, int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
