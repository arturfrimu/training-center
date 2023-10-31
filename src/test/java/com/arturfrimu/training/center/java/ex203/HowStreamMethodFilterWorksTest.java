package com.arturfrimu.training.center.java.ex203;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see <a href="https://www.geeksforgeeks.org/java-8-predicate-with-examples//">Java Predicate</a>
 */
@Slf4j
class HowStreamMethodFilterWorksTest {

    @Test
    void testFilterWithIntegersV1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> newNumbers = numbers
                .stream()
                .filter(number -> number % 2 == 0)
                .toList();

        assertThat(newNumbers).isEqualTo(List.of(2, 4));
    }

    @Test
    void testFilterWithStringsV1() {
        List<String> strings = List.of("one", "two", "three");

        List<String> newStrings = strings
                .stream()
                .filter(string -> string.startsWith("t"))
                .toList();

        assertThat(newStrings).isEqualTo(List.of("two", "three"));
    }
}