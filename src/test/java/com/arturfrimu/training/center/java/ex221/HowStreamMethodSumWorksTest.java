package com.arturfrimu.training.center.java.ex221;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodSumWorksTest {

    @Test
    void testSum() {
        IntStream intStream = IntStream.of(1, 2, 3);

        int sum = intStream.sum();

        assertThat(sum).isEqualTo(6);
    }
}