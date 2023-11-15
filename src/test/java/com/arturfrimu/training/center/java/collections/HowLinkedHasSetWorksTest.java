package com.arturfrimu.training.center.java.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowLinkedHasSetWorksTest {

    @Test
    void testAdd() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        boolean wasAddedSuccessfullyOne = linkedHashSet.add("one");
        boolean wasAddedSuccessfullyTwo = linkedHashSet.add("two");
        boolean wasAddedSuccessfullyThree = linkedHashSet.add("three");

        assertThat(wasAddedSuccessfullyOne).isTrue();
        assertThat(wasAddedSuccessfullyTwo).isTrue();
        assertThat(wasAddedSuccessfullyThree).isTrue();

        assertThat(linkedHashSet).hasSize(3);

        log.info(String.valueOf(linkedHashSet));
    }

    @Test
    void testAddAll() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        boolean allValuesWasAddedSuccessfully = linkedHashSet.addAll(Arrays.asList("one", "two", "three"));

        assertThat(allValuesWasAddedSuccessfully).isTrue();

        assertThat(linkedHashSet).hasSize(3);

        log.info(String.valueOf(linkedHashSet));
    }

    @Test
    void testAddInParameterizedConstructorCall() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList("one", "two", "three"));

        assertThat(linkedHashSet).hasSize(3);

        log.info(String.valueOf(linkedHashSet));
    }

    @Test
    void testValues() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add("a");
        linkedHashSet.add("b");
        linkedHashSet.add("c");

        String[] array = linkedHashSet.toArray(new String[0]);

        assertThat(array[0]).isEqualTo("a");
        assertThat(array[1]).isEqualTo("b");
        assertThat(array[2]).isEqualTo("c");

        assertThat(linkedHashSet).hasSize(3);

        log.info(String.valueOf(linkedHashSet));
    }

    @Test
    void testSorting() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add("c");
        linkedHashSet.add("b");
        linkedHashSet.add("a");

        String[] array = linkedHashSet.toArray(new String[0]);

        assertThat(array)
                .as("LinkedHashSet guarantee order")
                .isEqualTo(new String[]{"c", "b", "a"});

        assertThat(linkedHashSet).hasSize(3);

        log.info(String.valueOf(linkedHashSet));
    }

    @Test
    void testDuplication() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add("a");
        linkedHashSet.add("a");
        linkedHashSet.add("a");

        String[] array = linkedHashSet.toArray(new String[0]);

        assertThat(array[0]).isEqualTo("a");

        assertThat(linkedHashSet).hasSize(1);

        log.info(String.valueOf(linkedHashSet));
    }
}