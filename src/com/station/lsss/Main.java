package com.station.lsss;

import com.station.lsss.controller.LifeSupportController;
import com.station.lsss.subsystems.OxygenSubsystem;
import com.station.lsss.subsystems.PowerSubsystem;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        OxygenSubsystem oxygen = new OxygenSubsystem();
        PowerSubsystem power = new PowerSubsystem();
        LifeSupportController controller = new LifeSupportController(oxygen, power);

        System.out.println("SPACE STATION SYSTEMS STARTING UP...");
        System.out.println("DEPENDING ON THE STATION STATUS, THE NON-VITAL SYSTEMS' ENERGIES WILL EITHER REDUCE OR THE NON-VITAL SYSTEMS WILL BE DISABLED TEMPORARILY");

        while(true) {
            oxygen.periodic();
            power.periodic();
            controller.update();
            controller.displayStatus();

            Thread.sleep(500);
        }
    }
}