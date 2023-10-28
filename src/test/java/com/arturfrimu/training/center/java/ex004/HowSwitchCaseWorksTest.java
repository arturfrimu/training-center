package com.arturfrimu.training.center.java.ex004;

import com.arturfrimu.training.center.streams.exception.ResourceNotFoundException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HowSwitchCaseWorksTest {

    @ParameterizedTest
    @MethodSource("dayOfWeekArgumentsProvider")
    void dayOfWeek(final int day, final String expectedWeek) {
        HowSwitchCaseWorks howSwitchCaseWorks = new HowSwitchCaseWorks();

        String actulaWeek = howSwitchCaseWorks.dayOfWeek(day);

        assertThat(actulaWeek).isEqualTo(expectedWeek);
    }

    public static Stream<Arguments> dayOfWeekArgumentsProvider() {
        return Stream.of(
                Arguments.of(1, "Luni"),
                Arguments.of(2, "Marti"),
                Arguments.of(3, "Miercuri"),
                Arguments.of(4, "Joi"),
                Arguments.of(5, "Vineri"),
                Arguments.of(6, "Sambata"),
                Arguments.of(7, "Duminica")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidDayOfWeekArgumentsProvider")
    void invalidDayOfWeek(final int day, final String errorMessage) {
        HowSwitchCaseWorks howSwitchCaseWorks = new HowSwitchCaseWorks();

        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> howSwitchCaseWorks.dayOfWeek(day));

        assertThat(illegalStateException.getMessage()).isEqualTo(errorMessage);
    }

    public static Stream<Arguments> invalidDayOfWeekArgumentsProvider() {
        int randomNumberBiggerThan7 = new Random().nextInt(8, Integer.MAX_VALUE);
        int randomNumberMinorThan1 = new Random().nextInt(Integer.MIN_VALUE, 0);

        return Stream.of(
                Arguments.of(randomNumberBiggerThan7, "Nu exista asa zi a saptamanii cu numarul: " + randomNumberBiggerThan7),
                Arguments.of(randomNumberMinorThan1, "Nu exista asa zi a saptamanii cu numarul: " + randomNumberMinorThan1)
        );
    }
}