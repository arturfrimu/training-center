package com.arturfrimu.training.center.articles.article1;

/**
 * How to Determine Java Thread Pool Size: A Comprehensive Guide
 */
public class Lesson1 {
    public static void main(String[] args) {
        int numOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println(numOfCores);
    }
}
