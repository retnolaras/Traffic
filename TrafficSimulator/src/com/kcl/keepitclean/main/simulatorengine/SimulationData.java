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

	public double calculateTrafficEstimation(int numberofvehicle, int totallanes, int sessionDuration) {
		int numberOfVehiclePerDay = numberofvehicle * 86400 / sessionDuration;
		double estimation = numberOfVehiclePerDay * (totallanes * 0.003) * 365;
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
	
	public double convertSpeed(double avg){
		avg = ((avg*3/(0.33))*3.6);
		return avg;		
	}
	
	public int convertSessionDuration(int iteration){
		iteration = iteration/3;
		return iteration;
	}
	
	public int getVehicleCounter() {
		return vehicleCounter;
	}
	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}
	public double getAverageSpeed() {
		return convertSpeed(avgSpeed);
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
		return sessionDuration ;
	}
	
	public void setSimulationData(SimulatorEngine simulation){
		vehicleCounter = simulation.getVehicleCounter();
		vehicleList = simulation.getVehicleList();
		speed = simulation.getSpeedList();
		avgSpeed = calculateAverageSpeed(speed);
		roadList = simulation.getContext().getRoadList();
		for(int i=0; i< roadList.size(); i++)
		  {
			totalLanes += roadList.get(i).getNumberOfLanes();  
		  }		
		
		sessionDuration = convertSessionDuration(simulation.getIteration());
		trafficEstimation = calculateTrafficEstimation(vehicleCounter, totalLanes, sessionDuration);
		successVehicle = simulation.getSuccessVehicle();
		
	}

}
