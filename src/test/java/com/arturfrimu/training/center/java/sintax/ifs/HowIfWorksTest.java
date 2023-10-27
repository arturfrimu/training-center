package com.arturfrimu.training.center.java.sintax.ifs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class HowIfWorksTest {

    @ValueSource(ints = {0, -1, -100, -1000})
    @ParameterizedTest
    void negativeAge(final int age) {
        HowIfWorks howIfWorks = new HowIfWorks();

        String result = howIfWorks.isAdult(age);
        assertThat(result).isEqualTo("Nu exista oameni cu varsta mai mica sau egala cu 0");
    }

    @ValueSource(ints = {1, 2, 14, 17})
    @ParameterizedTest
    void isMinor(final int age) {
        HowIfWorks howIfWorks = new HowIfWorks();

        String result = howIfWorks.isAdult(age);
        assertThat(result).isEqualTo("Este minor");
    }

    @ValueSource(ints = {18, 20, 57, 149})
    @ParameterizedTest
    void isAdult(final int age) {
        HowIfWorks howIfWorks = new HowIfWorks();

        String result = howIfWorks.isAdult(age);
        assertThat(result).isEqualTo("Este adult");
    }

    @ValueSource(ints = {150, 160, 500, 1000})
    @ParameterizedTest
    void oneHundredFiftyPlus(final int age) {
        HowIfWorks howIfWorks = new HowIfWorks();

        String result = howIfWorks.isAdult(age);
        assertThat(result).isEqualTo("Varsta nu poate depasi 150");
    }
}