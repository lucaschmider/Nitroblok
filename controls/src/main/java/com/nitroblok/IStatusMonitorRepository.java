package com.nitroblok;

public interface IStatusMonitorRepository {
    public boolean canUserAccessPressure(int userId);
    public boolean canUserAccessValveState(int userId);

    public double getCurrentPressure();
    public boolean getIsValveOpened();
}
