package com.station.lsss.subsystems;

import com.station.lsss.Constants;
import com.station.lsss.ISubsystem;

public class OxygenSubsystem implements ISubsystem {
    private double o2Level = Constants.ideal_o2;
    private boolean generatorRunning = false;

    @Override
    public void periodic() {
        o2Level -= Constants.o2_consume_rate;

        if (generatorRunning) {
            o2Level += Constants.o2_produce_rate;
        }
    }

    public void setGenerator(boolean state) {
        this.generatorRunning = state;
    }

    public boolean isGeneratorRunning() {
        return generatorRunning;
    }

    public double getO2Level() {
        return o2Level;
    }

    @Override
    public void logData() {
        System.out.printf("[LIFE SUPPORT] O2 Level: %.2f%% | Generator: %s%n",
                o2Level, generatorRunning ? "ON" : "OFF");
    }
}