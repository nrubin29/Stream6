package me.nrubin29.stream6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Examples {

	public static void main(String[] args) {
		ArrayList<String> strs = new ArrayList<String>(Arrays.asList("This", "is", "a", "test", "of", "Stream6"));

        /*
        Using Stream6 without method references
         */

		StreamSupport.stream(strs)
		        .filter(new Predicate<String>() {
			        @Override
			        public boolean test(String str) {
				        return str.contains("i");
			        }
                })
		        .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String str) {
                        return str.length();
                    }
                })
		        .sort()
		        .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {
                        System.out.println(i);
                    }
                })
        ;

        boolean filterI = true, sort = false, print = true;
        StreamSupport.stream(strs)
                .filterIf(new Predicate<String>() {
                    @Override
                    public boolean test(String str) {
                        return str.contains("i");
                    }
                }, filterI)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String str) {
                        return str.length();
                    }
                })
                .sortIf(sort)
                .forEachIf(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {
                        System.out.println(i);
                    }
                }, print)
        ;

        /*
        Using Stream6 with method references
         */

        StreamSupport
                .stream(strs)
                .filter(new MethodReference<Boolean>(ExampleMethods.class, "filter"))
                .map(new MethodReference<Integer>(ExampleMethods.class, "map"))
                .sort()
                .forEach(new MethodReference<Void>(System.out, "println"))
        ;

        StreamSupport
                .stream(strs)
                .filterIf(new MethodReference<Boolean>(ExampleMethods.class, "filter"), filterI)
                .map(new MethodReference<Integer>(ExampleMethods.class, "map"))
                .sortIf(sort)
                .forEachIf(new MethodReference<Void>(System.out, "println"), print)
        ;

		/*
		Using Java 6
		 */
		
		ArrayList<String> remove = new ArrayList<String>();
		
		for (String str : strs) {
			if (!str.contains("i")) {
				remove.add(str);
			}
		}
		
		strs.removeAll(remove);
		
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		
		for (String str : strs) {
			lengths.add(str.length());
		}
		
		Collections.sort(lengths);
		
		for (int i : lengths) {
			System.out.println(i);
		}

        /* */

        if (filterI) {
            remove = new ArrayList<String>();

            for (String str : strs) {
                if (!str.contains("i")) {
                    remove.add(str);
                }
            }

            strs.removeAll(remove);
        }

        lengths = new ArrayList<Integer>();

        for (String str : strs) {
            lengths.add(str.length());
        }

        if (sort) {
            Collections.sort(lengths);
        }

        if (print) {
            for (int i : lengths) {
                System.out.println(i);
            }
        }
	}

    public static class ExampleMethods {

        public static boolean filter(String str) {
            return str.contains("i");
        }

        public static int map(String str) {
            return str.length();
        }
    }
}