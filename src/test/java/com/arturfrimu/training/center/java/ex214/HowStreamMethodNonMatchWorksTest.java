package com.arturfrimu.training.center.java.ex214;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodNonMatchWorksTest {

    @Test
    void testNoneMatch() {
        List<Integer> numbers = List.of(1, 2, 3);

        boolean noneGreaterThanThree = numbers.stream().noneMatch(n -> n > 3);

        assertThat(noneGreaterThanThree).isTrue();
    }
}