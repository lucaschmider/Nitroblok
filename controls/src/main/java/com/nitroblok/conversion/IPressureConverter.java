package com.nitroblok.conversion;

public interface IPressureConverter {
    /**
     * Calculates the pressure in bar for a given voltage
     * @param voltage
     * @return
     */
    public double calculatePressure(double voltage);
}
