package com.nitroblok.implementations;

import com.nitroblok.IPressureClassificator;

public class PressureClassificator implements IPressureClassificator {

    private double logVal = 200.0; //120

    @Override
    public boolean shouldCreateLog(double pressure) {
        if
        (
            (logVal >= 50.0 && pressure < 50.0) || 
            (logVal <= 50.0 && pressure > 50.0) || 
            (logVal > 180.0 && pressure <= 180.0) || 
            (logVal <= 180.0 && pressure > 180.0) ||
            (logVal < 220.0 && pressure >= 220.0) || 
            (logVal >= 220.0 && pressure < 220.0) ||
            (logVal <= 300.0 && pressure > 300) ||   
            (logVal > 300.0 && pressure <= 300.0) || 
            (logVal <= 500.0 && pressure > 500) ||   
            (logVal > 500.0 && pressure <= 500.0) 
        ) {
            this.logVal = pressure;
            return true;
        } else {
            this.logVal = pressure;
            return false;
        }
    }

    @Override
    public boolean shouldRing(double pressure, double voltage) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean shouldNotifyPlantLeader(double pressure) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean shouldNotifyMaintenance(double pressure) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean shouldRiseAlarm(double pressure) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
