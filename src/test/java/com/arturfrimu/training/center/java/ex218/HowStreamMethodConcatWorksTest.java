package com.arturfrimu.training.center.java.ex218;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodConcatWorksTest {

    @Test
    void testConcat() {
        Stream<String> stream1 = Stream.of("a", "b");
        Stream<String> stream2 = Stream.of("c", "d");

        List<String> combinedList = Stream.concat(stream1, stream2).toList();

        assertThat(combinedList).containsExactly("a", "b", "c", "d");
    }
}