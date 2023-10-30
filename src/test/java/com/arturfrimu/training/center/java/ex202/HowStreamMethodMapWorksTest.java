package com.arturfrimu.training.center.java.ex202;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

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
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

        Function<Integer, Integer> multiplyByTwo = number -> number * 2;
        Consumer<Integer> printInConsole = System.out::println;

        integerStream.map(multiplyByTwo).forEach(printInConsole);
    }

    @Test
    void testMapWithIntegersV2() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

        Function<Integer, String> multiplyByTwo = number -> "[ " + number + " ]";
        Consumer<String> printInConsole = System.out::println;

        integerStream.map(multiplyByTwo).forEach(printInConsole);
    }

    @Test
    void testMapWithStringsV1() {
        Stream<String> stringStream = Stream.of("one", "two", "three");

        Function<String, String> addExclamationsAtTheEnd = string -> string + "!!!";
        Consumer<String> printInConsole = System.out::println;

        stringStream.map(addExclamationsAtTheEnd).forEach(printInConsole);
    }

    @Test
    void testMapWithStringsV2() {
        Stream<String> stringStream = Stream.of("1", "2", "3", "4", "5");

        Function<String, Integer> addExclamationsAtTheEnd = string -> Integer.parseInt(string);
        Consumer<Integer> printInConsole = System.out::println;

        stringStream.map(addExclamationsAtTheEnd).forEach(printInConsole);
    }

    @Test
    void testMapWithStringsV3() {
        Stream<String> stringStream = Stream.of("1", "2", "3", "4", "5");

        // method reference  Object::method
        Function<String, Integer> addExclamationsAtTheEnd = Integer::parseInt;
        Consumer<Integer> printInConsole = System.out::println;

        stringStream.map(addExclamationsAtTheEnd).forEach(printInConsole);
    }
}