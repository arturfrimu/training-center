package com.arturfrimu.training.center.articles.article1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelSquareCalculator {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = Runtime.getRuntime().availableProcessors(); // Get the number of CPU cores

        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(numThreads);
            for (int number : numbers) {
                executorService.submit(() -> {
                    int square = calculateSquare(number);
                    System.out.println("Square of " + number + " is " + square);
                });
            }
        } finally {
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static int calculateSquare(int number) {
        // Simulate a time-consuming calculation (e.g., database query, complex computation)
        try {
            Thread.sleep(1000); // Simulate a 1-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return number * number;
    }
}
