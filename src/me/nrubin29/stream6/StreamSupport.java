package me.nrubin29.stream6;

import java.util.List;

/**
 * Class used for streaming objects.
 */
public class StreamSupport {

    /**
     * Method used for streaming Lists.
     * @param collection The collection to stream.
     * @param <E> The type of object contained in the list.
     * @return A stream with the values of the collection.
     */
	public static <E extends Comparable<? super E>> Stream<E> stream(List<E> collection) {
		return new Stream<E>(collection);
	}
}