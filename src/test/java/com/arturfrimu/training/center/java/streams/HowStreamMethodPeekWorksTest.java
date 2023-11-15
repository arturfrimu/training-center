package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see <a href="https://www.baeldung.com/java-streams-peek-api">Stream peek</a>
 */
@Slf4j
class HowStreamMethodPeekWorksTest {

    /**
     * In this case we can see what number was before and after mapping
     */
    @Test
    void testPeekWithIntegersV1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> result = numbers
                .stream()
                .peek(number -> System.out.println("Numarul inainte de operatia map: " + number))
                .map(number -> number * 2)
                .peek(number -> System.out.println("Numarul dupa de operatia map: " + number))
                .toList();

        assertThat(result).isEqualTo(List.of(2, 4, 6, 8, 10));
    }

    /**
     * In this case we can see just filtered strings in console
     */
    @Test
    void testPeekWithStringsV1() {
        List<String> strings = List.of("one", "two", "three");

        List<String> result = strings
                .stream()
                .filter(str -> str.startsWith("t"))
                .peek(System.out::println)
                .toList();

        assertThat(result).isEqualTo(List.of("two", "three"));
    }
}