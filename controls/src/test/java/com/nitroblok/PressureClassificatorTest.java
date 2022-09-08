package com.nitroblok;

import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nitroblok.implementations.PressureClassificator;

public class PressureClassificatorTest {

    @ParameterizedTest(name ="{index} => pressure={0}, voltage={1}, expected={2}")
    @MethodSource("ShouldRing_initializationSource")
    void shouldCreateLogInitializationMeasurement(double pressure, double voltage, boolean expected) {
        IPressureClassificator pressureClassificator = new PressureClassificator();
        boolean result = pressureClassificator.shouldCreateLog(pressure, voltage);
        assertThat(result, IsEqual.equalTo(expected));
    }
    static Stream<Arguments> ShouldRing_initializationSource() {
        return Stream.of(
                Arguments.of(45.0, 4.99, true),
                Arguments.of(45.0, 5, true),

                Arguments.of(120.0, 4.99, true),
                Arguments.of(120.0, 5, false),

                Arguments.of(186.0, 4.99, true),
                Arguments.of(186.0, 5, false),

                Arguments.of(248.0, 4.99, true),
                Arguments.of(248.0, 5, false),

                Arguments.of(310.0, 4.99, true),
                Arguments.of(310.0, 5, true),

                Arguments.of(540.0, 4.99, true),
                Arguments.of(540.0, 5, true)
        );
    }

    @ParameterizedTest(name ="{index} => initValue={0}, measuredValue={1}, initVoltage={2}, measuredVoltage={3} expected={4}")
    @MethodSource("priorValuesSource")
    void shouldCreateLogWithPriorValuesMeausurement(double initValue, double measuredValue, double initVoltage, double measuredVoltage, boolean expected) {
        final IPressureClassificator pressureClassificator = new PressureClassificator();
        pressureClassificator.shouldCreateLog(initValue, initVoltage);
        final boolean result = pressureClassificator.shouldCreateLog(measuredValue, measuredVoltage);
        assertThat(result, IsEqual.equalTo(expected));
    }
    static Stream<Arguments> priorValuesSource() {
        return Stream.of(
                Arguments.of(49.99999, 48.999, 12, 13, false),
                //Arguments.of(49.99999, 48.999, 4, 3, false),
                //Arguments.of(49.99999, 48.999, 4.99, 5, true),
                Arguments.of(49.99999, 48.999, 5, 4.99, true),

                Arguments.of(45.0, 120.0, 12, 13,  true),
                Arguments.of(45.0, 120.0, 4, 3, true),
                Arguments.of(45.0, 120.0, 4.99, 5, true),
                Arguments.of(45.0, 120.0, 5, 4.99, true),

                Arguments.of(45.0, 700.0, 12, 13,  true),
                Arguments.of(45.0, 700.0, 4, 3, true),
                Arguments.of(45.0, 700.0, 4.99, 5, true),
                Arguments.of(45.0, 700.0, 5, 4.99, true),

                Arguments.of(55.0, 49.99999, 12, 13,  true),
                Arguments.of(55.0, 49.99999, 4, 3, true),
                Arguments.of(55.0, 49.99999, 4.99, 5, true),
                Arguments.of(55.0, 49.99999, 5, 4.99, true),

                Arguments.of(120.0, 100.0, 12, 13,  false),
                //Arguments.of(120.0, 100.0, 4, 3, false),
                //Arguments.of(120.0, 100.0, 4.99, 5, true),
                Arguments.of(120.0, 100.0, 5, 4.99, true),

                Arguments.of(170.0, 186.0, 12, 13,  true),
                Arguments.of(170.0, 186.0, 4, 3, true),
                Arguments.of(170.0, 186.0, 4.99, 5, true),
                Arguments.of(170.0, 186.0, 5, 4.99, true),

                Arguments.of(186.0, 170.0, 12, 13,  true),
                Arguments.of(186.0, 170.0, 4, 3, true),
                Arguments.of(186.0, 170.0, 4.99, 5, true),
                Arguments.of(186.0, 170.0, 5, 4.99, true),
                
                Arguments.of(186.0, 219.0, 12, 13,  false),
                Arguments.of(186.0, 219.0, 4, 3, false),
                Arguments.of(186.0, 219.0, 4.99, 5, true),
                Arguments.of(186.0, 219.0, 5, 4.99, true),

                Arguments.of(186.0, 257.0, 12, 13,  true),
                Arguments.of(186.0, 257.0, 4, 3, true),
                Arguments.of(186.0, 257.0, 4.99, 5, true),
                Arguments.of(186.0, 257.0, 5, 4.99, true),

                Arguments.of(230.0, 289.0, 12, 13,  false),
                //Arguments.of(230.0, 289.0, 4, 3, false),
                //Arguments.of(230.0, 289.0, 4.99, 5, true),
                Arguments.of(230.0, 289.0, 5, 4.99, true),

                Arguments.of(257.0, 186.0, 12, 13,  true),
                Arguments.of(257.0, 186.0, 4, 3, true),
                Arguments.of(257.0, 186.0, 4.99, 5, true),
                Arguments.of(257.0, 186.0, 5, 4.99, true),

                Arguments.of(276.0, 310.0, 12, 13,  true),
                Arguments.of(276.0, 310.0, 4, 3, true),
                Arguments.of(276.0, 310.0, 4.99, 5, true),
                Arguments.of(276.0, 310.0, 5, 4.99, true),

                Arguments.of(310.0, 276.0, 12, 13,  true),
                Arguments.of(310.0, 276.0, 4, 3, true),
                Arguments.of(310.0, 276.0, 4.99, 5, true),
                Arguments.of(310.0, 276.0, 5, 4.99, true),

                Arguments.of(310.0, 510.0, 12, 13,  true),
                Arguments.of(310.0, 510.0, 4, 3, true),
                Arguments.of(310.0, 510.0, 4.99, 5, true),
                Arguments.of(310.0, 510.0, 5, 4.99, true),

                Arguments.of(330.0, 400.0, 12, 13,  false),
                //Arguments.of(330.0, 400.0, 4, 3, false),
                //Arguments.of(330.0, 400.0, 4.99, 5, true),
                Arguments.of(330.0, 400.0, 5, 4.99, true),

                Arguments.of(503.0, 700.0, 12, 13,  false),
                //Arguments.of(503.0, 700.0, 4, 3, false),
                //Arguments.of(503.0, 700.0, 4.99, 5, true),
                Arguments.of(503.0, 700.0, 5, 4.99, true),
                
                Arguments.of(510.0, 310.0, 12, 13,  true),
                Arguments.of(510.0, 310.0, 4, 3, true),
                Arguments.of(510.0, 310.0, 4.99, 5, true),
                Arguments.of(510.0, 310.0, 5, 4.99, true)
        );
    }
    
}