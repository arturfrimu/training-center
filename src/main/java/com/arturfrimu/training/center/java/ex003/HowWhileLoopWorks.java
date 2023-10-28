package com.arturfrimu.training.center.java.ex003;

public class HowWhileLoopWorks {
    public String whileLoop(int count) {
        int number = 0;

        while (number < count) {
            number++;
        }

        return "Aceasta metoda s-a executat de " + number + " ori";
    }
}
