package com.nitroblok;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import com.nitroblok.exceptions.InsufficientPermissionsException;
import com.nitroblok.implementations.StatusMonitor;

public class StatusMonitorTest {

    @Test(expected = InsufficientPermissionsException.class)
    public void getCurrentPressure_UserWithoutPermissions_ThrowsInsufficientPermissionsException()
            throws InsufficientPermissionsException {
        // Arrange
        final int callingUserId = 231;
        final double expectedPressure = 200;

        final IStatusMonitorRepository repositoryMock = mock(IStatusMonitorRepository.class);
        when(repositoryMock.getCurrentPressure()).thenReturn(expectedPressure);
        when(repositoryMock.canUserAccessPressure(callingUserId)).thenReturn(false);

        final IStatusMonitor monitor = new StatusMonitor(repositoryMock);

        // Act
        monitor.getCurrentPressure(callingUserId);
    }

    @Test()
    public void getCurrentPressure_UserWithPermissions_ReturnsCorrectPressure()
            throws InsufficientPermissionsException {
        // Arrange
        final int callingUserId = 231;
        final double expectedPressure = 200;

        final IStatusMonitorRepository repositoryMock = mock(IStatusMonitorRepository.class);
        when(repositoryMock.getCurrentPressure()).thenReturn(expectedPressure);
        when(repositoryMock.canUserAccessPressure(callingUserId)).thenReturn(true);

        final IStatusMonitor monitor = new StatusMonitor(repositoryMock);

        // Act
        final double pressure = monitor.getCurrentPressure(callingUserId);

        // Assert
        assertEquals(expectedPressure, pressure, 0);
    }

    @Test(expected = InsufficientPermissionsException.class)
    public void getIsValveOpened_UserWithoutPermissions_ThrowsInsufficientPermissionsException()
            throws InsufficientPermissionsException {
        // Arrange
        final int callingUserId = 231;

        final IStatusMonitorRepository repositoryMock = mock(IStatusMonitorRepository.class);
        when(repositoryMock.canUserAccessPressure(callingUserId)).thenReturn(false);

        final IStatusMonitor monitor = new StatusMonitor(repositoryMock);

        // Act
        monitor.getCurrentPressure(callingUserId);
    }

    @Test()
    public void getIsValveOpened_UserWithPermissions_ReturnsCorrectState() throws InsufficientPermissionsException {
        // Arrange
        final int callingUserId = 231;
        final boolean expectedValveState = true;

        final IStatusMonitorRepository repositoryMock = mock(IStatusMonitorRepository.class);
        when(repositoryMock.getIsValveOpened()).thenReturn(expectedValveState);
        when(repositoryMock.canUserAccessValveState(callingUserId)).thenReturn(true);

        final IStatusMonitor monitor = new StatusMonitor(repositoryMock);

        // Act
        final boolean isValveOpened = monitor.getIsValveOpened(callingUserId);

        // Assert
        assertEquals(expectedValveState, isValveOpened);
    }

    @Test()
    public void getIsValveOpened_UserWithPermissions_CompletesBeforeThreshold()
            throws InsufficientPermissionsException {
        // Arrange
        final int callingUserId = 231;
        final boolean expectedValveState = true;

        final IStatusMonitorRepository repositoryMock = mock(IStatusMonitorRepository.class);
        when(repositoryMock.getIsValveOpened()).thenReturn(expectedValveState);
        when(repositoryMock.canUserAccessValveState(callingUserId)).thenReturn(true);

        final IStatusMonitor monitor = new StatusMonitor(repositoryMock);

        final StopWatch sw = new StopWatch();

        // Act
        sw.start();
        monitor.getIsValveOpened(callingUserId);
        sw.stop();

        // Assert
        final long elapsedMs = sw.getTime();
        assertTrue("Execution time (" + elapsedMs + "ms) should be less than or equal to 10ms", elapsedMs <= 10);
    }

    @Test()
    public void getCurrentPressure_UserWithPermissions_CompletesBeforeThreshold()
            throws InsufficientPermissionsException {
        // Arrange
        final int callingUserId = 231;
        final double expectedPressure = 200;

        final IStatusMonitorRepository repositoryMock = mock(IStatusMonitorRepository.class);
        when(repositoryMock.getCurrentPressure()).thenReturn(expectedPressure);
        when(repositoryMock.canUserAccessPressure(callingUserId)).thenReturn(true);

        final IStatusMonitor monitor = new StatusMonitor(repositoryMock);

        final StopWatch sw = new StopWatch();

        // Act
        sw.start();
        monitor.getCurrentPressure(callingUserId);
        sw.stop();

        // Assert
        final long elapsedMs = sw.getTime();
        assertTrue("Execution time (" + elapsedMs + "ms) should be less than or equal to 10ms", elapsedMs <= 10);
    }

}
