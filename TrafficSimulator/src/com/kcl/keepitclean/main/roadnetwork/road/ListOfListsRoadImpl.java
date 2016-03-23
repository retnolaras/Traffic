package com.kcl.keepitclean.main.roadnetwork.road;

import java.awt.Point;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
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
	private int index=0;
	private int numberOfLanes;
	private int speedLimit;
	private Point startCoordinate;
	private Point endCoordinate;
	private Junction startJunction= null; //the junction at the start of road
	private Junction endJunction =null; //the junction at the end of road
	private boolean hasJunction=false;

	private Junction endOfRoad;
        private Orientation orientation;
        private Point junctionStartCoordinates;
        private Point junctionEndCoordinates;


	public ListOfListsRoadImpl(int length, int numberOfLanes) {
		this.length = length;
		this.numberOfLanes = numberOfLanes;
	}

	@Override
	public int getLengthOfRoad() {
		return length;
	}

        @Override
	public Point getJuctionStartCoordinates() {
		return junctionStartCoordinates;
	}

        public void setJuctionStartCoordinates(Point coordinates) {
		this.junctionStartCoordinates = coordinates;

	}

         @Override
	public Point getJuctionEndCoordinates() {
		return junctionEndCoordinates;
	}

        public void setJuctionEndCoordinates(Point coordinates) {
		this.junctionEndCoordinates = coordinates;
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
	public void setStartCoordinate(Point point) {
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

	/*
	 * Getter and setter for the Junction that is at the end of each Road Object
	 */
	public Junction getEndOfRoad() {
		return endOfRoad;
	}

	public void setEndOfRoad(Junction endOfRoad) {
		this.endOfRoad = endOfRoad;
	}

        public void setOrientation(Orientation orientation){
            this.orientation = orientation;
        }

        public Orientation getOrientation(){
            return this.orientation;
        }

		public Junction getStartJunction() {
			return startJunction;
		}

		public void setStartJunction(Junction startJunction) {
			this.startJunction = startJunction;
			setHasJunction(true);
		}

		public Junction getEndJunction() {
			return endJunction;
		}

		public void setEndJunction(Junction endJunction) {
			this.endJunction = endJunction;
			this.setHasJunction(true);

		}

		public boolean hasJunction() {
			if (endJunction== null) return false;
			else return true;
		}

		private void setHasJunction(boolean hasJunction) {
			this.hasJunction = hasJunction;
		}

		@Override
		public int getIndex() {
			return index;
		}

		@Override
		public void setIndex(int a) {
			index=a;
		}
}
