package com.arturfrimu.training.center.java.collections.collectioncomparison;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

@Slf4j
class DifferenceBetweenLinkedListAndArrayListAddFromEndTest {
    private static final LinkedList<Long> linkedList = new LinkedList<>();
    private static final ArrayList<Long> arrayList = new ArrayList<>();

    private static final Random random = new Random();

//    @BeforeAll
    public static void setupOnce() {
        for (int i = 0; i < 10_000_000; i++) {
            linkedList.add(random.nextLong());
            arrayList.add(random.nextLong());
        }
    }

//    @Test
    void testAddingPerformance() {
        int addNext = 10_000_000;

        long startTimeForLinkedList = System.nanoTime();

        for (int i = 0; i < addNext; i++) {
            linkedList.add(random.nextLong());
        }

        long endTimeForLinkedList = System.nanoTime();
        long elapsedForLinkedList = endTimeForLinkedList - startTimeForLinkedList;

        long startTimeForArrayList = System.nanoTime();

        for (int i = 0; i < addNext; i++) {
            arrayList.add(random.nextLong());
        }

        long endTimeForArrayList = System.nanoTime();
        long elapsedForArrayList = endTimeForArrayList - startTimeForArrayList;

        double linkedListTimeInSeconds = nanoSecondsInSeconds(elapsedForLinkedList);
        double arrayListTimeInSeconds = nanoSecondsInSeconds(elapsedForArrayList);

        log.info("Time taken for LinkedList: " + linkedListTimeInSeconds + " seconds");
        log.info("Time taken for ArrayList: " + arrayListTimeInSeconds + " seconds");

        double difference = nanoSecondsInSeconds((elapsedForLinkedList - elapsedForArrayList));
        log.info("Difference: {}", difference);
    }

    private static double nanoSecondsInSeconds(long elapsedForLinkedList) {
        return elapsedForLinkedList / 1000_000_000.0;
    }
}