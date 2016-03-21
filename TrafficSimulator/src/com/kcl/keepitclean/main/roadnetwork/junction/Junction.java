package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;

public interface Junction {

	public List<LaneSection> produceRoute(Point roadEnteringCoord, Point roadLeavingCoord);
	public List<Point> getCoordinates();
        public Point getRandomExitPoint();
        

}
