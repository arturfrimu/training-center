package com.arturfrimu.training.center.java.ex206;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class HowStreamMethodCollectWorksTest {

    @Test
    void testCollectorsToList() {
        List<String> strings = List.of("one", "two", "three");

        List<String> result = strings
                .stream()
                .collect(Collectors.toList());

        result.add("four"); //Modifiable list

        assertThat(result.getClass()).isEqualTo(ArrayList.class);

        assertThat(result).isEqualTo(List.of("one", "two", "three", "four"));
    }

    @Test
    void testCollectorsToUnmodifiableList() {
        List<String> strings = List.of("one", "two", "three");

        List<String> result = strings
                .stream()
                .collect(Collectors.toUnmodifiableList()); // equivalent with .toList();

        assertThrows(UnsupportedOperationException.class, () -> result.add("four")); // Unmodifiable List

        assertThat(result).isEqualTo(List.of("one", "two", "three"));
    }

    @Test
    void testToList() {
        List<String> strings = List.of("one", "two", "three");

        List<String> result = strings
                .stream()
                .toList(); // equivalent with .collect(Collectors.toUnmodifiableList());

        assertThrows(UnsupportedOperationException.class, () -> result.add("four")); // Unmodifiable List

        assertThat(result).isEqualTo(List.of("one", "two", "three"));
    }

    @Test
    void testToSet() {
        List<String> strings = List.of("one", "two", "three");

        Set<String> result = strings
                .stream()
                .collect(Collectors.toSet());

        result.add("four"); // Modifiable Set

        assertThat(result.getClass()).isEqualTo(HashSet.class);

        assertThat(result).isEqualTo(Set.of("one", "two", "three", "four"));
    }

    @Test
    void testToUnmodifiableSet() {
        List<String> strings = List.of("one", "two", "three");

        Set<String> result = strings
                .stream()
                .collect(Collectors.toUnmodifiableSet());

        assertThrows(UnsupportedOperationException.class, () -> result.add("four")); // Unmodifiable Set

        assertThat(result).isEqualTo(Set.of("one", "two", "three"));
    }

    @Test
    void testCollectorsToMap() {
        List<String> strings = List.of("apple", "pear", "quince", "plum");

        Map<String, Integer> result = strings
                .stream()
                .collect(Collectors.toMap(
                        str -> str,               // keyMapper
                        String::length        // valueMapper
                ));

        assertThat(result.getClass()).isEqualTo(HashMap.class);

        result.put("banana", 6); // Modifiable map

        assertThat(result).isEqualTo(
                Map.of(
                        "banana", 6,
                        "apple", 5,
                        "pear", 4,
                        "quince", 6,
                        "plum", 4
                )
        );
    }
}