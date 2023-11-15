package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see <a href="https://www.javatpoint.com/java-8-method-reference">Method Reference</a>
 * @see <a href="https://www.geeksforgeeks.org/stream-foreach-method-java-examples/">Method forEach</a>
 * @see <a href="https://www.geeksforgeeks.org/java-8-consumer-interface-in-java-with-examples/">Java Consumer</a>
 */
@Slf4j
class HowStreamMethodForEachWorksTest {

    @Test
    void testForEachWithIntegers() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

        Consumer<Integer> printlnInConsole = x -> System.out.println(x);

        integerStream.forEach(printlnInConsole);
    }

    @Test
    void testForEachWithStrings() {
        Stream<String> stringStream = Stream.of("one", "two", "three");

        Consumer<String> printlnInConsole = System.out::println;

        stringStream.forEach(printlnInConsole);
    }

    @Test
    void testForEach() {
        List<Integer> numbers = List.of(1, 2, 3);

        List<Integer> result = new ArrayList<>();

        numbers.stream().forEach(result::add);

        assertThat(result).containsExactlyElementsOf(numbers);
    }
}