package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;

public class TrafficLightWrapper implements Junction {
	
	private Junction junctionWrappedByThisTrafficLight;

	@Override
	public List<LaneSection> produceRoute(Point endCoordinateOfCurrentRoad, Point startCoordinateOfNextRoad) {
		if (greenLight){
			return junctionWrappedByThisTrafficLight.produceRoute(endCoordinateOfCurrentRoad, startCoordinateOfNextRoad);
		}
	}
	
	public void changeLights() {
		
	}

}
