package com.kcl.keepitclean.main.roadnetwork;

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
	
	/*
	 * method for returning whether or not a Vehicle currently occupies this 
	 * section of road.
	 * 
	 * @return true if there is a Vehicle on the section. False if otherwise
	 */
	public boolean hasVehicleOnSeciton();

}
