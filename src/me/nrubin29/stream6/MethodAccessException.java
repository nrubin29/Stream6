package me.nrubin29.stream6;

/**
 * Exception thrown when a method cannot be referenced.
 * @see me.nrubin29.stream6.ExceptionHandler
 */
public class MethodAccessException extends Exception {

    protected MethodAccessException(Exception e) {
        super(e);
    }
}