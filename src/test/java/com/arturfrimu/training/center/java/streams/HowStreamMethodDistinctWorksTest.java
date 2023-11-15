package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodDistinctWorksTest {

    @Test
    void testDistinct() {
        List<Integer> numbers = List.of(1, 2, 2, 3, 3, 3);

        List<Integer> distinctList = numbers.stream().distinct().toList();

        assertThat(distinctList).containsExactly(1, 2, 3);
    }

    @Test
    void testDistinctStrings() {
        List<String> numbers = List.of("1", "2", "2", "3", "3", "3");

        List<String> distinctList = numbers.stream().distinct().toList();

        assertThat(distinctList).containsExactly("1", "2", "3");
    }
}