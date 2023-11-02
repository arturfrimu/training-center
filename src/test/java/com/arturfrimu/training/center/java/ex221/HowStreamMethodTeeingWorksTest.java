package com.arturfrimu.training.center.java.ex221;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@Slf4j
class HowStreamMethodTeeingWorksTest {

    @Test
    void testTeeingV1() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        String result = stream.collect(Collectors.teeing(
                Collectors.summingInt(Integer::intValue),
                Collectors.counting(),
                (sum, count) -> "Sum: " + sum + ", Count: " + count));
        assertThat(result).isEqualTo("Sum: 15, Count: 5");
    }

    @Test
    void testTeeingV2() {
        Stream<Double> doubleStream = Stream.of(1.1, 2.2, 3.3);

        Double avgResult = doubleStream.collect(Collectors.teeing(
                Collectors.summingDouble(Double::doubleValue),
                Collectors.counting(),
                (sum, count) -> sum / count));

        // Use within() to allow a small margin of error for floating-point arithmetic
        assertThat(avgResult).isCloseTo(2.2, within(0.0001));
    }

    @Test
    void testTeeingV3() {
        Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5);

        String evenOddResult = intStream.collect(Collectors.teeing(
                Collectors.filtering(i -> i % 2 == 0, Collectors.toList()),
                Collectors.filtering(i -> i % 2 != 0, Collectors.toList()),
                (evens, odds) -> "Evens: " + evens + ", Odds: " + odds));
        assertThat(evenOddResult).isEqualTo("Evens: [2, 4], Odds: [1, 3, 5]");
    }
}