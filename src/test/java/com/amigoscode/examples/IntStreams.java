package com.amigoscode.examples;


import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.ObjectStreamClass;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreams {

    @Test
    public void range() throws Exception {
        System.out.println("with for i");
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }
        System.out.println("with int stream exclusive");
        IntStream.range(0, 10)
                .forEach(System.out::println);

        System.out.println("with int stream inclusive");
        IntStream intStream = IntStream.rangeClosed(0, 10);
        intStream.forEach(System.out::println);
    }

    // Loop through people using IntStream
    @Test
    public void rangeIteratingLists() throws Exception {
        List<Person> people = MockData.getPeople();
        // index of a list starts from 0 so we use range()
        IntStream.range(0, people.size())
                .forEach(idx -> {
                    System.out.println(people.get(idx));
                });
    }

    @Test
    public void intStreamIterate() {
        // this will run FOREVER
//        IntStream.iterate(0, val -> val + 1)
//                .forEach(System.out::println);
        // this will only print 10 values
        IntStream.iterate(0, val -> val + 1)
                .limit(11)
                .forEach(System.out::println);
    }
}
