package com.kcl.keepitclean.main.simulatorengine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.road.Junction;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.vehicle.Vehicle;

public class Context implements IContext {
	private List<Vehicle> vehicleList;
	private List<Junction> junctionList;
	
	//Remember a single Road is a ListOfListsRoadImpl
	//This List represents the entire network road
	//and it is a list of ListOfListsRoadImpl objects.
	private List<Road> roadList;
	
	public Context(List<Road> roadList) {
		this.roadList = roadList;
		vehicleList =  new ArrayList<Vehicle>();
	}
	
	public boolean addRoad(Road road){
		return roadList.add(road);
	}
	
	public List<Road> getRoadList(){
		 return roadList;
	}
	
	public List<Road> getRoadList(List<Road> roadList){
		 return roadList;
	}	
	
	public boolean addJunction(Junction junction){
		return junctionList.add(junction);
	}
	
	public List<Junction> getJunctionList(){
		 return junctionList;
	}
	
	public boolean addVehicle(Vehicle vehicle){
		boolean res = vehicleList.add(vehicle);
		return res;
	}
	
	/*public Set<Vehicle> getVehicleSet(){
		 return vehicleSet;
	}*/
		
	@Override
	public void moveVehicle(int roadIndex, int laneIndex, int sectionIndex, 
							Vehicle vehicle, int lanesToMove) {
		
		Road road = roadList.get(roadIndex);
		
		//need to find the start point first
		double x = road.getStartCoordinates().getX();
		for(int i = 0; i < sectionIndex; i++){
			x += 50;
		}
		Point p = new Point((int)x, (int)road.getStartCoordinates().getY());
		
		vehicle.setAxom(p);
		
		
		/*
		List<LaneSection> lane = ((ListOfListsRoadImpl)road).getLaneSectionsOfRoad().get(laneIndex);
		Point startCoordinates = road.getStartCoordinates();
		Point endCoordinates = road.getEndCoordinates();
		if(startCoordinates.getX() == endCoordinates.getX()){
			
		}
		int numOfLanes = road.getNumberOfLanes();
		
		double yPerSeg = endCoordinates.getX() / numOfLanes;
		*/
		
	}

}
