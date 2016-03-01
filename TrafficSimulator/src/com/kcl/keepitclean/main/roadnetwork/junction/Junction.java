package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.util.Set;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;

public interface Junction {

	
	public Set<LaneSection> produceRoute(Point endCoordinateOfCurrentRoad, Point startCoordinateOfNextRoad);
}
