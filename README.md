Space Station Life Support Simulation System
For now; the simulation is improvable, but the logic is reasonable.

The simulation works on the balance of oxygen and battery system.
As oxygen is consumed, the level goes down and when it hits 19%, the oxygen generators are turned on using the energy in the battery.
When enough oxygen is generated (the battery level will go down), the generators will be turned off and solar panels will be turned on to charge the battery.

If the battery or oxygen level is under a specific limit, the station status will be changed to "WARNING" or "EMERGENCY".
Depending on the station status, energy taken from non-vital systems in the station will be saved for charging the battery.
