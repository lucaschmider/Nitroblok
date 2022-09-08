package com.nitroblok.implementations;

import com.nitroblok.IPressureClassificator;

public class PressureClassificator implements IPressureClassificator {

    private double logVal = 200.0;
    private double logVol = 5.0;

    @Override
    public boolean shouldCreateLog(double pressure, double voltage) {

        boolean previousPressureIsAccectable = ((logVal > 180) && (logVal < 220)) ? true : false;
        boolean previousPressureIsCriticallyLow = (logVal < 50) ? true : false;
        boolean previousPressureIsLow = ((logVal >= 50) && (logVal <= 180)) ? true : false;
        boolean previousPressureIsHigh = ((logVal >= 220) && (logVal <= 300)) ? true : false;
        boolean previousPressureisCriticallyHigh = ((logVal > 300) && (logVal <= 500)) ? true : false;
        boolean previousPressureisHazardValue = (logVal > 500) ? true : false;

        boolean pressureIsAccectable = ((pressure > 180) && (pressure < 220)) ? true : false;
        boolean pressureIsCriticallyLow = (pressure < 50) ? true : false;
        boolean pressureIsLow = ((pressure >= 50) && (pressure <= 180)) ? true : false;
        boolean pressureIsHigh = ((pressure >= 220) && (pressure <= 300)) ? true : false;
        boolean pressureIsCriticallyHigh = ((pressure > 300) && (pressure <= 500)) ? true : false;
        boolean pressureisHazardValue = (pressure > 500) ? true : false;

        boolean previousVoltageIsAcceptable = (logVol >= 5) ? true : false;
        boolean voltageIsAcceptable = (voltage >= 5) ? true : false;

        this.logVal = pressure;
        this.logVol = voltage;

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
        boolean previousVoltageIsAcceptable = (logVol >= 5) ? true : false;
        boolean previousPressureIsAcceptable = ((logVal >= 50) && (logVal <= 300)) ? true : false;

        boolean voltageIsAcceptable = (voltage >= 5) ? true : false;
        boolean pressureIsAcceptable = ((pressure >= 50) && (pressure <= 300)) ? true : false;
        
        this.logVal = pressure;
        this.logVol = voltage;

        if(previousVoltageIsAcceptable == true && voltageIsAcceptable == false) return true;
        if(previousPressureIsAcceptable == true && pressureIsAcceptable == false) return true;

        return false;
    }

    @Override
    public boolean shouldNotifyPlantLeader(double pressure) {
        boolean previousPressureIsCriticallyLow = (logVal < 50.0) ? true : false;
        boolean previousPressureIsLow = ((logVal >= 50.0) && (logVal <= 180.0)) ? true : false;
        boolean previousPressureIsHigh = ((logVal >= 220.0) && (logVal <= 300.0)) ? true : false;
        boolean previousPressureisCriticallyHigh = (logVal > 300.0) ? true : false;
        
        boolean pressureIsCriticallyLow = (pressure < 50.0) ? true : false;
        boolean pressureIsLow = ((pressure >= 50.0) && (pressure <= 180.0)) ? true : false;
        boolean pressureIsHigh = ((pressure >= 220.0) && (pressure <= 300.0)) ? true : false;
        boolean pressureIsCriticallyHigh = (pressure > 300.0) ? true : false;

        this.logVal = pressure;

        if(pressureIsCriticallyLow && !previousPressureIsCriticallyLow) return true;
        if(pressureIsLow && !previousPressureIsLow) return true;
        if(pressureIsHigh && !previousPressureIsHigh) return true;
        if(pressureIsCriticallyHigh && !previousPressureisCriticallyHigh) return true;
        return false;
    }

    @Override
    public boolean shouldNotifyMaintenance(double pressure) {
        boolean previousPressureIsLow = ((logVal >= 50) && (logVal <= 180)) ? true : false;
        boolean previousPressureIsHigh = ((logVal >= 220) && (logVal <= 300)) ? true : false;
        boolean previousPressureIsCriticallyHigh = (logVal > 300) ? true : false;
        
        boolean pressureIsAcceptable = ((pressure > 180) && (pressure < 220)) ? true : false;
        boolean pressureIsLow = ((pressure >= 50) && (pressure <= 180)) ? true : false;
        boolean pressureIsHigh = ((pressure >= 220) && (pressure <= 300)) ? true : false;
        boolean pressureIsCriticallyHigh = (pressure > 300) ? true : false;

        this.logVal = pressure;

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
