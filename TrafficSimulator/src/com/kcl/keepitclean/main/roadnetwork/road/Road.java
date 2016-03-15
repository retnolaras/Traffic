package com.kcl.keepitclean.main.roadnetwork.road;

import java.awt.Point;

/**
 * 
 * @author igalna
 * 
 * A road is the thing in a traffic simulation in which Vehicles move along.
 * It is made up of lanes, which are themselves made up of lane sections.
 * Roads are connected through node Junctions.
 * A road can end in a junction or another road.
 *
 */
public interface Road {

	public int getLengthOfRoad();
	public int getNumberOfLanes();
	public int getSpeedLimit();
	public Point getStartCoordinates();
	public Point getEndCoordinates();
}
