package me.nrubin29.stream6;

/**
 * Interface used for consuming objects.
 * @param <E> The type of the object to consume.
 */
public interface Consumer<E> {

    /**
     * Method used for consuming objects.
     * @param e The object to consume.
     */
	public void accept(E e);
}