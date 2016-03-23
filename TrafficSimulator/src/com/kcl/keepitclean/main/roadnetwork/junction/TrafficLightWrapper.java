package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.kcl.keepitclean.main.policy.Policy;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;

public class TrafficLightWrapper extends TimerTask implements Junction {
	
	private final Junction junctionWrappedByThisTrafficLight;
	
	private final int redTime;
	private final int greenTime;
	
	private List<Point> greenList;
	private List<Point> redList;

	public TrafficLightWrapper(Junction junction, Policy policy) {
		this.junctionWrappedByThisTrafficLight = junction;
		this.redTime = policy.getRedTrafficLightTime();
		this.greenTime = policy.getGreenTrafficLightTime();
		
		greenList = new ArrayList<Point>();
		redList = new ArrayList<Point>();
		
		
	}
	
	@Override
	public List<LaneSection> produceRoute(Point endCoordinateOfCurrentRoad, Point startCoordinateOfNextRoad) {
		for (Point point : greenList) {
			if (point.equals(endCoordinateOfCurrentRoad)) {
				return junctionWrappedByThisTrafficLight.produceRoute(endCoordinateOfCurrentRoad, startCoordinateOfNextRoad);
			}
		}
		return null;
	}
	
	public void changeLights() {
		
	}

	@Override
	public List<Point> getCoordinates() {
		return junctionWrappedByThisTrafficLight.getCoordinates();
	}

	@Override
	public void run() {
		
		
		
	}

}
