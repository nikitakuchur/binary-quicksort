package sort;

public interface Digitizer<T> {

    /**
     * @param object the object
     * @param index the index
     * @return the bit by index
     */
    long getDigit(T object, int index);
}
