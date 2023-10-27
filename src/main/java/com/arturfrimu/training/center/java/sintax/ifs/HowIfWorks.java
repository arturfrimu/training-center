package com.arturfrimu.training.center.java.sintax.ifs;

public class HowIfWorks {
    public String isAdult(int age) {
        if (age <= 0) {
            return "Nu exista oameni cu varsta mai mica sau egala cu 0";
        } else if (age > 0 && age < 18) {
            return "Este minor";
        } else if (age >= 18 && age < 150) {
            return "Este adult";
        } else {
            return "Varsta nu poate depasi 150";
        }
    }
}
