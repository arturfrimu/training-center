package com.arturfrimu.training.center.articles.article1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoEncodingApp {
    public static void main(String[] args) {
        int availableCores = Runtime.getRuntime().availableProcessors();
        int numberOfThreads = Math.max(availableCores - 1, 1); // Adjust as needed

        try (ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads)) {
            // Submit video encoding tasks to the thread pool.
            for (int i = 0; i < 10; i++) {
                threadPool.execute(VideoEncodingApp::encodeVideo);
            }

            threadPool.shutdown();
        }
    }

    private static void encodeVideo() {
        // Simulate video encoding (CPU-bound) task.
        // Complex calculations and compression algorithms here.
    }
}
