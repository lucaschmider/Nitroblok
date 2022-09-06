package com.nitroblok.notification;

import java.util.concurrent.Future;

import com.nitroblok.models.NotificationAction;

public interface INotificationPublisher {
    /**
     * Notifies the corresponding parties about the pressure
     * @param actions
     * @param currentPressure the pressure in bar
     * @return
     */
    public Future<Void> publishNotifications(NotificationAction[] actions, double currentPressure);
}
