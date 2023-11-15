package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodOfWorksTest {

    @Test
    void testOf() {
        Stream<String> stream = Stream.of("a", "b", "c");

        List<String> resultList = stream.toList();

        assertThat(resultList).containsExactly("a", "b", "c");
    }
}