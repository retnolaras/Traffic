package com.kcl.keepitclean.main.simulatorengine;

import com.kcl.keepitclean.main.vehicle.Position;
import com.kcl.keepitclean.main.vehicle.Vehicle;

public interface IContext {
	
//	public void moveVehicle(int roadIndex, int laneIndex, int sectionIndex, 
//			Vehicle vehicle, int numOfLanes);

	void moveVehicle(int roadIndex, int laneIndex, int sectionIndex, Vehicle vehicle, int fromThisLane,
			int toThisLane);
	
	void moveVehicle(Vehicle vehicle, Position oldPos, Position newPos);
}
