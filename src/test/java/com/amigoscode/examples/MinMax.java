package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MinMax {

    @Test
    public void min() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        // this is not allowed
        // "local variables referenced from a lambda must be final or effectively final
//        int minNum = Integer.MAX_VALUE;
//        numbers.forEach(integer -> {
//            if (integer < minNum) {
//                minNum = integer;
//            }
//        });
//        System.out.println(minNum);
        //     AtomicInteger is a class from the java.util.concurrent.atomic package that allows safe,
        //     atomic operations on an integer variable. We can modify it inside a lambda expression
        //     by using methods like get() and set().
        //    The minNum.get() method returns the current value, and minNum.set(integer) updates the value.
        //
        // This approach ensures that the variable can be safely modified within the lambda while
        // also being thread-safe (in case you extend it to parallel processing).
        AtomicInteger minNum = new AtomicInteger(Integer.MAX_VALUE);
        numbers.forEach(integer -> {
            if (integer < minNum.get()) {
                minNum.set(integer);
            }
        });
        System.out.println(minNum.get()); // print out result

        /// next solution
        int minNum2 = numbers.stream()
                .min(((o1, o2) -> o1.compareTo(o2)))
                .get();
        System.out.println("Result 1: " + minNum2);

        int minNum3 = numbers.stream()
                .min(Integer::compareTo)
                .get();
        System.out.println("Result 2: " + minNum3);
        int min = numbers.stream()
                .min(Comparator.naturalOrder())
                .get();
        System.out.println(min);
    }

    @Test
    public void max() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        int max = numbers.stream()
                .max(Comparator.naturalOrder())
                .get();
        System.out.println(max);
    }
}
