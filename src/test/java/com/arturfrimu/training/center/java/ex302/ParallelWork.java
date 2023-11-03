package com.arturfrimu.training.center.java.ex302;

import org.junit.jupiter.api.Test;

class ParallelWork {

    @Test
    void testParallelTasks() throws InterruptedException {
        Thread task1 = new Thread(() -> {
            doHardWork(2000);
            System.out.printf("Lambda task1 running: %s%n", Thread.currentThread().getName());
        }, "LambdaThread1");

        Thread task2 = new Thread(() -> {
            doHardWork(3000);
            System.out.printf("Lambda task2 running: %s%n", Thread.currentThread().getName());
        }, "LambdaThread2");

        Thread task3 = new Thread(() -> {
            doHardWork(1000);
            System.out.printf("Lambda task3 running: %s%n", Thread.currentThread().getName());
        }, "LambdaThread3");

        long startTime = System.currentTimeMillis();

        task1.start();
        task2.start();
        task3.start();

        task1.join();
        task2.join();
        task3.join();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("End of all tasks executionTime: " + executionTime + "ms");
    }

    private static void doHardWork(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
