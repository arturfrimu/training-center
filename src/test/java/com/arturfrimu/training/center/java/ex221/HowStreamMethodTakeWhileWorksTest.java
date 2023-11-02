package com.arturfrimu.training.center.java.ex221;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodTakeWhileWorksTest {

    @Test
    void testTakeWhile() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 1, 2);

        List<Integer> takenList = numbers.stream().takeWhile(n -> n < 4).toList();

        assertThat(takenList).containsExactly(1, 2, 3);
    }
}