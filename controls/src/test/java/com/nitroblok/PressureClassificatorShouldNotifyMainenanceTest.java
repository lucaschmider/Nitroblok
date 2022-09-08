package com.nitroblok;

import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nitroblok.implementations.PressureClassificator;

public class PressureClassificatorShouldNotifyMainenanceTest {
    @ParameterizedTest(name ="{index} => pressure={0}, expected={1}")
    @MethodSource("shouldNotifyMaintenanceInitial_Source")
    void shouldNotifyMaintenance_testIfNotifyMaintenanceInitial(double pressure, boolean expected) {
        IPressureClassificator pressureClassificator = new PressureClassificator();
        boolean result = pressureClassificator.shouldNotifyMaintenance(pressure);
        assertThat(result, IsEqual.equalTo(expected));
    }
    static Stream<Arguments> shouldNotifyMaintenanceInitial_Source() {
        return Stream.of(
                Arguments.of(49.999, false),
                Arguments.of(50, true),
                Arguments.of(180, true),
                Arguments.of(180.001, false),
                Arguments.of(219.999, false),
                Arguments.of(220, true),
                Arguments.of(300, true),
                Arguments.of(300.001, true),
                Arguments.of(551, true)
        );
    }

    @ParameterizedTest(name ="{index} => initPressure={0}, measuredPressure={1}, expected={2}")
    @MethodSource("shouldNotifyMaintenance_Source")
    void shouldNotifyMaintenance_testIfNotifyMaintenance(double initPressure, double measuredPressure, boolean expected) {
        final IPressureClassificator pressureClassificator = new PressureClassificator();
        pressureClassificator.shouldNotifyMaintenance(initPressure);
        final boolean result = pressureClassificator.shouldNotifyMaintenance(measuredPressure);
        assertThat(result, IsEqual.equalTo(expected));
    }
    static Stream<Arguments> shouldNotifyMaintenance_Source() {
        return Stream.of(
            Arguments.of(50, 49.9999, false),
            Arguments.of(49.9999, 50, true),
            Arguments.of(50, 180, false),
            Arguments.of(180, 180.001, false),
            Arguments.of(180.001, 180, true),
            Arguments.of(180.001, 219.999, false),
            Arguments.of(219.999, 220, true),
            Arguments.of(220, 300, false),
            Arguments.of(300, 300.001, true),
            Arguments.of(300.001, 550, false),
            Arguments.of(300.001, 55, true)

        );
    }
}
