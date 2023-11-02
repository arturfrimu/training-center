package com.arturfrimu.training.center.java.ex107;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowHashMapWorksTest {

    @Test
    void testPut() {
        HashMap<Integer, String> hashMap = new HashMap<>();

        String one = hashMap.put(1, "one");
        String two = hashMap.put(2, "two");
        String three = hashMap.put(3, "three");

        assertThat(one).isNull();
        assertThat(two).isNull();
        assertThat(three).isNull();

        assertThat(hashMap).hasSize(3);

        log.info(String.valueOf(hashMap));
    }

    @Test
    void testPutAll() {
        HashMap<Integer, String> hashMap = new HashMap<>();

        hashMap.putAll(Map.of(1, "one", 2, "two", 3, "three"));

        assertThat(hashMap).hasSize(3);

        log.info(String.valueOf(hashMap));
    }

    @Test
    void testPutInParameterizedConstructorCall() {
        HashMap<Integer, String> hashMap = new HashMap<>(Map.of(1, "one", 2, "two", 3, "three"));

        assertThat(hashMap).hasSize(3);

        log.info(String.valueOf(hashMap));
    }

    @Test
    void testNoOrderGuarantee() {
        HashMap<String, String> hasMap = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String randomString = UUID.randomUUID().toString().substring(0, 4);

            hasMap.put(randomString, randomString);
            arrayList.add(randomString);
        }

        String[] hasMapArray = hasMap.keySet().toArray(new String[0]);
        String[] arrayListArray = arrayList.toArray(new String[0]);

        // Check that at least one element is out of order.
        boolean isAnyElementOutOfOrder = false;
        for (int i = 0; i < hasMapArray.length; i++) {
            if (!hasMapArray[i].equals(arrayListArray[i])) {
                isAnyElementOutOfOrder = true;
                break;
            }
        }

        assertThat(isAnyElementOutOfOrder)
                .as("HasMap should not guarantee order, it should differ from ArrayList")
                .isTrue();

        log.info(String.valueOf(arrayList));
        log.info(String.valueOf(hasMap.keySet()));
    }

    @Test
    void testDuplication() {
        HashMap<Integer, String> hashMap = new HashMap<>();

        hashMap.put(1, "a");
        hashMap.put(1, "b");
        hashMap.put(1, "c");

        assertThat(hashMap).hasSize(1);
        assertThat(hashMap.get(1)).isEqualTo("c");

        log.info(String.valueOf(hashMap));
    }
}