package com.nitroblok;

import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nitroblok.implementations.PressureClassificator;

public class PressureClassificatorShouldRingTest {

    //Arrange
    @ParameterizedTest(name ="{index} => pressure={0}, voltage={1}, expected={2}")
    @MethodSource("shouldRing_testData_initializationMeasurement")
    void shouldRing_initializationMeasurement_triggerAPIRequest(double pressure, double voltage, boolean expected) {
        //Act
        IPressureClassificator pressureClassificator = new PressureClassificator();
        boolean result = pressureClassificator.shouldRing(pressure, voltage);

        //Assert
        assertThat(result, IsEqual.equalTo(expected));
    }

    //Arrange
    static Stream<Arguments> shouldRing_testData_initializationMeasurement() {
        return Stream.of(
                Arguments.of(45.0, 4.99999, true),
                Arguments.of(45.0, 5, true),

                Arguments.of(49.9999999, 4.9999, true),
                Arguments.of(49.9999999, 5, true),

                Arguments.of(50, 4.9999, true),
                Arguments.of(50, 5, false),

                Arguments.of(69, 4.9999, true),
                Arguments.of(69, 5, false),

                Arguments.of(300, 4.9999, true),
                Arguments.of(300, 5, false),

                Arguments.of(300.0001, 4.9999, true),
                Arguments.of(300.0001, 5, true),

                Arguments.of(600, 4.9999, true),
                Arguments.of(600, 5, true)
        );
    }

    //Arrange
    @ParameterizedTest(name ="{index} => initPressure={0}, measuredPressure={1}, initVoltage={2}, measuredVoltage={3}, expected={4}")
    @MethodSource("shouldRing_testData_priorMeasurement")
    void shouldRing_withPriorMeasurement_triggerAPIRequest(double initPressure, double measuredPressure, double initVoltage, double measuredVoltage, boolean expected) {
        //Act
        final IPressureClassificator pressureClassificator = new PressureClassificator();
        pressureClassificator.shouldRing(initPressure, initVoltage);
        final boolean result = pressureClassificator.shouldRing(measuredPressure, measuredVoltage);

        //Assert
        assertThat(result, IsEqual.equalTo(expected));
    }

    //Arrange
    static Stream<Arguments> shouldRing_testData_priorMeasurement() {
        return Stream.of(

            //Border: 50
            Arguments.of(50, 49.9999, 5.1, 5, true),
            Arguments.of(50, 49.9999, 5, 4.9, true),
            Arguments.of(50, 49.9999, 4.9, 5, true),
            Arguments.of(50, 49.9999, 4.9, 4, true),

            Arguments.of(49.9999, 50, 5.1, 5, false),
            Arguments.of(49.9999, 50, 5, 4.9, true),
            Arguments.of(49.9999, 50, 4.9, 5, false),
            Arguments.of(49.9999, 50, 4.9, 4, false),

            Arguments.of(49.9999, 43, 5.1, 5, false),
            Arguments.of(49.9999, 43, 5, 4.9, true),
            Arguments.of(49.9999, 43, 4.9, 5, false),
            Arguments.of(49.9999, 43, 4.9, 4, false),


            Arguments.of(50, 120, 5.1, 5, false),
            Arguments.of(50, 120, 5, 4.9, true),
            Arguments.of(50, 120, 4.9, 5, false),
            Arguments.of(50, 120, 4.9, 4, false),

            
            //Border: 300
            Arguments.of(300, 300.001, 5.1, 5, true),
            Arguments.of(300, 300.001, 5, 4.9, true),
            Arguments.of(300, 300.001, 4.9, 5, true),
            Arguments.of(300, 300.001, 4.9, 4, true),

            Arguments.of(300.001, 300, 5.1, 5, false),
            Arguments.of(300.001, 300, 5, 4.9, true),
            Arguments.of(300.001, 300, 4.9, 5, false),
            Arguments.of(300.001, 300, 4.9, 4, false),

            Arguments.of(300.001, 343, 5.1, 5, false),
            Arguments.of(300.001, 343, 5, 4.9, true),
            Arguments.of(300.001, 343, 4.9, 5, false),
            Arguments.of(300.001, 343, 4.9, 4, false),


            Arguments.of(300, 220, 5.1, 5, false),
            Arguments.of(300, 220, 5, 4.9, true),
            Arguments.of(300, 220, 4.9, 5, false),
            Arguments.of(300, 220, 4.9, 4, false)
        );
    }
}
