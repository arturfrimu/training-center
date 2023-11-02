package com.arturfrimu.training.center.java.ex211;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodSkipWorksTest {

    @Test
    void testSkip() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> skippedList = numbers.stream().skip(2).toList();

        assertThat(skippedList).containsExactly(3, 4, 5);
    }
}