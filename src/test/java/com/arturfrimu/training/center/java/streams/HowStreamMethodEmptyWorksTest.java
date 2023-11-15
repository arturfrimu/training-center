package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodEmptyWorksTest {

    @Test
    void testEmpty() {
        Stream<String> emptyStream = Stream.empty();

        List<String> resultList = emptyStream.toList();

        assertThat(resultList).isEmpty();
    }
}