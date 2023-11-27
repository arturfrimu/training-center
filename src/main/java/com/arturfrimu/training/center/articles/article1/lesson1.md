# How to Determine Java Thread Pool Size: A Comprehensive Guide

Thread creation in Java incurs a noticeable cost. Creating threads consumes time, adds latency to request processing, and involves substantial work by both the JVM and the operating system. To mitigate these overheads, thread pools come into play.

In this article, we delve into the art of determining the ideal thread pool size. A finely-tuned thread pool can extract optimal performance from your system and help you gracefully navigate peak workloads. However, it’s essential to remember that, even when utilizing a thread pool, the management of threads can itself become a bottleneck.

![plot](/Users/arturfrimu/Desktop/training-center/training-center/src/main/java/com/arturfrimu/training/center/medium/article1/ThreadPoolExecutor.webp)

# Reasons for using Thread Pool

Performance: Thread creation and destruction can be expensive, especially in Java. Thread pools help to reduce this overhead by creating a pool of threads that can be reused for multiple tasks.
Scalability: Thread pools can be scaled to meet the needs of the application. For example, under heavy load, the thread pool can be expanded to handle the additional tasks.
Resource management: Thread pools can help to manage the resources used by threads. For example, a thread pool can limit the number of threads that can be active at any given time, which can help to prevent the application from running out of memory.

# Sizing Your Thread Pool: Understanding System and Resource Limits

Understanding the limitations of your system, including hardware and external dependencies, is crucial when sizing a thread pool. Let’s elaborate on this concept with an example:

## Scenario:

Imagine you are developing a web application that handles incoming HTTP requests. Each request may involve processing data from a database and making calls to an external third-party service. Your goal is to determine the optimal thread pool size for handling these requests efficiently.

## Factors to Consider:

Database Connection Pool: Suppose you’re using a connection pool like HikariCP to manage database connections. You’ve configured it to allow a maximum of 100 connections. If you create more threads than there are available connections, these extra threads will end up waiting for an available connection, leading to resource contention and potential performance issues.

### Here’s an example of configuring a HikariCP database connection pool:

```java
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnectionExample {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        config.setUsername("username");
        config.setPassword("password");
        config.setMaximumPoolSize(100); // Set the maximum number of connections

        HikariDataSource dataSource = new HikariDataSource(config);

        // Use the dataSource to get database connections and perform queries.
    }
}
```


External Service Throughput: The external service your application interacts with has a limitation. It can only handle a few requests simultaneously, say 10 requests at a time. Sending more requests concurrently could overwhelm the service and lead to performance degradation or errors.

CPU Cores: Determining the number of CPU cores available on your server is essential for optimizing thread pool size.

```java
int numOfCores = Runtime.getRuntime().availableProcessors();
```

Each core can execute one thread concurrently. Exceeding the number of CPU cores with threads can lead to excessive context switching, which can degrade performance.

# CPU-Intensive Tasks & I/O-Intensive Tasks
![plot](/Users/arturfrimu/Desktop/training-center/training-center/src/main/java/com/arturfrimu/training/center/medium/article1/CPU-Intensive-Tasks&I:O-Intensive-Tasks.webp)

CPU-intensive tasks are those that require a lot of processing power, such as performing complex calculations or running simulations. These tasks are often limited by the speed of the CPU, rather than the speed of the I/O device.

- Encoding or decoding audio or video files
- Compiling and linking software
- Running complex simulations
- Performing machine learning or data mining tasks
- Playing video games

## Optimization:

- Multi-threading and parallelism: Parallel processing is a technique used to divide a larger task into smaller subtasks and distribute these subtasks across multiple CPU cores or processors to take advantage of concurrent execution and improve overall performance
  
Suppose you have a large array of numbers, and you want to calculate the square of each number concurrently using multiple threads, taking advantage of parallel processing.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelSquareCalculator {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = Runtime.getRuntime().availableProcessors(); // Get the number of CPU cores
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int number : numbers) {
            executorService.submit(() -> {
                int square = calculateSquare(number);
                System.out.println("Square of " + number + " is " + square);
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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
```

IO-intensive tasks are those that interact with storage devices (e.g., reading/writing files), network sockets (e.g., making API calls), or user input (e.g., user interactions in a graphical user interface).

- Reading or writing large files to disk (e.g., saving a video file, loading a database)
- Downloading or uploading files over the network (e.g., browsing the web, watching a streaming video)
- Sending and receiving email
- Running a web server or other network service
- Performing database queries
- Web servers handling incoming requests.

## Optimization:

- Caching: Cache frequently accessed data in memory to reduce the need for repeated I/O operations.
- Load balancing: Distribute I/O-bound tasks across multiple threads or processes to handle concurrent I/O operations efficiently.
- Use of SSDs: Solid-state drives (SSDs) can significantly speed up I/O operations compared to traditional hard disk drives (HDDs).
- Use efficient data structures, such as hash tables and B-trees, to reduce the number of I/O operations required.
- Avoid unnecessary file operations, such as opening and closing files multiple times.

## Determining Number Of Threads:

### For CPU Intensive Tasks:

For CPU-bound tasks, You want to maximize CPU utilization without overwhelming the system with too many threads, which can lead to excessive context switching. A common rule of thumb is to use the number of CPU cores available

### Real-Life Example: Video Encoding

Imagine you’re developing a video processing application. Video encoding is a CPU-bound task where you need to apply complex algorithms to compress a video file. You have a multi-core CPU available.

### Determining the Number of Threads for CPU-Bound Tasks:

1. Count Available CPU Cores: Use Runtime.getRuntime().availableProcessors() in Java to determine the number of available CPU cores. Let's say you have 8 cores.
2. Create Thread Pool: Create a thread pool with a size close to or slightly less than the number of available CPU cores. In this case, you might choose 6 or 7 threads to leave some CPU capacity for other tasks and system processes.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoEncodingApp {
    public static void main(String[] args) {
        int availableCores = Runtime.getRuntime().availableProcessors();
        int numberOfThreads = Math.max(availableCores - 1, 1); // Adjust as needed

        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);

        // Submit video encoding tasks to the thread pool.
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                encodeVideo(); // Simulated video encoding task
            });
        }

        threadPool.shutdown();
    }

    private static void encodeVideo() {
        // Simulate video encoding (CPU-bound) task.
        // Complex calculations and compression algorithms here.
    }
}
```

## For I/O Intensive Tasks:

For I/O-bound tasks, the optimal number of threads is often determined by the nature of the I/O operations and the expected latency. You want to have enough threads to keep the I/O devices busy without overloading them. The ideal number may not necessarily be equal to the number of CPU cores.

## Real-Life Example: Web Page Crawling

Consider building a web page crawler that downloads web pages and extracts information. This involves making HTTP requests, which are I/O-bound tasks due to network latency.

## Determining the Number of Threads for I/O-Bound Tasks:

1. Analyze I/O Latency: Estimate the expected I/O latency, which depends on the network or storage. For example, if each HTTP request takes around 500 milliseconds to complete, you may want to accommodate for some overlap in I/O operations.
2. Create Thread Pool: Create a thread pool with a size that balances parallelism with the expected I/O latency. You don’t necessarily need one thread per task; instead, you can have a smaller pool that efficiently manages I/O-bound tasks.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebPageCrawler {
    public static void main(String[] args) {
        int expectedIOLatency = 500; // Estimated I/O latency in milliseconds
        int numberOfThreads = 4; // Adjust based on your expected latency and system capabilities

        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);

        // List of URLs to crawl.
        String[] urlsToCrawl = {
            "https://example.com",
            "https://google.com",
            "https://github.com",
            // Add more URLs here
        };

        for (String url : urlsToCrawl) {
            threadPool.execute(() -> {
                crawlWebPage(url, expectedIOLatency);
            });
        }

        threadPool.shutdown();
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
```


### Can We Follow a Concrete Formula?

The formula for determining thread pool size can be written as follows:

Number of threads = Number of Available Cores * Target CPU utilization * (1 + Wait time / Service time)

Number of Available Cores: This is the number of CPU cores available to your application. It is important to note that this is not the same as the number of CPUs, as each CPU may have multiple cores.

Target CPU utilization: This is the percentage of CPU time that you want your application to use. If you set the target CPU utilization too high, your application may become unresponsive. If you set it too low, your application will not be able to fully utilize the available CPU resources.

Wait time: This is the amount of time that threads spend waiting for I/O operations to complete. This can include waiting for network responses, database queries, or file operations.

Service time: This is the amount of time that threads spend performing computation.

Blocking coefficient: This is the ratio of wait time to service time. It is a measure of how much time threads spend waiting for I/O operations to complete relative to the amount of time they spend performing computation.

# Example Usage

Suppose you have a server with 4 CPU cores and you want your application to use 50% of the available CPU resources.

Your application has two classes of tasks: I/O-intensive tasks and CPU-intensive tasks.

The I/O-intensive tasks have a blocking coefficient of 0.5, meaning that they spend 50% of their time waiting for I/O operations to complete.

Number of threads = 4 cores * 0.5 * (1 + 0.5) = 3 threads

The CPU-intensive tasks have a blocking coefficient of 0.1, meaning that they spend 10% of their time waiting for I/O operations to complete.

Number of threads = 4 cores * 0.5 * (1 + 0.1) = 2.2 threads

In this example, you would create two thread pools, one for the I/O-intensive tasks and one for the CPU-intensive tasks. The I/O-intensive thread pool would have 3 threads and the CPU-intensive thread pool would have 2 threads.