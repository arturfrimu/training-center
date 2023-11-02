package com.arturfrimu.training.center.java.ex212;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodAnyMatchWorksTest {

    @Test
    void testAnyMatch() {
        List<Integer> numbers = List.of(1, 2, 3);

        boolean anyGreaterThanTwo = numbers.stream().anyMatch(n -> n > 2);

        assertThat(anyGreaterThanTwo).isTrue();
    }
}