package com.arturfrimu.training.center.java.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowLinkedHasMapWorksTest {

    @Test
    void testPut() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();

        String one = linkedHashMap.put(1, "one");
        String two = linkedHashMap.put(2, "two");
        String three = linkedHashMap.put(3, "three");

        assertThat(one).isNull();
        assertThat(two).isNull();
        assertThat(three).isNull();

        assertThat(linkedHashMap).hasSize(3);

        log.info(String.valueOf(linkedHashMap));
    }

    @Test
    void testPutAll() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.putAll(Map.of(1, "one", 2, "two", 3, "three"));

        assertThat(linkedHashMap).hasSize(3);

        log.info(String.valueOf(linkedHashMap));
    }

    @Test
    void testPutInParameterizedConstructorCall() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(Map.of(1, "one", 2, "two", 3, "three"));

        assertThat(linkedHashMap).hasSize(3);

        log.info(String.valueOf(linkedHashMap));
    }

    @Test
    void testNoOrderGuarantee() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put("one", "one");
        linkedHashMap.put("two", "two");
        linkedHashMap.put("three", "three");

        String[] array = linkedHashMap.keySet().toArray(new String[0]);

        assertThat(array)
                .as("LinkedHasMap guarantee order")
                .isEqualTo(new String[]{"one", "two", "three"});

        log.info(Arrays.toString(array));
        log.info(String.valueOf(linkedHashMap.keySet()));
    }

    @Test
    void testDuplication() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(1, "a");
        linkedHashMap.put(1, "b");
        linkedHashMap.put(1, "c");

        assertThat(linkedHashMap).hasSize(1);
        assertThat("c").isEqualTo(linkedHashMap.get(1));

        log.info(String.valueOf(linkedHashMap));
    }
}