package me.nrubin29.stream6;

/**
 * Interface used for comparing two objects.
 * @param <T> The type of the objects to compare.
 */
public interface Comparator<T> {

    /**
     * Method used for comparing two objects.
     * @param o1 The first object to compare.
     * @param o2 The second object to compare.
     * @return A negative number, zero, or a positive number if the first object is smaller, equal to, or greater than the second object.
     */
	public int compare(T o1, T o2);
}