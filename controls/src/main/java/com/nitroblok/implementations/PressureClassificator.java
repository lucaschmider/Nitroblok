package com.nitroblok.implementations;

import com.nitroblok.IPressureClassificator;

public class PressureClassificator implements IPressureClassificator {

    @Override
    public boolean shouldCreateLog(double pressure) {
        // TODO Auto-generated method stub
        return false;
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
