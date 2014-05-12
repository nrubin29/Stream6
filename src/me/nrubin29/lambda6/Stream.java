package me.nrubin29.lambda6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Stream<T extends Comparable<? super T>> {
	
	private List<T> collection;

	protected Stream(List<T> collection) {
		this.collection = new ArrayList<T>();
		this.collection.addAll(collection);
	}

    /**
     * Filter the Stream based on the supplied Predicate.
     * @param predicate The Predicate with which the Stream should be filtered.
     * @return The same Stream with the values filtered.
     */
	public Stream<T> filter(Predicate<? super T> predicate) {
		Collection<T> remove = new ArrayList<T>();
		
		for (T t : collection) {
			if (!predicate.test(t)) remove.add(t);
		}
		
		collection.removeAll(remove);
		
		return this;
	}

    /**
     * Map the values of the Stream to a new Stream with the supplied Function.
     * @param function The Function with which the Stream should be mapped.
     * @param <R> The type of the new Stream.
     * @return A new Stream with the mapped values of the current Stream.
     */
	public <R extends Comparable<? super R>> Stream<R> map(Function<? super T, ? extends R> function) {
		List<R> results = new ArrayList<R>();
		
		for (T t : collection) {
			results.add(function.apply(t));
		}
		
		return StreamSupport.stream(results);
	}

    /**
     * Consume each value in the Stream.
     * @param consumer The consumer with which the Stream should be consumed.
     */
	public void forEach(Consumer<? super T> consumer) {
		for (T t : collection) {
			consumer.accept(t);
		}
	}

    /**
     * Return the minimum value in the Stream.
     * @param comparator The comparator with which the values of the Stream should be compared.
     * @return The smallest value of the Stream or {@link Optional<T>.empty()} if a value could not be found.
     */
	public Optional<T> min(Comparator<? super T> comparator) {
		T min = null;
		
		for (T t : collection) {
			if (comparator.compare(min, t) >= 1) min = t;
		}
		
		return min == null ? Optional.<T>empty() : new Optional<T>(min);
	}

    /**
     * Return the maximum value in the Stream.
     * @param comparator The comparator with which the values of the Stream should be compared.
     * @return The largest value of the Stream or {@link Optional<T>.empty()} if a value could not be found.
     */
	public Optional<T> max(Comparator<? super T> comparator) {
		T max = null;
		
		for (T t : collection) {
			if (comparator.compare(max, t) <= -1) max = t;
		}
		
		return max == null ? Optional.<T>empty() : new Optional<T>(max);
	}

    /**
     * Sorts the Stream.
     * @return The same Stream with the values sorted.
     */
	public Stream<T> sort() {
		Collections.sort(collection);
		
		return this;
	}

    /**
     * Return the collection of values used by the Stream.
     * @return The collection of values used by the Stream.
     */
	public Collection<T> getCollection() {
		return collection;
	}
}