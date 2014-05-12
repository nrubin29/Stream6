package me.nrubin29.stream6;

/**
 * Interface used for testing objects.
 * @param <E> The type of object to test.
 */
public interface Predicate<E> {

    /**
     * Method used for testing objects.
     * @param e The object to test.
     * @return The result of the test.
     */
	public boolean test(E e);
}