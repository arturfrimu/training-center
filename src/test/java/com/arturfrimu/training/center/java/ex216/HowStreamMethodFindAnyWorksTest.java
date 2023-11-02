package com.arturfrimu.training.center.java.ex216;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodFindAnyWorksTest {

    @Test
    void testFindAny() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Optional<Integer> anyEven = numbers.stream().filter(n -> n % 2 == 0).findAny();

        assertThat(anyEven).isPresent();
    }
}