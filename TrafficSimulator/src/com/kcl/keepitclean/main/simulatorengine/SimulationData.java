package com.kcl.keepitclean.main.simulatorengine;

import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.vehicle.Vehicle;

public class SimulationData {

	private int vehicleCounter;
    private List<Vehicle> vehicleList;
    private List<Integer> speed;
    private List<Road> roadList;
    private double avgSpeed;
    private double trafficEstimation;
    private int totalLanes = 0;
	private int successVehicle;
	private int sessionDuration;
	
	public SimulationData() {
		
	}

	private double calculateTrafficEstimation(int numberofvehicle, int totallanes) {
		double estimation = numberofvehicle * (totallanes * 0.003) * 365;
		return estimation;
	}

	public double calculateAverageSpeed(List<Integer> speedavg) {
		Integer sum = 0;
		  if(!speedavg.isEmpty()) {
		    for (Integer mark : speedavg) {
		        sum += mark;
		    }
		    return sum.doubleValue() / speedavg.size();
		  }
		  return sum;
	}
	
	public int getVehicleCounter() {
		return vehicleCounter;
	}
	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}
	public double getAverageSpeed() {
		return avgSpeed;
	}
	public double getTrafficEstimation() {
		return trafficEstimation;
	}	
	public int getTotalLanes() {
		return totalLanes;
	}	
	public int getSuccessVehicles(){
		return successVehicle;
	}	
	public int getSessionDuration(){
		return sessionDuration;
	}
	
	public void setSimulationData(SimulatorEngine simulation){
		vehicleCounter = simulation.getVehicleCounter();
		vehicleList = simulation.getVehicleList();
//		speed = simulation.getSpeedList();
//		avgSpeed = calculateAverageSpeed(speed);
		roadList = simulation.getContext().getRoadList();
		for(int i=0; i< roadList.size(); i++)
		  {
			totalLanes += roadList.get(i).getNumberOfLanes();  
		  }		
		trafficEstimation = calculateTrafficEstimation(vehicleCounter, totalLanes);
		sessionDuration = simulation.getIteration();
//		successVehicle = simulation.getSuccessVehicle();
		
	}

}
