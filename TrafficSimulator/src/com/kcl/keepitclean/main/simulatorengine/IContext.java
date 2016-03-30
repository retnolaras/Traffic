package com.kcl.keepitclean.main.simulatorengine;

import java.awt.Point;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.vehicle.Position;
import com.kcl.keepitclean.main.vehicle.Vehicle;

public interface IContext {
	public Point moveVehicle(Vehicle vehicle, Position newPos);

	public List<Road> getRoadList();
	
	public List<Vehicle> getVehicleList();
	
	public List<Junction> getJunctionList();

    public List<TrafficLight> getTrafficLightList();
    
    public SimulationData getSimulationData();
}
