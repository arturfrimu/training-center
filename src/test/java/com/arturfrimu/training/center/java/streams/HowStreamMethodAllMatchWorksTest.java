package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodAllMatchWorksTest {

    @Test
    void testAllMatch() {
        List<Integer> numbers = List.of(1, 2, 3);

        boolean allGreaterThanZero = numbers.stream().allMatch(n -> n > 0);

        assertThat(allGreaterThanZero).isTrue();
    }
}