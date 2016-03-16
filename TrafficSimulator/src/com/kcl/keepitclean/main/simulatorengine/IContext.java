package com.kcl.keepitclean.main.simulatorengine;

import java.awt.Point;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.vehicle.Position;
import com.kcl.keepitclean.main.vehicle.Vehicle;

public interface IContext {
	
//	public void moveVehicle(int roadIndex, int laneIndex, int sectionIndex, 
//			Vehicle vehicle, int numOfLanes);

	void moveVehicle(int roadIndex, int laneIndex, int sectionIndex, Vehicle vehicle, int fromThisLane,
			int toThisLane);
	
	public Point moveVehicle(Vehicle vehicle, Position oldPos, Position newPos);
	
	public List<Road> getRoadList();
	
	public List<Vehicle> getVehicleList();
	
	public List<Junction> getJunctionList();

    public List<TrafficLight> getTrafficLightList();
	
}
