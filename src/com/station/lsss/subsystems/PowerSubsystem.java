package com.station.lsss.subsystems;

import com.station.lsss.Constants;
import com.station.lsss.ISubsystem;

public class PowerSubsystem implements ISubsystem {
    private double batteryLevel = Constants.battery_max;
    private boolean solarPanelsActive = false;
    private double energySavings = 0.0;

    public void setSolarPanels(boolean state) {
        this.solarPanelsActive = state;
    }

    public void setEnergySavings(double amount) {
        this.energySavings = amount;
    }

    @Override
    public void periodic() {
        double totalInflow = energySavings;

        if (solarPanelsActive) {
            totalInflow += Constants.solar_charge_rate;
        }

        if (batteryLevel < Constants.battery_max) {
            batteryLevel = Math.min(batteryLevel + totalInflow, Constants.battery_max);
        }
    }

    public boolean requestPower(double amount) {
        if (batteryLevel >= amount) {
            batteryLevel -= amount;
            return true;
        }
        return false;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public void logData() {
        System.out.printf("[POWER UNIT] Battery: %.2f%% | Solar Panels: %s%n | Savings: %.2f%n",
                batteryLevel, solarPanelsActive ? "ACTIVE" : "OFF", energySavings);
    }
}