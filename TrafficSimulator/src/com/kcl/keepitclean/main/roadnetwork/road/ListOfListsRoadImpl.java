package com.kcl.keepitclean.main.roadnetwork.road;

import java.awt.Point;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;


/**
 * 
 * Implementation of a Road
 * 
 * Uses a Nested List structure to model Roads.
 * 
 * List is used to store List<LaneSection>s. Each nested List<LaneSection> represents a single Lane within a Road.
 * 
 * @author igalna
 *
 */
public class ListOfListsRoadImpl implements Road {

	private List<List<LaneSection>> laneSectionsOfRoad;
	private int length;
	private int numberOfLanes;
	private int speedLimit;
	private Point startCoordinate;
	private Point endCoordinate;
	
	
	public ListOfListsRoadImpl(int length, int numberOfLanes) {
		this.length = length;
		this.numberOfLanes = numberOfLanes;
	}
	
	@Override
	public int getLengthOfRoad() {
		return length;
	}

	@Override
	public int getNumberOfLanes() {
		return numberOfLanes;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public Point getStartCoordinates() {
		return startCoordinate;
	}

	@Override
	public Point getEndCoordinates() {
		return endCoordinate;
	}
	
	/*
	 * 
	 * getter and setter for the ListOfLists which this implementation of Road uses to model a road with some number of lanes
	 * 
	 */
	public List<List<LaneSection>> getLaneSectionsOfRoad() {
		return laneSectionsOfRoad;
	}

	public void setLaneSectionsOfRoad(List<List<LaneSection>> array) {
		this.laneSectionsOfRoad = array;
	}
	
	/*
	 * getter and setter for the start coordinates
	 * 
	 */
	public void setStartCoordinates(Point point) {
		this.startCoordinate = point;
	}
	public Point getStartCoordinate() {
		return this.startCoordinate;
	}
	
	/*
	 * 
	 * getter and setter for the end coordinates
	 * 
	 */
	public void setEndCoordinate(Point point) {
		this.endCoordinate = point;
	}
	public Point getEndCoordinate() {
		return this.endCoordinate;
	}
	
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}
}
