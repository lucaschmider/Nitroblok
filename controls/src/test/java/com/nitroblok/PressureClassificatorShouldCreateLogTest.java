package com.nitroblok;

import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nitroblok.implementations.PressureClassificator;

public class PressureClassificatorShouldCreateLogTest {
    //Arrange
    @ParameterizedTest(name ="{index} => initValue={0}, voltage={1}, expected={2}")
    @MethodSource("shouldCreateLog_testData_initializationMeasurement")
    void shouldCreateLog_initializationMeasurement_triggerAPIRequest(double initValue, double voltage, boolean expected) {
        //Act
        IPressureClassificator pressureClassificator = new PressureClassificator();
        boolean result = pressureClassificator.shouldCreateLog(initValue, voltage);

        //Assert
        assertThat(result, IsEqual.equalTo(expected));
    }

    //Arrange
    static Stream<Arguments> shouldCreateLog_testData_initializationMeasurement() {
        return Stream.of(
            Arguments.of(45.0, 4.99, true),
            Arguments.of(45.0, 5, true),
            
            Arguments.of(120.0, 4.99, true),
            Arguments.of(120.0, 5, true),
            
            Arguments.of(186.0, 4.99, true),
            Arguments.of(186.0, 5, false),
            
            Arguments.of(248.0, 4.99, true),
            Arguments.of(248.0, 5, true),
            
            Arguments.of(310.0, 4.99, true),
            Arguments.of(310.0, 5, true),
            
            Arguments.of(540.0, 4.99, true),
            Arguments.of(540.0, 5, true)
        );
    }

    //Arrange
    @ParameterizedTest(name ="{index} => initPressure={0}, measuredPressure={1}, initVoltage={2}, measuredVoltage={3} expected={4}")
    @MethodSource("shouldCreateLog_testData_priorMeasurement")
    void shouldCreateLog_withPriorMeasurement_triggerAPIRequest(double initPressure, double measuredPressure, double initVoltage, double measuredVoltage, boolean expected) {
        //Act
        final IPressureClassificator pressureClassificator = new PressureClassificator();
        pressureClassificator.shouldCreateLog(initPressure, initVoltage);
        final boolean result = pressureClassificator.shouldCreateLog(measuredPressure, measuredVoltage);

        //Assert
        assertThat(result, IsEqual.equalTo(expected));
    }

    //Arrange
    static Stream<Arguments> shouldCreateLog_testData_priorMeasurement() {
        return Stream.of(
                Arguments.of(49.99999, 48.999, 12, 13, false),
                Arguments.of(49.99999, 48.999, 4, 3, false),
                Arguments.of(49.99999, 48.999, 4.99, 5, true),
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
                Arguments.of(120.0, 100.0, 4, 3, false),
                Arguments.of(120.0, 100.0, 4.99, 5, true),
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
                Arguments.of(230.0, 289.0, 4, 3, false),
                Arguments.of(230.0, 289.0, 4.99, 5, true),
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
                Arguments.of(330.0, 400.0, 4, 3, false),
                Arguments.of(330.0, 400.0, 4.99, 5, true),
                Arguments.of(330.0, 400.0, 5, 4.99, true),

                Arguments.of(503.0, 700.0, 12, 13,  false),
                Arguments.of(503.0, 700.0, 4, 3, false),
                Arguments.of(503.0, 700.0, 4.99, 5, true),
                Arguments.of(503.0, 700.0, 5, 4.99, true),
                
                Arguments.of(510.0, 310.0, 12, 13,  true),
                Arguments.of(510.0, 310.0, 4, 3, true),
                Arguments.of(510.0, 310.0, 4.99, 5, true),
                Arguments.of(510.0, 310.0, 5, 4.99, true)
        );
    }
    
}