/*
* Defines car position based on Lane, Road and LaneSection Indexes 
*/
package com.kcl.keepitclean.main.vehicle;

import com.kcl.keepitclean.main.utils.Constant;

public class Position {
	private int road = 0;
	private int lane = 0;
	private int laneSection = 0;
	private int mode = Constant.MOVE_IN_ROAD;
	
	public Position() {
	}

	public Position(int road, int lane, int laneSection) {
		this.road = road;
		this.lane = lane;
		this.laneSection = laneSection;
	}
	
	public Position(int mode, int road, int lane, int laneSection) {
		this.road = road;
		this.lane = lane;
		this.laneSection = laneSection;
		this.mode = mode;
	}

	public void setRoad(int x) {
		road = x;
	}

	public void setLane(int x) {
		lane = x;
	}

	public void setLaneSection(int x) {
		laneSection = x;
	}

	public int getRoad() {
		return road;
	}

	public int getLane() {
		return lane;
	}

	public int getLaneSection() {
		return laneSection;
	}
	
	/**
	 * 
	 * @param road
	 * @param lane
	 * @param laneSection
	 */
	public void update(int road, int lane, int laneSection) {
		this.road = road;
		this.lane = lane;
		this.laneSection = laneSection;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}