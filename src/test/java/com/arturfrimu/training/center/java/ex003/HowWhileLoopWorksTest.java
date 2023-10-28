package com.arturfrimu.training.center.java.ex003;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class HowWhileLoopWorksTest {

    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @ParameterizedTest
    void loop(final int count) {
        HowWhileLoopWorks howWhileLoopWorks = new HowWhileLoopWorks();

        String result = howWhileLoopWorks.whileLoop(count);
        assertThat(result).isEqualTo("Aceasta metoda s-a executat de " + count + " ori");
    }

    @ValueSource(ints = {-1, -10, -100})
    @ParameterizedTest
    void loop2(final int count) {
        HowWhileLoopWorks howWhileLoopWorks = new HowWhileLoopWorks();

        String result = howWhileLoopWorks.whileLoop(count);
        assertThat(result).isEqualTo("Aceasta metoda s-a executat de " + 0 + " ori");
    }
}