package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodToArrayWorksTest {

    @Test
    void testToArray() {
        List<Integer> numbers = List.of(1, 2, 3);

        Integer[] array = numbers.stream().toArray(Integer[]::new);

        assertThat(array).containsExactly(1, 2, 3);
    }
}