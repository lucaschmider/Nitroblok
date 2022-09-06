package com.nitroblok;

import com.nitroblok.exceptions.InsufficientPermissionsException;

public interface IStatusMonitor {
    public double getCurrentPressure(int userId) throws InsufficientPermissionsException;

    public boolean getIsValveOpened(int userId) throws InsufficientPermissionsException;
}
