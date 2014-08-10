package me.nrubin29.stream6;

import java.util.Arrays;
import java.util.List;

/**
 * Class used for streaming objects.
 */
public class StreamSupport {

    /**
     * Method used for streaming {@link java.util.List}s.
     * @param list The list to stream.
     * @param <E> The type of object contained in the list.
     * @return A stream with the values of the list.
     */
	public static <E extends Comparable<? super E>> Stream<E> stream(List<E> list) {
		return new Stream<E>(list);
	}

    /**
     * Method used for streaming arrays.
     * @param array The array to stream.
     * @param <E> The type of object contained in the array.
     * @return A stream with the values of the array.
     */
    public static <E extends Comparable<? super E>> Stream<E> stream(E... array) {
        return new Stream<E>(Arrays.asList(array));
    }
}