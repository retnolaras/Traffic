package com.kcl.keepitclean.main.simulatorengine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.vehicle.Position;
import com.kcl.keepitclean.main.vehicle.Vehicle;

public class Context implements IContext {
	private static int MARGIN_X = 2;
	private static int MARGIN_Y = 3;
	
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
	
	/*public List<Road> getRoadList(List<Road> roadList){
		 return roadList;
	}*/	
	
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
	
	public List<Vehicle> getVehicleList(){
		 return vehicleList;
	}
	
	@Override
	public Point moveVehicle(Vehicle vehicle, Position oldPos, Position newPos){
		Point p;
		
		Road road = roadList.get(newPos.getRoad());
		
		if(oldPos.getRoad() == newPos.getRoad()){
			//need to find the start point first
			double x = road.getStartCoordinates().getX();
			for(int i = 0; i < oldPos.getLaneSection(); i++){
				x += 50;
			}
			for(int i = 0; i < newPos.getLaneSection(); i++){
				x += 50;
			}
			//adding 2 to x to place the car in the middle of the lane
			p = new Point((int)x + MARGIN_X,
							(int)road.getStartCoordinates().getY() + MARGIN_Y);
		} else {
			//need to find the start point first
			double x = road.getStartCoordinates().getX();
			for(int i = 0; i < newPos.getLaneSection(); i++){
				x += 50;
			}
			//adding 2 to x to place the car in the middle of the lane
			p = new Point((int)x + MARGIN_X,
							(int)road.getStartCoordinates().getY() + MARGIN_Y);
		}
		
		vehicle.setAxom(p);
		return p;		
	}
	
	@Override
	public void moveVehicle(int roadIndex, int laneIndex, int sectionIndex, 
							Vehicle vehicle, int fromThisLane , int toThisLane) {
		
		Road road = roadList.get(roadIndex);
		
		//need to find the start point first
		double x = road.getStartCoordinates().getX();
		for(int i = 0; i < fromThisLane; i++){
			x += 50;
		}
		for(int i = 0; i < toThisLane; i++){
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
