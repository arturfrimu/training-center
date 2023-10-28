package com.arturfrimu.training.center.java.ex104;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowHasSetWorksTest {

    @Test
    void testAdd() {
        HashSet<String> hashSet = new HashSet<>();

        boolean wasAddedSuccessfullyOne = hashSet.add("one");
        boolean wasAddedSuccessfullyTwo = hashSet.add("two");
        boolean wasAddedSuccessfullyThree = hashSet.add("three");

        assertThat(wasAddedSuccessfullyOne).isTrue();
        assertThat(wasAddedSuccessfullyTwo).isTrue();
        assertThat(wasAddedSuccessfullyThree).isTrue();

        assertThat(hashSet).hasSize(3);

        log.info(String.valueOf(hashSet));
    }

    @Test
    void testAddAll() {
        HashSet<String> hashSet = new HashSet<>();

        boolean allValuesWasAddedSuccessfully = hashSet.addAll(Arrays.asList("one", "two", "three"));

        assertThat(allValuesWasAddedSuccessfully).isTrue();

        assertThat(hashSet).hasSize(3);

        log.info(String.valueOf(hashSet));
    }

    @Test
    void testAddInParameterizedConstructorCall() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("one", "two", "three"));

        assertThat(hashSet).hasSize(3);

        log.info(String.valueOf(hashSet));
    }

    @Test
    void testValues() {
        HashSet<String> hashSet = new HashSet<>();
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String randomString = UUID.randomUUID().toString().substring(0, 4);

            hashSet.add(randomString);
            arrayList.add(randomString);
        }

        String[] hashSetArray = hashSet.toArray(new String[0]);
        String[] arrayListArray = arrayList.toArray(new String[0]);

        // Check that at least one element is out of order.
        boolean isAnyElementOutOfOrder = false;
        for (int i = 0; i < hashSetArray.length; i++) {
            if (!hashSetArray[i].equals(arrayListArray[i])) {
                isAnyElementOutOfOrder = true;
                break;
            }
        }

        assertThat(isAnyElementOutOfOrder)
                .as("HashSet should not guarantee order, it should differ from ArrayList")
                .isTrue();

        log.info(String.valueOf(arrayList));
        log.info(String.valueOf(hashSet));
    }

    @Test
    void testDuplication() {
        HashSet<String> hashSet = new HashSet<>();

        hashSet.add("a");
        hashSet.add("a");
        hashSet.add("a");

        String[] array = hashSet.toArray(new String[0]);

        assertThat(array[0]).isEqualTo("a");

        assertThat(hashSet).hasSize(1);

        log.info(String.valueOf(hashSet));
    }
}