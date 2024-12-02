package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        Comparator<Person> comparing = Comparator.comparing(Person::getLastName)
                .reversed();
        List<Person> sorted = people.stream()
                .sorted(comparing)
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }

    @Test
    public void topTenMostExpensiveBlueCars() throws IOException {
        List<Car> cars = MockData.getCars();
    }

}
