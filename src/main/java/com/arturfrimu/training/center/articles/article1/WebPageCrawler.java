package com.arturfrimu.training.center.articles.article1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebPageCrawler {
    public static void main(String[] args) {
        int expectedIOLatency = 500; // Estimated I/O latency in milliseconds
        int numberOfThreads = 4; // Adjust based on your expected latency and system capabilities

        try (ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads)) {
            // List of URLs to crawl.
            String[] urlsToCrawl = {
                    "https://example.com",
                    "https://google.com",
                    "https://github.com",
                    // Add more URLs here
            };

            for (String url : urlsToCrawl) {
                threadPool.execute(() -> crawlWebPage(url, expectedIOLatency));
            }

            threadPool.shutdown();
        }
    }

    private static void crawlWebPage(String url, int expectedIOLatency) {
        // Simulate web page crawling (I/O-bound) task.
        // Perform HTTP request and process the page content.
        try {
            Thread.sleep(expectedIOLatency); // Simulating I/O latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
