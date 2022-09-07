package com.nitroblok;

import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nitroblok.implementations.PressureClassificator;

public class PressureClassificatorShouldRiseAlarmTest {
    @ParameterizedTest(name ="{index} => initValue={0}, expected={1}")
    @MethodSource("shouldRiseAlarmSource")
    void shouldRiseAlarm(double initValue, boolean expected) {
        IPressureClassificator pressureClassificator = new PressureClassificator();
        boolean result = pressureClassificator.shouldRiseAlarm(initValue);
        assertThat(result, IsEqual.equalTo(expected));
    }
    static Stream<Arguments> shouldRiseAlarmSource() {
        return Stream.of(
                Arguments.of(500, false),
                Arguments.of(500.000001, true),
                Arguments.of(450, false),
                Arguments.of(850, true)
        );
    }

    
}
