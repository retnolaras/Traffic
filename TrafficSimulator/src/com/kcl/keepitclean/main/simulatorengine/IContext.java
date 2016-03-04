package com.kcl.keepitclean.main.simulatorengine;

import com.kcl.keepitclean.main.vehicle.Vehicle;

public interface IContext {
	
	public void moveVehicle(int roadIndex, int laneIndex, int sectionIndex, 
			Vehicle vehicle, int numOfLanes);
	
}
