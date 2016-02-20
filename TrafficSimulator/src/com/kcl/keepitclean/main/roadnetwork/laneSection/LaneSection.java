package com.kcl.keepitclean.main.roadnetwork.laneSection;

import com.kcl.keepitclean.main.vehicle.Vehicle;

/**
 * 
 * 
 * @author igalna
 * 
 * LaneSection is the base object of a road network.
 *
 */

public interface LaneSection {
	
	public boolean isOpenForward();
	public boolean isOpenLeft();
	public boolean isOpenRight();
	public boolean isOpeanBackward();
	
	/**
	 * method for returning whether or not a Vehicle currently occupies this 
	 * section of road.
	 * 
	 * @return true if there is a Vehicle on the section. False if otherwise
	 */
	public boolean hasVehicleOnSeciton();

	/**
	 * 
	 * method for putting a Vehicle on this section of lane.
	 * once this Section is occupied by a Vehicle, other Vehicles will not be able to position themselves on it.
	 * 
	 * @param the Vehicle type occupying this LaneSection
	 * 
	 */
	public void putVehicleOnSection(Vehicle vehicle);
	
	/**
	 * 
	 * once a vehicle moves off a LaneSection it is then free to be occupied by another Vehicle
	 * this method is to remove the pointer to the vehicle once it has left the Section
	 * 
	 */
	public void removeVehicleFromSection();
}
