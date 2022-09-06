package com.nitroblok.implementations;

import com.nitroblok.IStatusMonitor;
import com.nitroblok.IStatusMonitorRepository;
import com.nitroblok.exceptions.InsufficientPermissionsException;

public class StatusMonitor implements IStatusMonitor {

    private final IStatusMonitorRepository _repository;

    public StatusMonitor(IStatusMonitorRepository repository) {
        _repository = repository;
    }

    @Override
    public double getCurrentPressure(int userId) throws InsufficientPermissionsException {
        final boolean hasPermissions = _repository.canUserAccessPressure(userId);
        if (!hasPermissions)
            throw new InsufficientPermissionsException();

        return _repository.getCurrentPressure();
    }

    @Override
    public boolean getIsValveOpened(int userId) throws InsufficientPermissionsException {
        final boolean hasPermissions = _repository.canUserAccessValveState(userId);
        if (!hasPermissions)
            throw new InsufficientPermissionsException();

        return _repository.getIsValveOpened();
    }

}
