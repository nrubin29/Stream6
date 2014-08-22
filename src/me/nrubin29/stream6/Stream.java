package me.nrubin29.stream6;

import java.util.*;

public class Stream<T extends Comparable<? super T>> {
	
	private List<T> collection;
    private ExceptionHandler<MethodAccessException> exceptionHandler;

	protected Stream(List<T> collection) {
		this.collection = new ArrayList<T>();
		this.collection.addAll(collection);

        this.exceptionHandler = new ExceptionHandler<MethodAccessException>() {
            @Override
            public void handle(MethodAccessException e) {
                e.printStackTrace();
            }
        };
	}

    /**
     * Filter the Stream based on the supplied Predicate.
     * @param predicate The Predicate with which the Stream should be filtered.
     * @return The same Stream with the values filtered.
     */
	public Stream<T> filter(Predicate<? super T> predicate) {
		Collection<T> remove = new ArrayList<T>();
		
		for (T t : collection) {
			if (!predicate.test(t)) {
                remove.add(t);
            }
		}
		
		collection.removeAll(remove);
		
		return this;
	}

    /**
     * Filter the Stream based on the supplied Predicate if the condition is met.
     * @param predicate The Predicate with which the Stream should be filtered.
     * @param condition The condition which must be met in order to filter.
     * @return The same Stream with the values filtered if the condition is met, or the same Stream unchanged.
     */
    public Stream<T> filterIf(Predicate<? super T> predicate, boolean condition) {
        if (condition) {
            return filter(predicate);
        }

        else {
            return this;
        }
    }

    /**
     * Filter the Stream based on the supplied MethodReference.
     * @param reference The method with which the Stream should be filtered.
     * @return The same Stream with the values filtered.
     */
    public Stream<T> filter(MethodReference<Boolean> reference) {
        Collection<T> remove = new ArrayList<T>();

        for (T t : collection) {
            try {
                if (!reference.access(t)) {
                    remove.add(t);
                }
            } catch (MethodAccessException e) {
                if (exceptionHandler != null) exceptionHandler.handle(e);
            }
        }

        collection.removeAll(remove);

        return this;
    }

    /**
     * Filter the Stream based on the supplied MethodReference if the condition is met.
     * @param reference The method with which the Stream should be filtered.
     * @param condition The condition which must be met in order to filter.
     * @return The same Stream with the values filtered if the condition is met, or the same Stream unchanged.
     */
    public Stream<T> filterIf(MethodReference<Boolean> reference, boolean condition) {
        if (condition) {
            return filter(reference);
        }

        else {
            return this;
        }
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
     * Map the values of the Stream to a new Stream with the supplied MethodReference.
     * @param reference The method with which the Stream should be mapped.
     * @param <R> The type of the new Stream.
     * @return A new Stream with the mapped values of the current Stream.
     */
    public <R extends Comparable<? super R>> Stream<R> map(MethodReference<R> reference) {
        List<R> results = new ArrayList<R>();

        for (T t : collection) {
            try {
                results.add(reference.access(t));
            }

            catch (MethodAccessException e) {
                if (exceptionHandler != null) {
                    exceptionHandler.handle(e);
                }
            }
        }

        return StreamSupport.stream(results);
    }

    /**
     * Consume each value in the Stream.
     * @param consumer The Consumer with which the Stream should be consumed.
     */
	public void forEach(Consumer<? super T> consumer) {
		for (T t : collection) {
			consumer.accept(t);
		}
	}

    /**
     * Consume each value in the Stream if the condition is met.
     * @param consumer The Consumer with which the Stream should be consumed.
     * @param condition The condition which must be met in order to consume.
     */
    public void forEachIf(Consumer<? super T> consumer, boolean condition) {
        if (condition) {
            forEach(consumer);
        }
    }

    /**
     * Consume each value in the Stream.
     * @param reference The method with which the Stream should be consumed.
     */
    public void forEach(MethodReference<Void> reference) {
        for (T t : collection) {
            try {
                reference.access(t);
            }

            catch (MethodAccessException e) {
                if (exceptionHandler != null) {
                    exceptionHandler.handle(e);
                }
            }
        }
    }

    /**
     * Consume each value in the Stream if the condition is met.
     * @param reference The method with which the Stream should be consumed.
     * @param condition The condition which must be met in order to consume.
     */
    public void forEachIf(MethodReference<Void> reference, boolean condition) {
        if (condition) {
            forEach(reference);
        }
    }

    /**
     * Return the minimum value in the Stream.
     * @param comparator The Comparator with which the values of the Stream should be compared.
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
     * Return the minimum value in the Stream.
     * @param reference The method with which the values of the Stream should be compared.
     * @return The smallest value of the Stream or {@link Optional<T>.empty()} if a value could not be found.
     */
    public Optional<T> min(MethodReference<Integer> reference) {
        T min = null;

        for (T t : collection) {
            try {
                if (reference.access(min, t) >= 1){
                    min = t;
                }
            }

            catch (MethodAccessException e) {
                if (exceptionHandler != null) exceptionHandler.handle(e);
            }
        }

        return min == null ? Optional.<T>empty() : new Optional<T>(min);
    }

    /**
     * Return the maximum value in the Stream.
     * @param comparator The Comparator with which the values of the Stream should be compared.
     * @return The largest value of the Stream or {@link Optional<T>.empty()} if a value could not be found.
     */
	public Optional<T> max(Comparator<? super T> comparator) {
		T max = null;
		
		for (T t : collection) {
			if (comparator.compare(max, t) <= -1) {
                max = t;
            }
		}
		
		return max == null ? Optional.<T>empty() : new Optional<T>(max);
	}

    /**
     * Return the maximum value in the Stream.
     * @param reference The method with which the values of the Stream should be compared.
     * @return The largest value of the Stream or {@link Optional<T>.empty()} if a value could not be found.
     */
    public Optional<T> max(MethodReference<Integer> reference) {
        T max = null;

        for (T t : collection) {
            try {
                if (reference.access(max, t) <= -1) {
                    max = t;
                }
            }

            catch (MethodAccessException e) {
                if (exceptionHandler != null) exceptionHandler.handle(e);
            }
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
     * Sort the Stream.
     * @param condition The condition which must be met in order to sort.
     * @return The same Stream with the values sorted if the condition is met.
     */
    public Stream<T> sortIf(boolean condition) {
        if (condition) {
            Collections.sort(collection);
        }

        return this;
    }

    /**
     * Sets the ExceptionHandler for the Stream.
     * @param exceptionHandler The ExceptionHandler for the Stream.
     * @return The same Stream.
     */
    public Stream<T> withExceptionHandler(ExceptionHandler<MethodAccessException> exceptionHandler) {
        this.exceptionHandler = exceptionHandler;

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