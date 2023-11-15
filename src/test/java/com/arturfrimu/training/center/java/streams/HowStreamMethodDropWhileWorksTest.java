package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodDropWhileWorksTest {

    @Test
    void testDropWhile() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 1, 2);

        List<Integer> droppedList = numbers.stream().dropWhile(n -> n < 4).toList();

        assertThat(droppedList).containsExactly(4, 5, 1, 2);
    }
}