package com.arturfrimu.training.center.java.ex204;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see <a href="https://www.geeksforgeeks.org/java-8-predicate-with-examples/">Java Predicate</a>
 * @see <a href="https://www.baeldung.com/java-stream-reduce">Stream reduce</a>
 */
@Slf4j
class HowStreamMethodReduceWorksTest {

    @Test
    void testReduceWithIntegersV1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Integer result = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);

        assertThat(result).isEqualTo(15);
    }

    @Test
    void testReduceWithIntegersV2() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Integer result = numbers
                .stream()
                .reduce(0, Integer::sum);

        assertThat(result).isEqualTo(15);
    }

    @Test
    void testReduceWithStringsV1() {
        List<String> strings = List.of("one", "two", "three");

        String result = strings
                .stream()
                .reduce("", (subtotal, element) -> subtotal.concat(element));

        assertThat(result).isEqualTo("onetwothree");
    }

    @Test
    void testReduceWithStringsV2() {
        List<String> strings = List.of("one", "two", "three");

        var result = strings
                .stream()
                .reduce("", String::concat);

        assertThat(result).isEqualTo("onetwothree");
    }

    @Test
    void testReduceWithStringsV3() {
        List<String> strings = List.of("one", "two", "three");

        Integer result = strings
                .stream()
                .map(String::length)
                .reduce(0, Integer::sum);

        assertThat(result).isEqualTo(11);
    }
}