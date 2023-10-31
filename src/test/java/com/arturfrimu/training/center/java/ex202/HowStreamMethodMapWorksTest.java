package com.arturfrimu.training.center.java.ex202;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see <a href=https://www.geeksforgeeks.org/functional-interfaces-java/?ref=lbp">Functional Interfaces in Java</a>
 * @see <a href="https://www.geeksforgeeks.org/java-8-consumer-interface-in-java-with-examples/">Consumer Interface</a>
 * @see <a href="https://www.geeksforgeeks.org/function-interface-in-java-with-examples/?ref=lbp">Function Interface</a>
 * @see <a href="https://www.javatpoint.com/java-8-method-reference">Method reference</a>
 */
@Slf4j
class HowStreamMethodMapWorksTest {

    @Test
    void testMapWithIntegersV1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> newNumbers = numbers
                .stream()
                .map(number -> number * 2)
                .toList();

        assertThat(newNumbers).isEqualTo(List.of(2, 4, 6, 8, 10));
    }

    @Test
    void testMapWithIntegersV2() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<String> newNumbers = numbers
                .stream()
                .map(number -> "[ " + number + " ]")
                .toList();

        assertThat(newNumbers).isEqualTo(List.of("[ 1 ]", "[ 2 ]", "[ 3 ]", "[ 4 ]", "[ 5 ]"));
    }

    @Test
    void testMapWithStringsV1() {
        List<String> strings = List.of("one", "two", "three");

        List<String> newStrings = strings
                .stream()
                .map(String::toUpperCase)
                .toList();

        assertThat(newStrings).isEqualTo(List.of("ONE", "TWO", "THREE"));
    }

    @Test
    void testMapWithStringsV2() {
        List<String> strings = List.of("1", "2", "3", "4", "5");

        List<Integer> newStrings = strings
                .stream()
                .map(Integer::parseInt)
                .toList();

        assertThat(newStrings).isEqualTo(List.of(1, 2, 3, 4, 5));
    }
}