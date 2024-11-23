package com.amigoscode.examples;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TransformationsWithFlatMap {

    private static final List<List<String>> arrayListOfNames = List.of(
            List.of("Mariam", "Alex", "Ismail"),
            List.of("John", "Alesha", "Andre"),
            List.of("Susy", "Ali")
    );

    @BeforeEach
    void setUp() {
        System.out.println(arrayListOfNames);
    }

    @Test
    public void withoutFlatMap() throws Exception {
        // [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]
        List<String> names = new ArrayList<>();
        for (int i = 0; i < arrayListOfNames.size(); i++) {
            for (int j = 0; j < arrayListOfNames.get(i)
                    .size(); j++) {
                names.add(arrayListOfNames.get(i)
                        .get(j));
            }
        }
        System.out.println(names);

        List<String> names2 = new ArrayList<>();
        for (List<String> str : arrayListOfNames) {
            names2.addAll(str);
        }
        System.out.println(names2);

        List<String> names3 = new ArrayList<>();
        //arrayListOfNames.forEach(names::addAll);
        arrayListOfNames.forEach(strings -> {
            names3.addAll(strings);
        });
        System.out.println(names3);

    }

    @Test
    public void withFlatMap() throws Exception {
        // [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]
        Function<List<String>, Stream<? extends String>> stream = List::stream;
        List<String> names = arrayListOfNames.stream()
                .flatMap(stream)
                .collect(Collectors.toList());
        System.out.println(names);
    }

    @Test
    public void flatMapWithOptionals() {
        List<Optional<String>> optionals = List.of(
                Optional.of("Amigos"),
                Optional.of("Code")
        );
        System.out.println(optionals);
        List<String> list = optionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println(list);
    }
}

