package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkingWithStreams {

    @Test
    void streams() {
        List<String> names = List.of("Amigoscode", "Alex", "Zara");
        Stream<String> stream = names.stream();

        Stream<String> namesStream = Stream.of("Amigoscode", "Alex", "Zara");

        // Example 1
        /*List<Object> collect = stream.limit(2)
                .map(null)
                .sorted(null)
                .dropWhile(null)
                .collect(Collectors.toList());*/

        // Example 2
        /*long count = stream.limit(2)
                .map(null)
                .sorted(null)
                .dropWhile(null)
                .count();*/

        /*String[] namesArray = {};
        // cannot do namesArray.stream()
        Arrays.stream(namesArray);*/
    }
}
