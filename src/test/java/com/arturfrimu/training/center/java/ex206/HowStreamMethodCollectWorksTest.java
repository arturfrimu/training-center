package com.arturfrimu.training.center.java.ex206;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BinaryOperator;
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

    @Test
    void testCollectorsToUnmodifiableMap() {
        List<String> strings = List.of("apple", "pear", "quince", "plum");

        Map<String, Integer> result = strings
                .stream()
                .collect(Collectors.toUnmodifiableMap(
                        str -> str,               // keyMapper
                        String::length        // valueMapper
                ));

        assertThrows(UnsupportedOperationException.class, () -> result.put("banana", 6)); // Unmodifiable map

        assertThat(result).isEqualTo(
                Map.of(
                        "apple", 5,
                        "pear", 4,
                        "quince", 6,
                        "plum", 4
                )
        );
    }

    @Test
    void testCollectorsToUnmodifiableMapWithComparator() {
        List<String> strings = List.of("apple", "apple", "pear", "quince", "plum", "plum");

        Map<String, Integer> result = strings
                .stream()
                .collect(Collectors.toUnmodifiableMap(
                        str -> str,               // keyMapper
                        String::length,       // valueMapper
                        BinaryOperator.minBy((a, b) -> a)
                ));

        assertThrows(UnsupportedOperationException.class, () -> result.put("banana", 6)); // Unmodifiable map

        assertThat(result).isEqualTo(
                Map.of(
                        "apple", 5,
                        "pear", 4,
                        "quince", 6,
                        "plum", 4
                )
        );
    }

    @Test
    void testCollectorsGroupingBy() {
        List<String> strings = List.of("apple", "apple", "pear", "quince", "plum", "plum");

        Map<Integer, List<String>> result = strings
                .stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toList()));

        result.put(10, List.of("strawberry"));

        assertThat(result.getClass()).isEqualTo(HashMap.class);

        assertThat(result).isEqualTo(
                Map.of(
                        5, List.of("apple", "apple"),
                        4, List.of("pear", "plum", "plum"),
                        6, List.of("quince"),
                        10, List.of("strawberry")
                )
        );
    }

    @Test
    void testCollectorsGroupingToUnmodifiableMap() {
        List<String> strings = List.of("apple", "apple", "pear", "quince", "plum", "plum");

        Map<Integer, List<String>> result = strings
                .stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(String::length, Collectors.toList()),
                        Collections::unmodifiableMap)
                );

        assertThrows(UnsupportedOperationException.class, () -> result.put(10, List.of("strawberry"))); // Unmodifiable map

        assertThat(result).isEqualTo(
                Map.of(
                        5, List.of("apple", "apple"),
                        4, List.of("pear", "plum", "plum"),
                        6, List.of("quince")
                )
        );
    }

    @Test
    void testCollectorsPartitioningBy() {
        List<String> strings = List.of("apple", "apple", "pear", "quince", "plum", "plum");

        Map<Boolean, List<String>> result = strings
                .stream()
                .collect(Collectors.partitioningBy(string -> string.length() > 4));

        assertThrows(UnsupportedOperationException.class, () -> result.put(true, List.of("strawberry"))); // Unmodifiable map

        assertThat(result.get(true)).isEqualTo(List.of("apple", "apple", "quince"));
        assertThat(result.get(false)).isEqualTo(List.of("pear", "plum", "plum"));
    }

    @Test
    void testCollectorsPartitioningToUnmodifiableMap() {
        List<String> strings = List.of("apple", "apple", "pear", "quince", "plum", "plum");

        Map<Boolean, List<String>> result = strings
                .stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.partitioningBy(s -> s.length() > 4, Collectors.toList()),
                        Collections::unmodifiableMap)
                );

        assertThrows(UnsupportedOperationException.class, () -> result.put(true, List.of("strawberry"))); // Unmodifiable map

        assertThat(result.get(true)).isEqualTo(List.of("apple", "apple", "quince"));
        assertThat(result.get(false)).isEqualTo(List.of("pear", "plum", "plum"));
    }

    @Test
    void testCollectorsJoining() {
        List<String> strings = List.of("one", "two", "three");

        String result = strings
                .stream()
                .collect(Collectors.joining(","));

        assertThat(result).isEqualTo("one,two,three");
    }

    @Test
    void testCollectorsCounting() {
        List<String> strings = List.of("one", "two", "three");

        long count = strings.stream().collect(Collectors.counting());

        assertThat(count).isEqualTo(3);
    }

    @Test
    void testCollectorsCount() {
        List<String> strings = List.of("one", "two", "three");

        long count = strings.stream().count();

        assertThat(count).isEqualTo(3);
    }
}