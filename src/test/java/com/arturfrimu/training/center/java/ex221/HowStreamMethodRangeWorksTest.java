package com.arturfrimu.training.center.java.ex221;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodRangeWorksTest {

    @Test
    void testRange() {
        IntStream rangeStream = IntStream.range(1, 4); // 1 to 3

        assertThat(rangeStream.toArray()).containsExactly(1, 2, 3);
    }

    @Test
    void testRangeClosed() {
        IntStream rangeClosedStream = IntStream.rangeClosed(1, 3); // 1 to 3

        assertThat(rangeClosedStream.toArray()).containsExactly(1, 2, 3);
    }
}