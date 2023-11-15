package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodBoxedWorksTest {

    @Test
    void testBoxed() {
        IntStream intStream = IntStream.of(1, 2, 3);

        List<Integer> boxedList = intStream.boxed().toList();

        assertThat(boxedList).containsExactly(1, 2, 3);
    }
}