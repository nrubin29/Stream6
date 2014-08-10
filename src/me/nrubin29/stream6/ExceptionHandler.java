package me.nrubin29.stream6;

/**
 * Interface used for handling Exceptions.
 * @param <T> The type of Exception to handle.
 */
@FunctionalInterface
public interface ExceptionHandler<T extends Exception> {

    /**
     * Method used for handling Exceptions.
     * @param t The Exception to handle.
     */
    public void handle(T t);
}