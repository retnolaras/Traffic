package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.util.Set;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.Road;

public class PrePlannedRouteJunction implements Junction {

	private Set<Road> roadsLeavingJunction;
	
	public PrePlannedRouteJunction(Set<Road> roadsEnteringThisJunction ) {
		
	}
	
	
	@Override
	public Set<LaneSection> produceRoute(Point endCoordinateOfCurrentRoad, Point startCoordinateOfNextRoad) {
		return null;
	}
}
