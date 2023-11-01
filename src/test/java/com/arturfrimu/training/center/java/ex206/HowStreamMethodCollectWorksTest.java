package com.arturfrimu.training.center.java.ex206;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodCollectWorksTest {

    @Test
    void testCollectorsToList() {
        List<String> strings = List.of("one", "two", "three");

        List<String> result = strings
                .stream()
                .collect(Collectors.toList());

        result.add("four"); //Modifiable list

        assertThat(result.getClass()).isEqualTo(ArrayList.class);

        assertThat(result).isEqualTo(List.of("one", "two", "three", "four"));
    }
}