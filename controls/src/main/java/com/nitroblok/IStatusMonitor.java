package com.nitroblok;

public interface IStatusMonitor {
    public double getCurrentPressure(int userId);
    public boolean getIsValveOpened(int userId);
}
