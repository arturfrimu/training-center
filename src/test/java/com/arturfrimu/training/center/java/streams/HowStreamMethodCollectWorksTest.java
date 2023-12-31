package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    void testCollectorsSummarizingInt() {
        List<Integer> numbers = List.of(1, 2, 3);

        IntSummaryStatistics result = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue));

        assertThat(result).satisfies(stats -> {
            assertThat(stats.getSum()).isEqualTo(6);
            assertThat(stats.getCount()).isEqualTo(3);
            assertThat(stats.getMin()).isEqualTo(1);
            assertThat(stats.getMax()).isEqualTo(3);
            assertThat(stats.getAverage()).isEqualTo(2.0);
        });
    }

    @Test
    void testCollectorsSummarizingLong() {
        List<Long> numbers = List.of(1L, 2L, 3L);

        LongSummaryStatistics result = numbers.stream().collect(Collectors.summarizingLong(Long::longValue));

        assertThat(result).satisfies(stats -> {
            assertThat(stats.getSum()).isEqualTo(6L);
            assertThat(stats.getCount()).isEqualTo(3L);
            assertThat(stats.getMin()).isEqualTo(1L);
            assertThat(stats.getMax()).isEqualTo(3L);
            assertThat(stats.getAverage()).isEqualTo(2.0);
        });
    }

    @Test
    void testCollectorsSummarizingDouble() {
        List<Double> numbers = List.of(1D, 2D, 3D);

        DoubleSummaryStatistics result = numbers.stream().collect(Collectors.summarizingDouble(Double::doubleValue));

        assertThat(result).satisfies(stats -> {
            assertThat(stats.getSum()).isEqualTo(6.0);
            assertThat(stats.getCount()).isEqualTo(3L);
            assertThat(stats.getMin()).isEqualTo(1.0);
            assertThat(stats.getMax()).isEqualTo(3.0);
            assertThat(stats.getAverage()).isEqualTo(2.0);
        });
    }

    @Test
    void testCollectorsAveragingInt() {
        List<Integer> numbers = List.of(1, 2, 3);

        double average = numbers.stream().collect(Collectors.averagingInt(Integer::intValue));

        assertThat(average).isEqualTo(2.0);
    }

    @Test
    void testCollectorsAveragingLong() {
        List<Long> numbers = List.of(1L, 2L, 3L);

        double average = numbers.stream().collect(Collectors.averagingLong(Long::longValue));

        assertThat(average).isEqualTo(2.0);
    }

    @Test
    void testCollectorsAveragingDouble() {
        List<Double> numbers = List.of(1D, 2D, 3D);

        double average = numbers.stream().collect(Collectors.averagingDouble(Double::doubleValue));

        assertThat(average).isEqualTo(2.0);
    }

    @Test
    void testCollectorsMaxBy() {
        List<Integer> numbers = List.of(1, 2, 3);

        Optional<Integer> max = numbers.stream().collect(Collectors.maxBy(Integer::compareTo));

        assertThat(max).isPresent();
        assertThat(max.get()).isEqualTo(3);
    }

    @Test
    void testCollectorsMax() {
        List<Integer> numbers = List.of(1, 2, 3);

        Optional<Integer> max = numbers.stream().max(Integer::compareTo);

        assertThat(max).contains(3);
    }

    @Test
    void testCollectorsMinBy() {
        List<Integer> numbers = List.of(1, 2, 3);

        Optional<Integer> min = numbers.stream().collect(Collectors.minBy(Integer::compareTo));

        assertThat(min).contains(1);
    }

    @Test
    void testCollectorsMin() {
        List<Integer> numbers = List.of(1, 2, 3);

        Optional<Integer> min = numbers.stream().min(Integer::compareTo);

        assertThat(min).contains(1);
    }

    @Test
    void testCollectorsReducing() {
        List<Integer> numbers = List.of(1, 2, 3);

        Optional<Integer> sum = numbers.stream().collect(Collectors.reducing((a, b) -> a + b));

        assertThat(sum).contains(6);
    }

    @Test
    void testCollectorsReducingV2() {
        List<Integer> numbers = List.of(1, 2, 3);

        Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);

        assertThat(sum).contains(6);
    }

    @Test
    void testCollectorsReducingV3() {
        List<Integer> numbers = List.of(1, 2, 3);

        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);

        assertThat(sum).contains(6);
    }

    @Test
    void testCollectorsGroupingByConcurrent() {
        List<String> strings = List.of("apple", "banana", "cherry");

        ConcurrentMap<Integer, List<String>> resultMap = strings
                .stream()
                .collect(Collectors.groupingByConcurrent(String::length));

        assertThat(resultMap).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        5, List.of("apple"),
                        6, List.of("banana", "cherry")
                )
        );
    }

    @Test
    void testCollectorsGroupingByConcurrentParallel() {
        List<String> strings = List.of("apple", "banana", "cherry");

        ConcurrentMap<Integer, List<String>> resultMap = strings.parallelStream().collect(Collectors.groupingByConcurrent(String::length));

        assertThat(resultMap).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        5, List.of("apple"),
                        6, List.of("banana", "cherry")
                )
        );
    }

//    @Test
    void testComparisonGroupingByWithRegularAndParallelStreams() {
        List<String> uuids = Stream.generate(UUID::randomUUID)
                .limit(10_000_000)
                .map(UUID::toString)
                .toList();

        // Garbage collection
        System.gc();

        // Testing regular stream
        long startTimeRegular = System.nanoTime();
        Map<Integer, List<String>> resultMapRegular = uuids
                .stream()
                .collect(Collectors.groupingBy(String::length));
        long durationRegular = System.nanoTime() - startTimeRegular;

        // Testing parallel stream
        long startTimeParallel = System.nanoTime();
        Map<Integer, List<String>> resultMapParallel = uuids
                .parallelStream()
                .collect(Collectors.groupingBy(String::length));
        long durationParallel = System.nanoTime() - startTimeParallel;

        // Output results
        System.out.println("Regular Stream Duration: " + durationRegular + " ns");
        System.out.println("Parallel Stream Duration: " + durationParallel + " ns");

        // Verify that the results are the same
        assertThat(resultMapRegular).isEqualTo(resultMapParallel);

        // Verify that the parallel stream is faster
        assertThat(durationParallel).isLessThanOrEqualTo(durationRegular);
    }

    @Test
    void testCollectorsCollectingAndThen() {
        List<String> strings = List.of("one", "two", "three");

        List<String> uppercasedStrings = strings
                .stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().map(String::toUpperCase).collect(Collectors.toList())
                        )
                );

        assertThat(uppercasedStrings).containsExactly("ONE", "TWO", "THREE");
    }

    @Test
    void testCollectorsMapping() {
        List<String> strings = List.of("one", "two", "three");

        List<Integer> stringLengths = strings.stream().collect(Collectors.mapping(String::length, Collectors.toList()));

        assertThat(stringLengths).containsExactly(3, 3, 5);
    }

    @Test
    void testCollectorsMappingV2() {
        List<String> strings = List.of("one", "two", "three");

        List<Integer> stringLengths = strings.stream().map(String::length).collect(Collectors.toList());

        assertThat(stringLengths).containsExactly(3, 3, 5);
    }

    @Test
    void testCollectorsFiltering() {
        List<String> strings = List.of("one", "two", "three", "four");

        List<String> filteredStrings = strings.stream().collect(Collectors.filtering(s -> s.length() > 3, Collectors.toList()));

        assertThat(filteredStrings).containsExactly("three", "four");
    }

    @Test
    void testCollectorsFilteringV2() {
        List<String> strings = List.of("one", "two", "three", "four");

        List<String> filteredStrings = strings.stream().filter(s -> s.length() > 3).toList();

        assertThat(filteredStrings).containsExactly("three", "four");
    }
}