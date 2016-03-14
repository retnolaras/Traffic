package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;

public interface Junction {

	
	public List<LaneSection> produceRoute(Point endCoordinateOfCurrentRoad, Point startCoordinateOfNextRoad);
	public List<Point> getCoordinates();
}
