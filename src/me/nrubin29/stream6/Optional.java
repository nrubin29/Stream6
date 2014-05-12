package me.nrubin29.stream6;

import java.util.NoSuchElementException;

/**
 * Class that can either contain a value or null.
 * @param <T> The type of value.
 */
public class Optional<T> {

	private static final Optional<?> EMPTY = new Optional<Object>();
	
	private final T value;
	
	private Optional() {
		this.value = null;
	}

    /**
     * Method that returns an empty Optional.
     * @param <T> The type of Optional to return.
     * @return An empty Optional of type T.
     */
	public static <T> Optional<T> empty() {
		@SuppressWarnings("unchecked")
		Optional<T> t = (Optional<T>) EMPTY;
		return t;
	}

    /**
     * Constructor that creates an Optional.
     * @param value The not-null value for the Optional.
     */
	public Optional(T value) {
        if (value == null) {
            throw new NullPointerException("No value present");
        }
		this.value = value;
	}

    /**
     * Method that returns the value if it is not null.
     * @return The not-null value for the Optional.
     */
	public T get() {
		if (value == null) {
			throw new NoSuchElementException("No value present");
		}
		return value;
	}

    /**
     * Method that returns whether or not a value is present.
     * @return Whether or not a value is present.
     */
	public boolean isPresent() {
		return value != null;
	}

    /**
     * Method that consumes the value if it is present.
     * @param consumer The Consumer to consume the value if it is present.
     */
	public void ifPresent(Consumer<? super T> consumer) {
		if (value != null) {
			consumer.accept(value);
		}
	}
}