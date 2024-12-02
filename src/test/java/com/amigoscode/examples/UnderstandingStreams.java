package com.amigoscode.examples;

import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UnderstandingStreams {

    @Test
    void collect() throws IOException {
        List<String> emails = MockData.getPeople()
                .stream()
                .map(Person::getEmail)
                .collect(Collectors.toList());
//        emails.forEach(System.out::println);
        System.out.println("============================");
        System.out.println("============================");
        ///  Same thing as using collect(Collectors.toList())
        List<String> emails1 = MockData.getPeople()
                .stream()
                .map(Person::getEmail)
                .collect(
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll
                );
        emails1.forEach(System.out::println);
        ///  Same thing as above
        List<String> emails2 = MockData.getPeople()
                .stream()
                .map(Person::getEmail)
                .collect(
                        () -> new ArrayList<String>(), // supplier
                        (list, element) -> list.add(element), // accumulator
                        (list1, list2) -> list1.addAll(list2) // combiner
                );
        // emails2.forEach(System.out::println);
        // emails2.forEach(email -> System.out.println(String.format("  %s", email)));
//        emails2.forEach(email -> System.out.println("  " + email));
    }

    @Test
    public void lazy() throws Exception {
        System.out.println(
                MockData.getCars()
                        .stream()
                        .filter(car -> {
                            System.out.println("filter car " + car);
                            return car.getPrice() < 10000;
                        })
                        .map(car -> {
                            System.out.println("mapping car " + car);
                            return car.getPrice();
                        })
                        .map(price -> {
                            System.out.println("mapping price " + price);
                            return price + (price * .14);
                        })
                        .collect(Collectors.toList())
        );
    }
}
