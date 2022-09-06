package com.nitroblok.implementations;

import com.nitroblok.IStatusMonitor;
import com.nitroblok.IStatusMonitorRepository;

public class StatusMonitor implements IStatusMonitor {

    private final IStatusMonitorRepository _repository;

    StatusMonitor(IStatusMonitorRepository repository) {
        _repository = repository;
    }

    @Override
    public double getCurrentPressure() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean getIsValveOpened() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
