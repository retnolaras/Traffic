package com.kcl.keepitclean.main.simulatorengine;

import java.util.List;

import com.kcl.keepitclean.main.vehicle.Vehicle;

public class SimulationData {

	private int vehicleCounter;
    private SimulatorEngine simulation;
    private List<Vehicle> vehicleList;
    private List<Integer> speed;

	public SimulationData() {
		vehicleCounter = simulation.getVehicleCounter();
		vehicleList = simulation.getVehicleList();
		//speed = vehicleList.accerelartion
		
	}

}
