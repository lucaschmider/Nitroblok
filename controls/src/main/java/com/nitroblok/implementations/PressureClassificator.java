package com.nitroblok.implementations;

import com.nitroblok.IPressureClassificator;

public class PressureClassificator implements IPressureClassificator {

    private double initialPressure = 200.0;
    private double initialVoltage = 5.0;

    @Override
    public boolean shouldCreateLog(double pressure, double voltage) {

        boolean previousPressureIsAccectable = ((initialPressure > 180) && (initialPressure < 220));
        boolean previousPressureIsCriticallyLow = (initialPressure < 50);
        boolean previousPressureIsLow = ((initialPressure >= 50) && (initialPressure <= 180));
        boolean previousPressureIsHigh = ((initialPressure >= 220) && (initialPressure <= 300));
        boolean previousPressureisCriticallyHigh = ((initialPressure > 300) && (initialPressure <= 500));
        boolean previousPressureisHazardValue = (initialPressure > 500);

        boolean pressureIsAccectable = ((pressure > 180) && (pressure < 220));
        boolean pressureIsCriticallyLow = (pressure < 50);
        boolean pressureIsLow = ((pressure >= 50) && (pressure <= 180));
        boolean pressureIsHigh = ((pressure >= 220) && (pressure <= 300));
        boolean pressureIsCriticallyHigh = ((pressure > 300) && (pressure <= 500));
        boolean pressureisHazardValue = (pressure > 500);

        boolean previousVoltageIsAcceptable = (initialVoltage >= 5);
        boolean voltageIsAcceptable = (voltage >= 5);

        this.initialPressure = pressure;
        this.initialVoltage = voltage;

        //check Pressure
        if(pressureIsAccectable != previousPressureIsAccectable) return true;
        if(pressureIsCriticallyLow != previousPressureIsCriticallyLow) return true;
        if(pressureIsLow != previousPressureIsLow) return true;
        if(pressureIsHigh != previousPressureIsHigh) return true;
        if(pressureIsCriticallyHigh != previousPressureisCriticallyHigh) return true;
        if(pressureisHazardValue != previousPressureisHazardValue) return true;

        //check Voltage
        if(voltageIsAcceptable != previousVoltageIsAcceptable) return true;


        return false;
    }

    @Override
    public boolean shouldRing(double pressure, double voltage) {
        boolean previousVoltageIsAcceptable = (initialVoltage >= 5);
        boolean previousPressureIsAcceptable = ((initialPressure >= 50) && (initialPressure <= 300));

        boolean voltageIsAcceptable = (voltage >= 5);
        boolean pressureIsAcceptable = ((pressure >= 50) && (pressure <= 300));
        
        this.initialPressure = pressure;
        this.initialVoltage = voltage;

        if(previousVoltageIsAcceptable == true && voltageIsAcceptable == false) return true;
        if(previousPressureIsAcceptable == true && pressureIsAcceptable == false) return true;

        return false;
    }

    @Override
    public boolean shouldNotifyPlantLeader(double pressure) {
        boolean previousPressureIsCriticallyLow = (initialPressure < 50.0);
        boolean previousPressureIsLow = ((initialPressure >= 50.0) && (initialPressure <= 180.0));
        boolean previousPressureIsHigh = ((initialPressure >= 220.0) && (initialPressure <= 300.0));
        boolean previousPressureisCriticallyHigh = (initialPressure > 300.0);
        
        boolean pressureIsCriticallyLow = (pressure < 50.0);
        boolean pressureIsLow = ((pressure >= 50.0) && (pressure <= 180.0));
        boolean pressureIsHigh = ((pressure >= 220.0) && (pressure <= 300.0));
        boolean pressureIsCriticallyHigh = (pressure > 300.0);

        this.initialPressure = pressure;

        if(pressureIsCriticallyLow && !previousPressureIsCriticallyLow) return true;
        if(pressureIsLow && !previousPressureIsLow) return true;
        if(pressureIsHigh && !previousPressureIsHigh) return true;
        if(pressureIsCriticallyHigh && !previousPressureisCriticallyHigh) return true;
        return false;
    }

    @Override
    public boolean shouldNotifyMaintenance(double pressure) {
        boolean previousPressureIsLow = ((initialPressure >= 50) && (initialPressure <= 180));
        boolean previousPressureIsHigh = ((initialPressure >= 220) && (initialPressure <= 300));
        boolean previousPressureIsCriticallyHigh = (initialPressure > 300);
        
        boolean pressureIsAcceptable = ((pressure > 180) && (pressure < 220));
        boolean pressureIsLow = ((pressure >= 50) && (pressure <= 180));
        boolean pressureIsHigh = ((pressure >= 220) && (pressure <= 300));
        boolean pressureIsCriticallyHigh = (pressure > 300);

        this.initialPressure = pressure;

        if(pressureIsAcceptable == true) return false;
        if(pressureIsLow == true && previousPressureIsLow == false) return true;
        if(pressureIsHigh == true && previousPressureIsHigh == false) return true;
        if(pressureIsCriticallyHigh == true && previousPressureIsCriticallyHigh == false) return true;

        return false;
    }

    @Override
    public boolean shouldRiseAlarm(double pressure) {
        if(pressure > 500.0) return true;
        return false;
    }
    
}
