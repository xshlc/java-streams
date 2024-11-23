package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.beans.PersonDTO;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class TransformationsMapAndReduce {

    @Test
    void yourFirstTransformationWithMap() throws IOException {
        List<Person> people = MockData.getPeople();
        // transform this list into a list of a different data type
        // change Person type to PersonDTO
        people.stream()
                .map(person -> {
                    return new PersonDTO(person.getId(), person.getFirstName(), person.getAge());
                });
        // refactored version
        Function<Person, PersonDTO> personPersonDTOFunction = person ->
                new PersonDTO(
                        person.getId(),
                        person.getFirstName(),
                        person.getAge());
        List<PersonDTO> peopleDTO = people.stream()
                .map(personPersonDTOFunction)
                .collect(Collectors.toList());

        //org.assertj.core.api.Assertions.assertThat(peopleDTO);
        assertThat(people.size()).isEqualTo(peopleDTO.size());

        /// ///

        // inside PersonDTO class:
        /*
        public static PersonDTO map(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getFirstName(),
                person.getAge());
        }
         */
        List<PersonDTO> dto = people.stream()
                .filter(person -> person.getAge() > 20)
                .map(PersonDTO::map) // we can use map() inside PersonDTO class
                .collect(Collectors.toList());

        List<PersonDTO> peopleDTO2 = people.stream()
                .filter(person -> person.getAge() > 20)
                .map(person -> {
                    PersonDTO personDTO = new PersonDTO(person.getId(), person.getFirstName(), person.getAge());
                    return personDTO;
                })
                .collect(Collectors.toList());
        System.out.println("eeee");

        // this lambda below can be replaced with a method reference
//        peopleDTO.forEach(person -> {
//            System.out.println(person);
//        });
        dto.forEach(System.out::println); // here is the method reference
    }

    @Test
    void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    @Test
    public void reduce() {
        int[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
    }
}

