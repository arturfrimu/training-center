package com.arturfrimu.training.center.java.ex103;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

@Slf4j
class DifferenceBetweenLinkedListAndArrayListGetTest {
    private static final LinkedList<Long> linkedList = new LinkedList<>();
    private static final ArrayList<Long> arrayList = new ArrayList<>();

    private static final Random random = new Random();

    @BeforeAll
    public static void setupOnce() {
        for (int i = 0; i < 10_000_000; i++) {
            linkedList.add(random.nextLong());
            arrayList.add(random.nextLong());
        }
    }

    /**
     * Extract 150_000 from 10_000_000 from begin
     */
    @Test
    void testGettingFromBeginPerformance() {
        int extractFromBegin = 150_000;

        long startTimeForLinkedList = System.nanoTime();

        for (int i = 0; i < extractFromBegin; i++) {
            linkedList.get(i);
        }

        long endTimeForLinkedList = System.nanoTime();
        long elapsedForLinkedList = endTimeForLinkedList - startTimeForLinkedList;

        long startTimeForArrayList = System.nanoTime();

        for (int i = 0; i < extractFromBegin; i++) {
            arrayList.get(i);
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

    /**
     * Extract 500 from 10_000_000 from center
     */
    @Test
    void testGettingFromCenterPerformance() {
        int extractFromCenter = 500;

        long startTimeForLinkedList = System.nanoTime();

        for (int i = 5_000_000; i < 5_000_000 + extractFromCenter; i++) {
            linkedList.get(i);
        }

        long endTimeForLinkedList = System.nanoTime();
        long elapsedForLinkedList = endTimeForLinkedList - startTimeForLinkedList;

        long startTimeForArrayList = System.nanoTime();

        for (int i = 5_000_000; i < 5_000_000 + extractFromCenter; i++) {
            arrayList.get(i);
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

    /**
     * Extract 150_000 from 10_000_000 from end
     */
    @Test
    void testGettingFromEndPerformance() {
        int extractFromEnd = 150_000;

        long startTimeForLinkedList = System.nanoTime();

        for (int i = 10_000_000 - 1; i > 10_000_000 - extractFromEnd; i--) {
            linkedList.get(i);
        }

        long endTimeForLinkedList = System.nanoTime();
        long elapsedForLinkedList = endTimeForLinkedList - startTimeForLinkedList;

        long startTimeForArrayList = System.nanoTime();

        for (int i = 10_000_000 - 1; i > 10_000_000 - extractFromEnd; i--) {
            arrayList.get(i);
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