package com.nitroblok;

import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nitroblok.implementations.PressureClassificator;

public class PressureClassificatorTest {

    @ParameterizedTest(name ="{index} => initValue={0}, expected={1}")
    @MethodSource("initializationSource")
    void shouldCreateLogInitializationMeasurement(double initValue, boolean expected) {
        IPressureClassificator pressureClassificator = new PressureClassificator();
        boolean result = pressureClassificator.shouldCreateLog(initValue);
        assertThat(result, IsEqual.equalTo(expected));
    }
    static Stream<Arguments> initializationSource() {
        return Stream.of(
                Arguments.of(45.0, true),
                Arguments.of(120.0, true),
                Arguments.of(186.0, false),
                Arguments.of(248.0, true),
                Arguments.of(310.0, true),
                Arguments.of(540.0, true)
        );
    }

    @ParameterizedTest(name ="{index} => initValue={0}, measuredVAlue={1}, expected={2}")
    @MethodSource("priorValuesSource")
    void shouldCreateLogWithPriorValuesMeausurement(double initValue, double measuredValue, boolean expected) {
        final IPressureClassificator pressureClassificator = new PressureClassificator();
        pressureClassificator.shouldCreateLog(initValue);
        final boolean result = pressureClassificator.shouldCreateLog(measuredValue);
        assertThat(result, IsEqual.equalTo(expected));
    }
    static Stream<Arguments> priorValuesSource() {
        return Stream.of(
                Arguments.of(49.99999, 48.999, false),
                Arguments.of(45.0, 120.0, true),
                Arguments.of(45.0, 700.0, true),
                Arguments.of(55.0, 49.99999, true),
                Arguments.of(120.0, 100.0, false),
                Arguments.of(170.0, 186.0, true),
                Arguments.of(186.0, 170.0, true),
                Arguments.of(186.0, 219.0, false),
                Arguments.of(186.0, 257.0, true),
                Arguments.of(230.0, 289.0, false),
                Arguments.of(257.0, 186.0, true),
                Arguments.of(276.0, 310.0, true),
                Arguments.of(310.0, 276.0, true),
                Arguments.of(310.0, 510.0, true),
                Arguments.of(330.0, 400.0, false),
                Arguments.of(503.0, 700.0, false),
                Arguments.of(503.0, 700.0, false),
                Arguments.of(510.0, 310.0, true)
        );
    }
    
}