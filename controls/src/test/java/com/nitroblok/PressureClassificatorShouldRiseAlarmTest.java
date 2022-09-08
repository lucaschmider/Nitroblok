package com.nitroblok;

import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nitroblok.implementations.PressureClassificator;

public class PressureClassificatorShouldRiseAlarmTest {
    //Arrange
    @ParameterizedTest(name ="{index} => initValue={0}, expected={1}")
    @MethodSource("shouldRiseAlarm_testData_alarmMeasurement")
    void shouldRiseAlarm_alarmMeasurement_triggerAPIRequest(double initValue, boolean expected) {
        //Act
        IPressureClassificator pressureClassificator = new PressureClassificator();
        boolean result = pressureClassificator.shouldRiseAlarm(initValue);

        //Assert
        assertThat(result, IsEqual.equalTo(expected));
    }

    //Arrange
    static Stream<Arguments> shouldRiseAlarm_testData_alarmMeasurement() {
        return Stream.of(
                Arguments.of(500, false),
                Arguments.of(500.000001, true),
                Arguments.of(450, false),
                Arguments.of(850, true)
        );
    }

    
}
