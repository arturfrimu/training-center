package com.arturfrimu.training.center.loops;

public class LoopsExample {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }

        int nr = 0;
        while (nr < 10) {
            System.out.println(nr++);
        }

        do {
            System.out.println(nr--);
        } while (nr > 0);
    }
}
