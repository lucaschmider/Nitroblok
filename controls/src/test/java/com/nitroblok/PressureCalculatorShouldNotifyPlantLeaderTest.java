package com.nitroblok;

import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nitroblok.implementations.PressureClassificator;

public class PressureCalculatorShouldNotifyPlantLeaderTest {

    //Arrange
    @ParameterizedTest(name ="{index} => initValue={0}, expected={1}")
    @MethodSource("shouldNotifyPlantLeader_testData_initializationMeasurement")
    void shouldNotifyPlantLeader_initializationMeasurement_triggerAPIRequest(double initValue, boolean expected) {
        //Act
        IPressureClassificator pressureClassificator = new PressureClassificator();
        boolean result = pressureClassificator.shouldNotifyPlantLeader(initValue);

        //Assert
        assertThat(result, IsEqual.equalTo(expected));
    }

    //Arrange
    static Stream<Arguments> shouldNotifyPlantLeader_testData_initializationMeasurement() {
        return Stream.of(
                Arguments.of(180.0, true),
                Arguments.of(180.00001, false),
                Arguments.of(200, false),
                Arguments.of(219.99999, false),
                Arguments.of(220.0, true),
                Arguments.of(300, true),
                Arguments.of(350, true)
        );
    }

    //Arrange
    @ParameterizedTest(name ="{index} => initValue={0}, measuredVAlue={1}, expected={2}")
    @MethodSource("shouldNotifyPlantLeader_testData_priorMeasurement")
    void shouldNotifyPlantLeader_withPriorMeasurement_triggerAPIRequest(double initValue, double measuredValue, boolean expected) {
        //Act
        final IPressureClassificator pressureClassificator = new PressureClassificator();
        pressureClassificator.shouldNotifyPlantLeader(initValue);
        final boolean result = pressureClassificator.shouldNotifyPlantLeader(measuredValue);

        //Assert
        assertThat(result, IsEqual.equalTo(expected));
    }

    //Arrange
    static Stream<Arguments> shouldNotifyPlantLeader_testData_priorMeasurement() {
        return Stream.of(
                Arguments.of(40, 45, false),
                Arguments.of(49.999, 50, true),
                Arguments.of(50, 50.0001, false),
                Arguments.of(65, 120, false),
                Arguments.of(175, 180, false),
                Arguments.of(180, 185, false),
                Arguments.of(207, 212, false),
                Arguments.of(219.999, 220, true),
                Arguments.of(238, 290, false),
                Arguments.of(300, 300.0001, true),
                Arguments.of(320, 350, false),
                Arguments.of(45, 350, true),
                Arguments.of(400, 200, false),
                Arguments.of(220, 65, true)
        );
    }
}
