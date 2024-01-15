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

        int day = 4;
        switch (day) {
            case 1:
                System.out.println("LUNI");
                break;
            case 2:
                System.out.println("MARTI");
                break;
            case 3:
                System.out.println("MIERCURI");
                break;
            case 4:
                System.out.println("JOI");
                break;
            case 5:
                System.out.println("VINERI");
                break;
            case 6:
                System.out.println("SAMBATA");
                break;
            case 7:
                System.out.println("DUMINICA");
                break;
            default:
                System.out.println("NU EXISTA ASA ZI A SAPTAMANII");
        }
    }
}
