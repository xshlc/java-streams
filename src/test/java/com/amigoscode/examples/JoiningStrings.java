package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class JoiningStrings {

    @Test
    public void joiningStrings() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // "Anna, John, Marcos, Helena, Yasmin"
        //// Imperative approaches to joining strings
        // version 1
        // this version has string immutability issues
        // poor performance due to repeated creation of new string object
        String joined = "";
        for (String name : names) {
            char[] tmp = name.toCharArray();
            tmp[0] = Character.toUpperCase(tmp[0]);
            String tmp2 = new String(tmp);
            joined += tmp2 + ", ";
        }
        System.out.println(joined);
        // version 2
        // modifies the underlying character array
        // without creating new string objects
        StringBuilder join = new StringBuilder();
        for (String name : names) {
            join.append(name.substring(0, 1)
                            .toUpperCase())
                    .append(name.substring(1))
                    .append(", ");
        }
        System.out.println(join.substring(0, join.length() - 2));
    }

    @Test
    public void joiningStringsWithStream() throws Exception {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // "Anna, John, Marcos, Helena, Yasmin"
        String join = names.stream()
                .map(name -> name.substring(0, 1)
                        .toUpperCase() + name.substring(1))
                .collect(Collectors.joining(","));
        System.out.println(join);
    }

}
