package com.arturfrimu.training.center.java.ex004;

public class HowSwitchCaseWorks {
    public String dayOfWeek(int day) {
        switch (day) {
            case 1 -> {
                return "Luni";
            }
            case 2 -> {
                return "Marti";
            }
            case 3 -> {
                return "Miercuri";
            }
            case 4 -> {
                return "Joi";
            }
            case 5 -> {
                return "Vineri";
            }
            case 6 -> {
                return "Sambata";
            }
            case 7 -> {
                return "Duminica";
            }
            default -> throw new IllegalStateException("Nu exista asa zi a saptamanii cu numarul: " + day);
        }
    }
}
