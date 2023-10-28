package com.arturfrimu.training.center.java.ex102;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class HowLinkedListWorksTest {

    @Test
    void testAdd() {
        LinkedList<String> linkedList = new LinkedList<>();

        boolean wasAddedSuccessfullyOne = linkedList.add("one");
        boolean wasAddedSuccessfullyTwo = linkedList.add("two");
        boolean wasAddedSuccessfullyThree = linkedList.add("three");

        assertThat(wasAddedSuccessfullyOne).isTrue();
        assertThat(wasAddedSuccessfullyTwo).isTrue();
        assertThat(wasAddedSuccessfullyThree).isTrue();

        assertThat(linkedList).hasSize(3);

        log.info(String.valueOf(linkedList));
    }

    @Test
    void testAddAll() {
        LinkedList<String> linkedList = new LinkedList<>();

        boolean allValuesWasAddedSuccessfully = linkedList.addAll(Arrays.asList("one", "two", "three"));

        assertThat(allValuesWasAddedSuccessfully).isTrue();

        assertThat(linkedList).hasSize(3);

        log.info(String.valueOf(linkedList));
    }

    @Test
    void testAddInParameterizedConstructorCall() {
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList("one", "two", "three"));

        assertThat(linkedList).hasSize(3);

        log.info(String.valueOf(linkedList));
    }

    @Test
    void testGetByIndex() {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("one");
        linkedList.add("two");
        linkedList.add("three");

        assertThat(linkedList.get(0)).isEqualTo("one");
        assertThat(linkedList.get(1)).isEqualTo("two");
        assertThat(linkedList.get(2)).isEqualTo("three");

        IndexOutOfBoundsException indexOutOfBoundsException1 = assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-1));
        assertThat(indexOutOfBoundsException1.getMessage()).isEqualTo("Index: -1, Size: 3");

        IndexOutOfBoundsException indexOutOfBoundsException2 = assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(3));
        assertThat(indexOutOfBoundsException2.getMessage()).isEqualTo("Index: 3, Size: 3");

        assertThat(linkedList).hasSize(3);

        log.info(String.valueOf(linkedList));
    }

    @Test
    void testIndexOf() {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("one");
        linkedList.add("two");
        linkedList.add("three");

        int indexOfOne = linkedList.indexOf("one");
        int indexOfTwo = linkedList.indexOf("two");
        int indexOfThree = linkedList.indexOf("three");

        assertThat(indexOfOne).isEqualTo(0);
        assertThat(indexOfTwo).isEqualTo(1);
        assertThat(indexOfThree).isEqualTo(2);

        int indexOfZero = linkedList.indexOf("zero");

        assertThat(indexOfZero).isEqualTo(-1);

        assertThat(linkedList).hasSize(3);

        log.info(String.valueOf(linkedList));
    }
}