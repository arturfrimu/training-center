package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodSortedWorksTest {

    @Test
    void testSorted() {
        List<Integer> numbers = List.of(3, 1, 2);

        List<Integer> sortedList = numbers.stream().sorted().collect(Collectors.toList());

        assertThat(sortedList).containsExactly(1, 2, 3);
    }
}