package com.nitroblok.implementations;

import com.nitroblok.IPressureClassificator;

public class PressureClassificator implements IPressureClassificator {

    private double previousPressureCreateLog = 200.0;
    private double previousPressureRing = 200.0;
    private double previousPressureNotifyPlantLeader = 200.0;
    private double previousPressureNotifyMaintenance = 200.0;
    private double previousVoltageCreateLog = 5.0;
    private double previousVoltageRing = 5.0;

    @Override
    public boolean shouldCreateLog(double pressure, double voltage) {

        boolean previousPressureIsAccectable = ((previousPressureCreateLog > 180) && (previousPressureCreateLog < 220));
        boolean previousPressureIsCriticallyLow = (previousPressureCreateLog < 50);
        boolean previousPressureIsLow = ((previousPressureCreateLog >= 50) && (previousPressureCreateLog <= 180));
        boolean previousPressureIsHigh = ((previousPressureCreateLog >= 220) && (previousPressureCreateLog <= 300));
        boolean previousPressureisCriticallyHigh = ((previousPressureCreateLog > 300) && (previousPressureCreateLog <= 500));
        boolean previousPressureisHazardValue = (previousPressureCreateLog > 500);

        boolean pressureIsAccectable = ((pressure > 180) && (pressure < 220));
        boolean pressureIsCriticallyLow = (pressure < 50);
        boolean pressureIsLow = ((pressure >= 50) && (pressure <= 180));
        boolean pressureIsHigh = ((pressure >= 220) && (pressure <= 300));
        boolean pressureIsCriticallyHigh = ((pressure > 300) && (pressure <= 500));
        boolean pressureisHazardValue = (pressure > 500);

        boolean previousVoltageIsAcceptable = (previousVoltageCreateLog >= 5);
        boolean voltageIsAcceptable = (voltage >= 5);

        this.previousPressureCreateLog = pressure;
        this.previousVoltageCreateLog = voltage;

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
        boolean previousVoltageIsAcceptable = (previousVoltageRing >= 5);
        boolean previousPressureIsAcceptable = ((previousPressureRing >= 50) && (previousPressureRing <= 300));

        boolean voltageIsAcceptable = (voltage >= 5);
        boolean pressureIsAcceptable = ((pressure >= 50) && (pressure <= 300));
        
        this.previousPressureRing = pressure;
        this.previousVoltageRing = voltage;

        if(previousVoltageIsAcceptable == true && voltageIsAcceptable == false) return true;
        if(previousPressureIsAcceptable == true && pressureIsAcceptable == false) return true;

        return false;
    }

    @Override
    public boolean shouldNotifyPlantLeader(double pressure) {
        boolean previousPressureIsCriticallyLow = (previousPressureNotifyPlantLeader < 50.0);
        boolean previousPressureIsLow = ((previousPressureNotifyPlantLeader >= 50.0) && (previousPressureNotifyPlantLeader <= 180.0));
        boolean previousPressureIsHigh = ((previousPressureNotifyPlantLeader >= 220.0) && (previousPressureNotifyPlantLeader <= 300.0));
        boolean previousPressureisCriticallyHigh = (previousPressureNotifyPlantLeader > 300.0);
        
        boolean pressureIsCriticallyLow = (pressure < 50.0);
        boolean pressureIsLow = ((pressure >= 50.0) && (pressure <= 180.0));
        boolean pressureIsHigh = ((pressure >= 220.0) && (pressure <= 300.0));
        boolean pressureIsCriticallyHigh = (pressure > 300.0);

        this.previousPressureNotifyPlantLeader = pressure;

        if(pressureIsCriticallyLow && !previousPressureIsCriticallyLow) return true;
        if(pressureIsLow && !previousPressureIsLow) return true;
        if(pressureIsHigh && !previousPressureIsHigh) return true;
        if(pressureIsCriticallyHigh && !previousPressureisCriticallyHigh) return true;
        return false;
    }

    @Override
    public boolean shouldNotifyMaintenance(double pressure) {
        boolean previousPressureIsLow = ((previousPressureNotifyMaintenance >= 50) && (previousPressureNotifyMaintenance <= 180));
        boolean previousPressureIsHigh = ((previousPressureNotifyMaintenance >= 220) && (previousPressureNotifyMaintenance <= 300));
        boolean previousPressureIsCriticallyHigh = (previousPressureNotifyMaintenance > 300);
        
        boolean pressureIsAcceptable = ((pressure > 180) && (pressure < 220));
        boolean pressureIsLow = ((pressure >= 50) && (pressure <= 180));
        boolean pressureIsHigh = ((pressure >= 220) && (pressure <= 300));
        boolean pressureIsCriticallyHigh = (pressure > 300);

        this.previousPressureNotifyMaintenance = pressure;

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
