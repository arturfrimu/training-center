package com.arturfrimu.training.center.java.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodFindFirstWorksTest {

    @Test
    void testFindFirst() {
        List<Integer> numbers = List.of(1, 2, 3);

        Optional<Integer> firstEven = numbers.stream().filter(n -> n % 2 == 0).findFirst();

        assertThat(firstEven).contains(2);
    }
}