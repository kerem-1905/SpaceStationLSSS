package com.station.lsss.controller;

import com.station.lsss.Constants;
import com.station.lsss.subsystems.OxygenSubsystem;
import com.station.lsss.subsystems.PowerSubsystem;

public class LifeSupportController {
    private OxygenSubsystem o2Sys;
    private PowerSubsystem pwrSys;
    private Constants.StationStatus currentStatus;

    public LifeSupportController(OxygenSubsystem o2, PowerSubsystem pwr) {
        this.o2Sys = o2;
        this.pwrSys = pwr;
        this.currentStatus = Constants.StationStatus.NORMAL;
    }

    public void update() {
        double o2 = o2Sys.getO2Level();
        double battery = pwrSys.getBatteryLevel();

        if (o2 < Constants.min_o2 || battery < Constants.critical_energy) {
            currentStatus = Constants.StationStatus.EMERGENCY;
            pwrSys.setEnergySavings(Constants.emergency_power_savings);
        }
        else if (o2 < Constants.min_o2 + 0.5 || battery < Constants.low_energy) {
            currentStatus = Constants.StationStatus.WARNING;
            pwrSys.setEnergySavings(Constants.low_power_savings);
        }
        else {
            currentStatus = Constants.StationStatus.NORMAL;
            pwrSys.setEnergySavings(0.0);
        }

        if (o2 <= Constants.min_o2) {
            o2Sys.setGenerator(true);
            pwrSys.setSolarPanels(false);
        }
        else if (o2 >= Constants.max_o2) {
            o2Sys.setGenerator(false);
            pwrSys.setSolarPanels(true);
        }

        if (o2Sys.isGeneratorRunning()) {
            boolean powerAvailable = pwrSys.requestPower(Constants.generator_power_consume);
            if (!powerAvailable) {
                o2Sys.setGenerator(false);
            }
        }
    }

    public void displayStatus() {
        System.out.println("------------------------------------");
        System.out.println("STATION STATUS: " + currentStatus);
        o2Sys.logData();
        pwrSys.logData();
    }
}