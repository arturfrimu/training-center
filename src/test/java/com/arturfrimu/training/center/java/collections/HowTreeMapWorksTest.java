package com.arturfrimu.training.center.java.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowTreeMapWorksTest {

    @Test
    void testPut() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        String one = treeMap.put(1, "one");
        String two = treeMap.put(2, "two");
        String three = treeMap.put(3, "three");

        assertThat(one).isNull();
        assertThat(two).isNull();
        assertThat(three).isNull();

        assertThat(treeMap).hasSize(3);

        log.info(String.valueOf(treeMap));
    }

    @Test
    void testPutAll() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        Map<Integer, String> map = Map.of(
                1, "one",
                2, "two",
                3, "three"
        );
        
        treeMap.putAll(map);

        assertThat(treeMap).hasSize(3);

        log.info(String.valueOf(treeMap));
    }

    @Test
    void testPutInParameterizedConstructorCall() {
        Map<Integer, String> map = Map.of(
                1, "one",
                2, "two",
                3, "three"
        );

        TreeMap<Integer, String> treeMap = new TreeMap<>(map);

        assertThat(treeMap).hasSize(3);

        log.info(String.valueOf(treeMap));
    }

    @Test
    void testOrderGuarantee() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(3, "three");
        treeMap.put(2, "two");
        treeMap.put(1, "one");

        Integer[] array = treeMap.keySet().toArray(new Integer[0]);

        assertThat(array)
                .as("TreeMap guarantee order")
                .isEqualTo(new Integer[]{1, 2, 3});

        log.info(Arrays.toString(array));
        log.info(String.valueOf(treeMap.keySet()));
    }

    @Test
    void testCustomOrderGuarantee() {
        TreeMap<Integer, String> treeMap = new TreeMap<>((a, b) -> b - a);

        treeMap.put(3, "three");
        treeMap.put(2, "two");
        treeMap.put(1, "one");

        Integer[] array = treeMap.keySet().toArray(new Integer[0]);

        assertThat(array)
                .as("TreeMap guarantee order")
                .isEqualTo(new Integer[]{3, 2, 1});

        log.info(Arrays.toString(array));
        log.info(String.valueOf(treeMap.keySet()));
    }

    @Test
    void testDuplication() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(1, "a");
        treeMap.put(1, "b");
        treeMap.put(1, "c");

        assertThat(treeMap).hasSize(1).containsEntry(1, "c");

        log.info(String.valueOf(treeMap));
    }
}