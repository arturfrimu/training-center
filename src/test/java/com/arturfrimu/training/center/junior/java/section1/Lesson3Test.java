package com.arturfrimu.training.center.junior.java.section1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Lesson3Test {

    /**
     * Acesta este un test unitar care demonstreaza ca 1 + 1 = 2
     */
    @Test
    void test1() {
        byte b1 = 1;
        byte b2 = 1;

        byte rezultat = (byte) (b1 + b2);

        System.out.println(rezultat);

        assertThat(rezultat).isEqualTo((byte) 2); // nu trage atentia la (byte) asta se numeste casting, v-om invata mai tarziu
    }

    /**
     * Acesta este un test unitar care demonstreaza ca 126 + 1 = 127 <br>
     * Diapazonul maxim pentru byte este 127
     */
    @Test
    void test2() {
        byte b1 = 126;
        byte b2 = 1;

        byte rezultat = (byte) (b1 + b2);

        System.out.println(rezultat);

        assertThat(rezultat).isEqualTo((byte) 127);
    }

    /**
     * Acesta este un test unitar care demonstreaza ca -127 + (-1) = -128 <br>
     * Diapazonul minim pentru byte este -128
     */
    @Test
    void test3() {
        byte b1 = -127;
        byte b2 = -1;

        byte rezultat = (byte) (b1 + b2);

        System.out.println(rezultat);

        assertThat(rezultat).isEqualTo((byte) -128);
    }

    /**
     * Acesta este un test unitar care demonstreaza ca:  <br>
     * 127 + 1 = 128 matematic <br>
     * 127 + 1 = -128 programatic <br>
     * Asta se intampla cand incalcam diapazonul maxim lui byte de 127
     */
    @Test
    void test4() {
        byte b1 = 127;
        byte b2 = 1;

        byte rezultat = (byte) (b1 + b2);

        System.out.println(rezultat);

        assertThat(rezultat).isEqualTo((byte) -128);
    }

    /**
     * Acesta este un test unitar care demonstreaza ca:  <br>
     * -128 + (-1) matematic = -129 <br>
     * -128 + (-1) programatic =127 <br>
     * Asta se intampla cand incalcam diapazonul minim lui byte de -128
     */
    @Test
    void test5() {
        byte b1 = -128;
        byte b2 = -1;

        byte rezultat = (byte) (b1 + b2);

        System.out.println(rezultat);

        assertThat(rezultat).isEqualTo((byte) 127);
    }
}