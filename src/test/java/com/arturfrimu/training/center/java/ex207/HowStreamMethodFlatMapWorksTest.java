package com.arturfrimu.training.center.java.ex207;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodFlatMapWorksTest {

    @Test
    void testFlatMap() {
        List<List<Integer>> nestedLists = List.of(List.of(1, 2), List.of(3, 4));

        List<Integer> flattenedList = nestedLists.stream().flatMap(List::stream).collect(Collectors.toList());

        assertThat(flattenedList).containsExactly(1, 2, 3, 4);
    }
}