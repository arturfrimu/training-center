package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see <a href="https://www.baeldung.com/java-stream-reduce">Stream reduce</a>
 * @see <a href="https://medium.com/@mgm06bm/list-of-vs-arrays-aslist-7e2f7af64361#:~:text=of()%3A-,List.,cannot%20be%20modified%20after%20creation.">List.of()</a>
 */
@Slf4j
class HowStreamMethodReduceWorksTest {

    /**
     * List. of() is a factory method introduced in Java 9 that creates an immutable list containing the specified elements. Here are some key points to note: Immutability: The resulting list is immutable, meaning its size and elements cannot be modified after creation.
     */
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