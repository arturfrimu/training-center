package com.arturfrimu.training.center.java.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowTreeSetWorksTest {

    @Test
    void testAdd() {
        TreeSet<String> treeSet = new TreeSet<>();

        boolean wasAddedSuccessfullyOne = treeSet.add("one");
        boolean wasAddedSuccessfullyTwo = treeSet.add("two");
        boolean wasAddedSuccessfullyThree = treeSet.add("three");

        assertThat(wasAddedSuccessfullyOne).isTrue();
        assertThat(wasAddedSuccessfullyTwo).isTrue();
        assertThat(wasAddedSuccessfullyThree).isTrue();

        assertThat(treeSet).hasSize(3);

        log.info(String.valueOf(treeSet));
    }

    @Test
    void testAddAll() {
        TreeSet<String> treeSet = new TreeSet<>();

        boolean allValuesWasAddedSuccessfully = treeSet.addAll(Arrays.asList("one", "two", "three"));

        assertThat(allValuesWasAddedSuccessfully).isTrue();

        assertThat(treeSet).hasSize(3);

        log.info(String.valueOf(treeSet));
    }

    @Test
    void testAddInParameterizedConstructorCall() {
        TreeSet<String> treeSet = new TreeSet<>(Arrays.asList("one", "two", "three"));

        assertThat(treeSet).hasSize(3);

        log.info(String.valueOf(treeSet));
    }

    @Test
    void testValues() {
        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("a");
        treeSet.add("b");
        treeSet.add("c");

        String[] array = treeSet.toArray(new String[0]);

        assertThat(array)
                .as("TreeSet guarantee sorted order")
                .isEqualTo(new String[]{"a", "b", "c"});

        assertThat(treeSet).hasSize(3);

        log.info(String.valueOf(treeSet));
    }

    @Test
    void testSorting() {
        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("c");
        treeSet.add("b");
        treeSet.add("a");

        String[] array = treeSet.toArray(new String[0]);

        assertThat(array)
                .as("TreeSet guarantee sorted order")
                .isEqualTo(new String[]{"a", "b", "c"});

        assertThat(treeSet).hasSize(3);

        log.info(String.valueOf(treeSet));
    }

    @Test
    void testCustomSorting() {
        TreeSet<String> treeSet = new TreeSet<>(Comparator.reverseOrder());

        treeSet.add("a");
        treeSet.add("b");
        treeSet.add("c");

        String[] array = treeSet.toArray(new String[0]);

        assertThat(array)
                .as("TreeSet guarantee sorted order")
                .isEqualTo(new String[]{"c", "b", "a"});

        assertThat(treeSet).hasSize(3);

        log.info(String.valueOf(treeSet));
    }

    @Test
    void testDuplication() {
        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("a");
        treeSet.add("a");
        treeSet.add("a");

        String[] array = treeSet.toArray(new String[0]);

        assertThat(array[0]).isEqualTo("a");

        assertThat(treeSet).hasSize(1);

        log.info(String.valueOf(treeSet));
    }
}