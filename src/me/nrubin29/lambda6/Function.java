package me.nrubin29.lambda6;

/**
 * Interface used for receiving a value and returning a value based on the input.
 * @param <T> The type of the object to receive.
 * @param <R> The type of the object to return.
 */
public interface Function<T, R> {

    /**
     * Method used for receiving a value and returning a value based on the input.
     * @param t The object to receive.
     * @return The value based on the input.
     */
	public R apply(T t);
}