package com.arturfrimu.training.center.java.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class HowArrayListWorksTest {

    @Test
    void testAdd() {
        ArrayList<String> arrayList = new ArrayList<>();

        boolean wasAddedSuccessfullyOne = arrayList.add("one");
        boolean wasAddedSuccessfullyTwo = arrayList.add("two");
        boolean wasAddedSuccessfullyThree = arrayList.add("three");

        assertThat(wasAddedSuccessfullyOne).isTrue();
        assertThat(wasAddedSuccessfullyTwo).isTrue();
        assertThat(wasAddedSuccessfullyThree).isTrue();

        assertThat(arrayList).hasSize(3);

        log.info(String.valueOf(arrayList));
    }

    @Test
    void testAddAll() {
        ArrayList<String> arrayList = new ArrayList<>();

        boolean allValuesWasAddedSuccessfully = arrayList.addAll(Arrays.asList("one", "two", "three"));

        assertThat(allValuesWasAddedSuccessfully).isTrue();

        assertThat(arrayList).hasSize(3);

        log.info(String.valueOf(arrayList));
    }

    @Test
    void testAddInParameterizedConstructorCall() {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("one", "two", "three"));

        assertThat(arrayList).hasSize(3);

        log.info(String.valueOf(arrayList));
    }

    @Test
    void testGetByIndex() {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("one");
        arrayList.add("two");
        arrayList.add("three");

        assertThat(arrayList.get(0)).isEqualTo("one");
        assertThat(arrayList.get(1)).isEqualTo("two");
        assertThat(arrayList.get(2)).isEqualTo("three");

        IndexOutOfBoundsException indexOutOfBoundsException1 = assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(-1));
        assertThat(indexOutOfBoundsException1.getMessage()).isEqualTo("Index -1 out of bounds for length 3");

        IndexOutOfBoundsException indexOutOfBoundsException2 = assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(3));
        assertThat(indexOutOfBoundsException2.getMessage()).isEqualTo("Index 3 out of bounds for length 3");

        assertThat(arrayList).hasSize(3);

        log.info(String.valueOf(arrayList));
    }

    @Test
    void testIndexOf() {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("one");
        arrayList.add("two");
        arrayList.add("three");

        int indexOfOne = arrayList.indexOf("one");
        int indexOfTwo = arrayList.indexOf("two");
        int indexOfThree = arrayList.indexOf("three");

        assertThat(indexOfOne).isEqualTo(0);
        assertThat(indexOfTwo).isEqualTo(1);
        assertThat(indexOfThree).isEqualTo(2);

        int indexOfZero = arrayList.indexOf("zero");

        assertThat(indexOfZero).isEqualTo(-1);

        assertThat(arrayList).hasSize(3);

        log.info(String.valueOf(arrayList));
    }
}