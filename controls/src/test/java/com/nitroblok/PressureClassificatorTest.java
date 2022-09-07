package com.nitroblok;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.nitroblok.implementations.PressureClassificator;

@RunWith(Theories.class)
public class PressureClassificatorTest {

    @DataPoints("initial values")
    public static Object[] initValues() {
        return new Object[][]{
            {45.0, true},
            {120.0, true},
            {186.0, false},
            {248.0, true},
            {310.0, true},
            {580.0, true},
        };
    }

    @DataPoints("inside values")
    public static Object[] insideValues() {
        return new Object[][]{
            {49.99999, 48.999, false},
            {120.0, 100.0, false},
            {186.0, 221.999999, false}, //
            //{230.0, 289.0, false},
            //{330.0, 400.0, false},
            //{503.0, 700.0, false},
        };
    }

    @DataPoints("boarder values")
    public static Object[] boarderValues() {
        return new Object[][]{
            {55.0, 49.99999, true},
            {45.0, 120.0, true},
            {186.0, 170.0, true},
            {170.0, 186.0, true},
            {257.0, 186.0, true},
            {186.0, 257.0, true},
            {310.0, 276.0, true},
            {276.0, 310.0, true},
            {510.0, 310.0, true},
            {310.0, 510.0, true},
        };
    }

    @Theory
    public void shouldCreateLog_a(@FromDataPoints("inside values") Object[] values) {
        final IPressureClassificator pressureClassificator = new PressureClassificator();
        pressureClassificator.shouldCreateLog((double) values[0]);
        final boolean result = pressureClassificator.shouldCreateLog((double) values[1]);
        assertThat(result, IsEqual.equalTo(values[2]));
    }

    @Theory
    public void shouldCreateLog_b(@FromDataPoints("boarder values") Object[] values) {
        final IPressureClassificator pressureClassificator = new PressureClassificator();
        pressureClassificator.shouldCreateLog((double) values[0]);
        final boolean result = pressureClassificator.shouldCreateLog((double) values[1]);
        assertThat(result, IsEqual.equalTo(values[2]));
    }


    @Theory
    public void shouldCreateLog_c(@FromDataPoints("initial values") Object[] values) {
        IPressureClassificator pressureClassificator = new PressureClassificator();
        boolean result = pressureClassificator.shouldCreateLog((double) values[0]);
        assertThat(result, IsEqual.equalTo(values[1]));
    }


    
}