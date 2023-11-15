package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodUnorderedWorksTest {

    @Test
    void testUnordered() {
        Stream<Integer> sortedUnorderedStream = Stream.of(4, 3, 2, 1).sorted().unordered();
        assertThat(sortedUnorderedStream.collect(Collectors.toList())).containsExactlyInAnyOrder(2, 1, 4, 3);
    }
}