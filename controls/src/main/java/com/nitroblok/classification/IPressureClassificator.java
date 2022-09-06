package com.nitroblok.classification;

import com.nitroblok.models.NotificationAction;

public interface IPressureClassificator {
    /**
     * Decides which notifications should be sent for a given pressure value in bar.
     * @param pressure 
     * @return
     */
    public NotificationAction[] classifyPressure(double pressure);
}
