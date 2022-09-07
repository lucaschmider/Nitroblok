package com.nitroblok;

public interface IPressureClassificator {
    public boolean shouldCreateLog(double pressure, double voltage);
    public boolean shouldRing(double pressure, double voltage);
    public boolean shouldNotifyPlantLeader(double pressure);
    public boolean shouldNotifyMaintenance(double pressure);
    public boolean shouldRiseAlarm(double pressure);
}
