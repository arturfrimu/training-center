package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodLimitWorksTest {

    @Test
    void testLimit() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> limitedList = numbers.stream().limit(3).toList();

        assertThat(limitedList).containsExactly(1, 2, 3);
    }
}