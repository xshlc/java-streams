package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sorting {

    @Test
    public void sortingSteamOfElements() throws IOException {
        List<Person> people = MockData.getPeople();
        List<String> sortedList = people.stream()
                .map(Person::getFirstName)
                .sorted()
                .collect(Collectors.toList());
//        System.out.println(sortedList);
        sortedList.forEach(System.out::println);
    }

    @Test
    public void sortingSteamOfElementsReverse() throws IOException {
        List<Person> people = MockData.getPeople();
        Comparator<String> comparator = Comparator.reverseOrder();
        List<String> sorted = people.stream()
                .map(Person::getFirstName)
                .sorted(comparator)
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }

    @Test
    public void sortingSteamOfObjets() throws IOException {
        List<Person> people = MockData.getPeople();
//        Comparator<Person> comparing = Comparator.comparing(Person::getFirstName);
        Comparator<Person> comparing = Comparator.comparing(Person::getEmail)
                .reversed()  // email will be reversed but not the first name
                .thenComparing(Person::getFirstName);
        List<Person> sorted = people.stream()
                .sorted(comparing)
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }

    @Test
    public void topTenMostExpensiveBlueCars() throws IOException {
        List<Car> cars = MockData.getCars();
        // Incorrect attempt:
//        cars.stream()
//                .map(car -> {
//                    return car.getColor()
//                            .equals("blue");
//                })
//                .sorted(Comparator.comparing(Car::getPrice))
//                .limit(10)
//                .forEach(System.out::println);
        // End of incorrect attempt

        /*
        Top ten expensive BLUE cars
         */
        List<Car> topTenBlue = cars.stream()
                .filter(car -> car.getColor()
                        .equalsIgnoreCase("blue"))
                .sorted(Comparator.comparing(Car::getPrice))
                .limit(10)
                .collect(Collectors.toList());
        topTenBlue.forEach(System.out::println);
    }

}
