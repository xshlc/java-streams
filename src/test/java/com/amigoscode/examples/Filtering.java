package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtering {

    @Test
    public void filter() throws Exception {
        List<Car> cars = MockData.getCars();
        // filter takes a predicate
        Predicate<Car> carPredicate = car -> car.getPrice() < 20_000.00;
        List<Car> carsLessThan20k = cars.stream()
                .filter(carPredicate)
                .filter(car -> car.getColor()
                        .equals("Yellow"))
                .collect(Collectors.toList());
        carsLessThan20k.forEach(System.out::println);
    }

    @Test
    public void dropWhile() throws Exception {
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        System.out.println("using dropWhile");
        // drops the elements that match the condition
        // and then returns all of the remaining
        // here, 9 doesn't match the condition so we return "9 10 12"
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .dropWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
    }

    @Test
    public void takeWhile() throws Exception {
        // using filter
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();
        System.out.println("using take while");
        // the takeWhile() function will stop after 9
        // because 9 is not divisible by 2
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .takeWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
    }

    @Test
    public void findFirst() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        OptionalInt optional = Arrays.stream(numbers)
                .filter(n -> n == 9)
                .findFirst();
        optional.ifPresent(System.out::println); // this will not print if not present
        int res = Arrays.stream(numbers)
                .filter(n -> n == 50)
                .findFirst()
                .orElse(-1);
        System.out.println(res);

    }

    @Test
    public void findAny() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10};
        // we have no control whether it will find the first 9 or second 9
        int res = Arrays.stream(numbers)
                .filter(n -> n == 9)
                .findAny()
                .orElse(-1);
        System.out.println(res);
    }

    @Test
    public void allMatch() throws Exception {
        int[] even = {2, 4, 6, 8, 10};
        // we want to make sure that all the numbers
        // in this list are even

        int[] oneOdd = {2, 4, 6, 8, 10, 11};
        boolean allMatch = Arrays.stream(oneOdd)
                .allMatch(n -> n % 2 == 0);
        System.out.println(allMatch);
    }

    @Test
    public void anyMatch() throws Exception {
        int[] evenAndOneOdd = {2, 4, 6, 8, 10, 11};

        boolean anyMatch = Arrays.stream(evenAndOneOdd)
                .anyMatch(n -> !(n % 2 == 0));
        System.out.println(anyMatch);
    }

}



