package com.arturfrimu.training.center.java.ex002;

public class HowForLoopWorks {
    public String loop(int count) {
        int number = 0;

        for (int i = 0; i < count; i++) {
            number++;
        }

        return "Aceasta metoda s-a executat de " + number + " ori";
    }
}
