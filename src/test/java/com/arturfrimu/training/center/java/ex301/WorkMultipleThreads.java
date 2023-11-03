package com.arturfrimu.training.center.java.ex301;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkMultipleThreads {

    @Test
    void testSingleThreadExecutionTime() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int numberOfThreads1 = 1;
        noMultithreading(numberOfThreads1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time with nr threads " + numberOfThreads1 + ": " + executionTime + "ms");

        long startTime2 = System.currentTimeMillis();
        int numberOfThreads2 = 2;
        multithreading(numberOfThreads2);
        long endTime2 = System.currentTimeMillis();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("Execution time with nr threads " + numberOfThreads2 + ": " + executionTime2 + "ms");

        long startTime3 = System.currentTimeMillis();
        int numberOfThreads3 = 5;
        multithreading(numberOfThreads3);
        long endTime3 = System.currentTimeMillis();
        long executionTime3 = endTime3 - startTime3;
        System.out.println("Execution time with nr threads " + numberOfThreads3 + ": " + executionTime3 + "ms");

        long startTime4 = System.currentTimeMillis();
        int numberOfThreads4 = 10;
        multithreading(numberOfThreads4);
        long endTime4 = System.currentTimeMillis();
        long executionTime4 = endTime4 - startTime4;
        System.out.println("Execution time with nr threads " + numberOfThreads4 + ": " + executionTime4 + "ms");
    }

    private static void noMultithreading(final int numberOfThreads) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        Runnable task = () -> doHardWork(1000);

        for (int i = 0; i < 10; i++) {
            service.submit(task);
        }

        service.shutdown();
        boolean finished = service.awaitTermination(1, TimeUnit.MINUTES);
        assertTrue(finished, "Not all tasks finished within the time limit.");
    }

    private static void multithreading(final int numberOfThreads) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        Runnable task = () -> doHardWork(1000);

        for (int i = 0; i < 10; i++) {
            service.submit(task);
        }

        service.shutdown();
        boolean finished = service.awaitTermination(1, TimeUnit.MINUTES);
        assertTrue(finished, "Not all tasks finished within the time limit.");
    }

    private static void doHardWork(final int timeout) {
        try {
            System.out.println(Thread.currentThread().getName() + " start");
            TimeUnit.MILLISECONDS.sleep(timeout);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
