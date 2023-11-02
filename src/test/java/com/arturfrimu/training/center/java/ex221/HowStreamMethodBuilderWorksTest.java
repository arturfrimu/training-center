package com.arturfrimu.training.center.java.ex221;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodBuilderWorksTest {

    @Test
    void testBuilder() {
        Stream.Builder<Integer> builder = Stream.builder();

        Stream<Integer> stream = builder.add(1).add(2).add(3).build();

        assertThat(stream.toList()).containsExactly(1, 2, 3);
    }
}