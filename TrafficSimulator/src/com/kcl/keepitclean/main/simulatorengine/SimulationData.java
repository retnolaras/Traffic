package com.kcl.keepitclean.main.simulatorengine;

import java.util.List;

import com.kcl.keepitclean.main.vehicle.Vehicle;

public class SimulationData {

	private int vehicleCounter;
    private SimulatorEngine simulation;
    private List<Vehicle> vehicleList;
    private List<Integer> speed;
    private double avgSpeed;

	public SimulationData() {
		vehicleCounter = simulation.getVehicleCounter();
		vehicleList = simulation.getVehicleList();
		speed = simulation.getSpeedList();
		avgSpeed = calculateAverage(speed);
	}

	private double calculateAverage(List<Integer> speedavg) {
		Integer sum = 0;
		  if(!speedavg.isEmpty()) {
		    for (Integer mark : speedavg) {
		        sum += mark;
		    }
		    return sum.doubleValue() / speedavg.size();
		  }
		  return sum;
	}

}
