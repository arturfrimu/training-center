package com.arturfrimu.training.center.junior.java.section1;

/**
 * Tipuri de date: <b>"byte"</b> este un tip de date primitiv care stochează numere întregi mici  <br> în intervalul [-128, 127]. <br>
 * Aceasta este o reprezentare a unui byte overflow:
 * <p>
 * <img src="/Users/arturfrimu/Desktop/training-center/training-center/src/main/resources/junior/java/section1/lesson3/byte-boundaries.jpeg" alt="Byte Overflow Illustration">
 * </p>
 *
 * Imaginea pe care ai încărcat-o ilustrează conceptul de overflow și underflow pentru tipul de date byte în Java .<br>
 * În Java, un byte este o valoare întreagă de 8 biți care poate stoca numere între -128 și 127. Dacă te uiți la imagine ca la un ceas, odată ce ai ajuns la 127 și adaugi 1,
 * indicatorul sare la -128. Acest fenomen este cunoscut sub numele de overflow.
 * Similar, dacă scad 1 din -128, indicatorul sare la 127, ceea ce este cunoscut ca underflow.
 *
 * Imaginea arată acest lucru ca un cerc sau o buclă, unde după valoarea maximă (127), urmează valoarea minimă (-128) și invers. Aceasta este o reprezentare vizuală a cum aritmetica pe biți este ciclică pentru tipurile de date cu dimensiune fixă în calculatoare.
// * {@link com.arturfrimu.training.center.junior.java.section1.Lesson3Test}
 */
public class Lesson3 {
    public static void main(String[] args) {
        byte minim = -128;
        byte maxim = 127;

        System.out.println("minim = " + minim);
        System.out.println("maxim = " + maxim);
    }
}
