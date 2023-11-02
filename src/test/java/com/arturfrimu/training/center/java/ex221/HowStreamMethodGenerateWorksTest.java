package com.arturfrimu.training.center.java.ex221;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodGenerateWorksTest {

    @Test
    void testGenerate() {
        Stream<Double> infiniteStream = Stream.generate(Math::random);

        List<Double> randomNumbers = infiniteStream.limit(5).toList();

        assertThat(randomNumbers).hasSize(5);
    }
}