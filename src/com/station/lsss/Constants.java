package com.station.lsss;

public class Constants {

    //oxygen constants
    public static final double ideal_o2 = 21.0;
    public static final double min_o2 = 19.0;
    public static final double max_o2 = 23.0;
    // power constants
    public static final double battery_max = 100.0;
    public static final double critical_energy = 20.0;
    public static final double low_energy = 40.0;

    //power savings
    public static final double low_power_savings = 0.08;
    public static final double emergency_power_savings = 0.18;

    // produce/consume rates
    public static final double o2_consume_rate = 0.01;
    public static final double o2_produce_rate = 0.03;
    public static final double solar_charge_rate = 0.05;
    public static final double generator_power_consume = 0.15;

    public enum StationStatus {
        NORMAL, WARNING, EMERGENCY
    }
}