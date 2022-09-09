package com.nitroblok;

import com.nitroblok.implementations.PressureClassificator;
import java.util.concurrent.TimeUnit;

public class App 
{
    public static void main( String[] args )
    {
        PressureClassificator pressureClassificator = new PressureClassificator();

        while(true) {
            double mockedPressure = Math.round((Math.random() * 600) * 100.0) / 100.0;
            double mockedVoltage = Math.round((Math.random() * 24) * 100.0) / 100.0;

            if(pressureClassificator.shouldCreateLog(mockedPressure, mockedVoltage)) {
                System.out.println("Log-Eintrag wird erstellt - Druck: " + mockedPressure + " Bar, Spannung: " + mockedVoltage + " Volt");
            }
            if(pressureClassificator.shouldNotifyMaintenance(mockedPressure)) {
                System.out.println("Wartungsarbeiter werden benachrichtigt - Druck: " + mockedPressure + " Bar");
            }
            if(pressureClassificator.shouldNotifyPlantLeader(mockedPressure)) {
                System.out.println("Betrtiebsleiter wird/werden benachrichtigt - Druck: " + mockedPressure + " Bar");
            }
            if(pressureClassificator.shouldRing(mockedPressure, mockedVoltage)) {
                System.out.println("Akkustisches Signal");
            }
            if(pressureClassificator.shouldRiseAlarm(mockedPressure)) {
                System.out.println("Alarm/Evakuierung");
            }

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
