package com.kcl.keepitclean.main.simulatorengine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight;
import com.kcl.keepitclean.main.roadnetwork.road.Orientation;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.utils.Constant;
import com.kcl.keepitclean.main.vehicle.Position;
import com.kcl.keepitclean.main.vehicle.Vehicle;

public class Context2 implements IContext {
	
	private List<Vehicle> vehicleList;
	private List<Junction> junctionList = new ArrayList();
	
	//Remember a single Road is a ListOfListsRoadImpl
	//This List represents the entire network road
	//and it is a list of ListOfListsRoadImpl objects.
	private List<Road> roadList;
	private List<TrafficLight> trafficLightList = new ArrayList();
	
	public Context2(List<Road> roadList, List<Vehicle> vehicleList /*, List<Junction> junctionList*/) {
		this.roadList = roadList;
		this.vehicleList =  vehicleList;
		
		//trafficLightList = new ArrayList<>();
		trafficLightList = getTrafficLights(junctionList);
	}
	
	private List<TrafficLight> getTrafficLights(List<Junction> jList) {
		if (jList != null) {
			for (Junction junction : jList) {
				if (junction instanceof TrafficLight) {
					trafficLightList.add(((TrafficLight) junction));
				}
			}
		}
		return trafficLightList;
	}

	public boolean addRoad(Road road){
		return roadList.add(road);
	}
	
	public List<Road> getRoadList(){
		 return roadList;
	}
	
	public boolean addJunction(Junction junction){
		return junctionList.add(junction);
	}
        public boolean addTrafficLight(TrafficLight trafficLight){
		return trafficLightList.add(trafficLight);
	}
	
	public List<Junction> getJunctionList(){
		 return junctionList;
	}
	
	public List<TrafficLight> getTrafficLightList(){
		return trafficLightList;
	}
	
	public boolean addVehicle(Vehicle vehicle){
		boolean res = vehicleList.add(vehicle);
		return res;
	}
	
	public List<Vehicle> getVehicleList(){
		 return vehicleList;
	}
	
	public Point moveVehicle(int moveType, Vehicle vehicle, Position oldPos, Position newPos){
		Point p;
		
		switch (moveType) {
		case 0:
			p = moveVehicle(vehicle, oldPos, newPos);
			break;
		case 1:
			p = moveVehicle(vehicle, 3, 3);
			break;
		default:
			p = null;
			break;
		}
		
		return p;
	}
	
	private Point moveVehicle(Vehicle vehicle, int junctionGridX, int junctionGridY){
		return null;
	}		
	
	public Point moveVehicle(Vehicle vehicle, Position oldPos, Position newPos){
		Point p = null;
		double move;
		
		Road road = roadList.get(newPos.getRoad());
		
		if(oldPos.getRoad() == newPos.getRoad()){
			//In this case we are in the same road after moving
			
			if (road.getOrientation() == Orientation.HORIZONTAL ||
					road.getOrientation() == Orientation.LEFT_HORIZONTAL ||
					road.getOrientation() == Orientation.RIGHT_HORIZONTAL) {
				//need to find the start point first
				move = road.getStartCoordinates().getX();
			} else {
				//need to find the start point first
				move = road.getStartCoordinates().getY();
			}
			
			for(int i = 0; i < oldPos.getLaneSection(); i++){
				move += 1;
			}
			for(int i = 0; i < newPos.getLaneSection(); i++){
				move += 1;
			}
			
			if (road.getOrientation() == Orientation.HORIZONTAL ||
					road.getOrientation() == Orientation.LEFT_HORIZONTAL ||
					road.getOrientation() == Orientation.RIGHT_HORIZONTAL) {
				
				//adding VEHICLE_LEFT_MARGIN to place the car in the middle of the lane
				p = new Point((int)move, (int)road.getStartCoordinates().getY()
														+ Constant.VEHICLE_LEFT_MARGIN);
			} else {
				
				//adding VEHICLE_LEFT_MARGIN to place the car in the middle of the lane
				p = new Point((int)road.getStartCoordinates().getX()
														+ Constant.VEHICLE_LEFT_MARGIN,
														(int)move);
			}
		}
		vehicle.setAxom(p);
		return p;
	}
	
	@Override
	@Deprecated
	public void moveVehicle(int roadIndex, int laneIndex, int sectionIndex, 
							Vehicle vehicle, int fromThisLane , int toThisLane) {
		
		Road road = roadList.get(roadIndex);
		
		//need to find the start point first
		double x = road.getStartCoordinates().getX();
		for(int i = 0; i < fromThisLane; i++){
			x += 5;
		}
		for(int i = 0; i < toThisLane; i++){
			x += 5;
		}
		Point p = new Point((int)x + Constant.LANE_SECTION_HEIGHT, 
							(int)road.getStartCoordinates().getY());
		
		vehicle.setAxom(p);
		
	}
        
        public TrafficLight getTrafficLight(Road road, Junction junction){
            
            /* get TrafficLight object for road <road> at junction <junction>
            if there is not a traffic light at the junction <junction> for road <road>, the procedure will return NULL
            For example:
            
            TraffiLight trafficLight = getTrafficLight(road1, junction1);
            if (trafficLight != null)
            {
              if ((traiffcLight.getState()  == State.GREEN)
                    produceroute();
              else 
                     vehicle.stop();
            }
            */
            
            TrafficLight myTrafficLight = null;
            for (TrafficLight trafficLight:trafficLightList){
                if ((trafficLight.getRoad() == road) && (trafficLight.getJunction() == junction))
                {
                    myTrafficLight = trafficLight;
                    break;
                }
            }
            
            return myTrafficLight;
            
        }
        
        
        
        
}