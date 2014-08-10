package me.nrubin29.stream6;

/**
 * Interface used for receiving a value and returning a value based on the input.
 * @param <T> The type of the Object to receive.
 * @param <R> The type of the Object to return.
 */
@FunctionalInterface
public interface Function<T, R> {

    /**
     * Method used for receiving a value and returning a value based on the input.
     * @param t The Object to receive.
     * @return The value based on the input.
     */
	public R apply(T t);
}