package com.arturfrimu.training.center.java.ex300;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class CreationOfThreads {

    @Test
    void testThreadCreation() throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread("MyThread");
        myThread.start();
        System.out.println("Tested " + myThread.getName());
        assertTrue(myThread.isAlive());

        Thread thread = new Thread(new MyRunnable(), "MyRunnableThread");
        thread.start();
        System.out.println("Tested " + thread.getName());
        assertTrue(thread.isAlive());

        Thread lambdaThread = new Thread(() -> System.out.println("Lambda lambdaThread running: " + Thread.currentThread().getName()), "LambdaThread");
        lambdaThread.start();
        System.out.println("Tested " + lambdaThread.getName());
        assertTrue(lambdaThread.isAlive());

        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread collableThread = new Thread(futureTask, "MyCallableThread");
        collableThread.start();
        System.out.println("Tested " + collableThread.getName());
        assertEquals("Task done", futureTask.get());

        ExecutorService executorService = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r, "ExecutorServiceThread");
            System.out.println("Thread created: " + t.getName());
            return t;
        });
        Future<?> future = executorService.submit(() -> System.out.println("Task is running in thread: " + Thread.currentThread().getName()));
        assertFalse(future.isCancelled());
        executorService.shutdown();
    }
}

class MyThread extends Thread {
    public MyThread(String name) {
        super(name); // Setăm numele thread-ului
    }

    public void run() {
        System.out.println("Thread '" + getName() + "' is running.");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable is running in thread: " + Thread.currentThread().getName());
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() {
        System.out.println("Callable is executing in thread: " + Thread.currentThread().getName());
        return "Task done";
    }
}
