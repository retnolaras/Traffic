package com.kcl.keepitclean.main.roadnetwork.laneSection;

import com.kcl.keepitclean.main.vehicle.Vehicle;


/**
 * 
 * @author igalna
 *	
 * Default Implementation for checking if there is a vehicle currently on the section of lane.
 * 
 * also for moving Vehicles on and off the section.
 *
 */
public abstract class AbstractLaneSection implements LaneSection {

	// In any direction a lane can either be open or closed
	private boolean closed = false;
	private boolean open = true;
	
	private Vehicle vehicleOnSection;
	
	@Override
	public boolean hasVehicleOnSeciton() {
		if (vehicleOnSection != null) {
			return true;
		}
		else
			return false;
	}
	
	public boolean getOpen() {
		return open;
	}
	
	public boolean getClosed() {
		return closed;
	}
	
	@Override
	public void putVehicleOnSection (Vehicle vehicle) {
		this.vehicleOnSection = vehicle;
	}
	
	@Override
	public void removeVehicleFromSection() {
		this.vehicleOnSection = null;
	}

}
