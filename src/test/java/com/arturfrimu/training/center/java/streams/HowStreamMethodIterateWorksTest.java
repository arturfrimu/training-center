package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodIterateWorksTest {

    @Test
    void testIterate() {
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 1);

        List<Integer> firstFiveNumbers = infiniteStream.limit(5).toList();

        assertThat(firstFiveNumbers).containsExactly(0, 1, 2, 3, 4);
    }
}