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

public class Context implements IContext {

	private List<Vehicle> vehicleList;
	private List<Junction> junctionList = new ArrayList<>();

	// Remember a single Road is a ListOfListsRoadImpl
	// This List represents the entire network road
	// and it is a list of ListOfListsRoadImpl objects.
	private List<Road> roadList;
	private List<TrafficLight> trafficLightList = new ArrayList<>();

	public Context(List<Road> roadList,
			List<Vehicle> vehicleList /* , List<Junction> junctionList */) {
		this.roadList = roadList;
		this.vehicleList = vehicleList;

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

	public boolean addRoad(Road road) {
		return roadList.add(road);
	}

	public List<Road> getRoadList() {
		return roadList;
	}

	public boolean addJunction(Junction junction) {
		return junctionList.add(junction);
	}

	public boolean addTrafficLight(TrafficLight trafficLight) {
		return trafficLightList.add(trafficLight);
	}

	public List<Junction> getJunctionList() {
		return junctionList;
	}

	public List<TrafficLight> getTrafficLightList() {
		return trafficLightList;
	}

	public boolean addVehicle(Vehicle vehicle) {
		boolean res = vehicleList.add(vehicle);
		return res;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

//	public Point moveVehicle(int moveType, Vehicle vehicle, Position oldPos, Position newPos) {
//		Point p;
//
//		switch (moveType) {
//			case 0:
//				p = moveVehicle(vehicle, oldPos, newPos);
//				break;
//			case 1:
//				p = moveVehicleInJunction(vehicle, oldPos, newPos);
//				break;
//			default:
//				p = null;
//				break;
//		}
//
//		return p;
//	}
//
//	private Point moveVehicleInJunction(Vehicle vehicle, Position oldPos, Position newPos) {
//		return null;
//	}

	public Point moveVehicle(Vehicle vehicle, Position oldPos, Position newPos){
		Point p = null;
		double move = 0;
		
		Road road = roadList.get(newPos.getRoad());
		
		if (newPos.getMode() == Constant.MOVE_IN_ROAD) {

			if (oldPos.getRoad() == newPos.getRoad()) {
				// In this case we are in the same road after moving

				if (road.getOrientation() == Orientation.RIGHT_HORIZONTAL
						|| road.getOrientation() == Orientation.DOWN_VERTICAL) {
					
					if (road.getOrientation() == Orientation.RIGHT_HORIZONTAL) {
						// need to find the start point first
						move = road.getStartCoordinates().getX();
					} else if (road.getOrientation() == Orientation.DOWN_VERTICAL) {
						// need to find the start point first
						move = road.getStartCoordinates().getY();
					}
					move += newPos.getLaneSection() * Constant.LANE_SECTION_HEIGHT * Constant.PIXELS;
				
				} else if (road.getOrientation() == Orientation.LEFT_HORIZONTAL
						|| road.getOrientation() == Orientation.UP_VERTICAL) {
					
					if (road.getOrientation() == Orientation.LEFT_HORIZONTAL) {
						// need to find the start point first
						move = road.getEndCoordinates().getX();
					} else if (road.getOrientation() == Orientation.UP_VERTICAL) {
						// need to find the start point first
						//move = road.getStartCoordinates().getY();
						move = road.getEndCoordinates().getY();
					}
					move -= newPos.getLaneSection() * Constant.LANE_SECTION_HEIGHT * Constant.PIXELS;
				}
				
				if (road.getOrientation() == Orientation.HORIZONTAL
						|| road.getOrientation() == Orientation.LEFT_HORIZONTAL
						|| road.getOrientation() == Orientation.RIGHT_HORIZONTAL) {

					// adding VEHICLE_LEFT_MARGIN to place the car in the middle
					// of the lane
					p = new Point((int) move, (int) road.getStartCoordinates().getY() + Constant.VEHICLE_LEFT_MARGIN*Constant.PIXELS);
				} else {

					// adding VEHICLE_LEFT_MARGIN to place the car in the middle
					// of the lane
					p = new Point((int) road.getStartCoordinates().getX() + Constant.VEHICLE_LEFT_MARGIN*Constant.PIXELS, (int) move);
				}
			}
			
		} else { // Moving through a Junction
			p = getPointInJunction(newPos.getLane(), junctionList.get(newPos.getRoad()));
		}
		
		vehicle.setAxom(p);
		return p;
	}

	public Point getPointInJunction(int index, Junction junction){
		List<Point> jncPointList;
		jncPointList = junction.getCoordinates();
		Point point = (Point) jncPointList.get(index).clone();
		
		switch (index) {
			case 0:
				point.x = point.x + Constant.VEHICLE_LEFT_MARGIN;
				point.y = point.y - Constant.VEHICLE_LENGTH;
				break;
				
			case 1:
				point.x = point.x + Constant.VEHICLE_LEFT_MARGIN;
				point.y = point.y + Constant.VEHICLE_LEFT_MARGIN;
				break;
				
			case 2:
				point.x = point.x - (Constant.VEHICLE_WIDTH + Constant.VEHICLE_LEFT_MARGIN);
				point.y = point.y + Constant.VEHICLE_LEFT_MARGIN;
				break;
			
			case 3:
				point.x = point.x - (Constant.VEHICLE_WIDTH + Constant.VEHICLE_LEFT_MARGIN);
				point.y = point.y - Constant.VEHICLE_LENGTH;
				break;
			
			default:
				point = null;
				break;
		}
		
		return point;
	}
	
	public TrafficLight getTrafficLight(Road road, Junction junction) {

		/*
		 * get TrafficLight object for road <road> at junction <junction> if
		 * there is not a traffic light at the junction <junction> for road
		 * <road>, the procedure will return NULL For example:
		 * 
		 * TraffiLight trafficLight = getTrafficLight(road1, junction1); if
		 * (trafficLight != null) { if ((traiffcLight.getState() == State.GREEN)
		 * produceroute(); else vehicle.stop(); }
		 */

		TrafficLight myTrafficLight = null;
		for (TrafficLight trafficLight : trafficLightList) {
			if ((trafficLight.getRoad() == road) && (trafficLight.getJunction() == junction)) {
				myTrafficLight = trafficLight;
				break;
			}
		}

		return myTrafficLight;

	}

}