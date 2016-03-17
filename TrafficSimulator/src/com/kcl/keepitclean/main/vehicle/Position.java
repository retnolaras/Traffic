/*
* Defines car position based on Lane, Road and LaneSection Indexes 
*/
package com.kcl.keepitclean.main.vehicle;

public class Position{
	private int road = 0;
	private int lane = 0;
	private int laneSection = 0;
	
	public Position(){
		this.road = 0;
		this.lane = 0;
		this.laneSection = 0;
	}
	
	public Position(int road, int lane, int laneSection){
		this.road = road;
		this.lane = lane;
		this.laneSection = laneSection;
	}
	
	public void setRoad(int x){
		road= x;
	}
	
	public void setLane(int x){
		lane=x;
	}
	
	public void setLaneSection(int x){
		laneSection = x;
	}
	
	public int getRoad(){
		return road;
	}
	
	public int getLane(){
		return lane;
	}
	
	public int getLaneSection(){
		return laneSection; 
	}
	
	public void update (int road ,int lane, int  laneSection){
		this.road = road;
		this.lane=lane;
		this.laneSection=laneSection;
	}
	
}