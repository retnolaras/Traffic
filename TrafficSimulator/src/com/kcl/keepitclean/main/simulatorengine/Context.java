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

	// Remember that a single Road is a ListOfListsRoadImpl.
	// This List represents the entire network road
	// and it is a list of ListOfListsRoadImpl objects.
	private List<Road> roadList;
	private List<TrafficLight> trafficLightList = new ArrayList<>();
	private SimulationData simulationData;

	public Context(List<Road> roadList, List<Vehicle> vehicleList) {
		this.roadList = roadList;
		this.vehicleList = vehicleList;

		trafficLightList = getTrafficLights(junctionList);
	}
	
	/**
	 * Extract a list of traffic light from a junction list
	 * @param jList
	 * @return
	 */
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

	/**
	 * Adds a road to the road list
	 * @param road
	 * @return
	 */
	public boolean addRoad(Road road) {
		return roadList.add(road);
	}

	/**
	 * @return The list of roads
	 */
	public List<Road> getRoadList() {
		return roadList;
	}

	/**
	 * Adds a junction to the junction list.
	 * @param junction
	 * @return
	 */
	public boolean addJunction(Junction junction) {
		return junctionList.add(junction);
	}
	
	/**
	 * Adds a traffic light to the traffic light list
	 * @param trafficLight
	 * @return
	 */
	public boolean addTrafficLight(TrafficLight trafficLight) {
		return trafficLightList.add(trafficLight);
	}

	/**
	 * @return The junction list
	 */
	public List<Junction> getJunctionList() {
		return junctionList;
	}

	/**
	 * @return The traffic light list
	 */
	public List<TrafficLight> getTrafficLightList() {
		return trafficLightList;
	}

	/**
	 * Adds a vehicle into the vehicle list
	 * @param vehicle
	 * @return
	 */
	public boolean addVehicle(Vehicle vehicle) {
		boolean res = vehicleList.add(vehicle);
		return res;
	}

	/**
	 * @return The vehicle list
	 */
	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	/**
	 * Calculates a point from the given position and assign it to the given vehicle.
	 * @return The assigned point to the given vehicle
	 */
	public Point moveVehicle(Vehicle vehicle, Position newPos) {
		Point p = null;
		double move = 0;

		Road road = roadList.get(newPos.getRoad());

		if (newPos.getMode() == Constant.MOVE_IN_ROAD) {

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
					move = road.getEndCoordinates().getY();
				}
				move -= newPos.getLaneSection() * Constant.LANE_SECTION_HEIGHT * Constant.PIXELS;
			}

			if (road.getOrientation() == Orientation.HORIZONTAL || road.getOrientation() == Orientation.LEFT_HORIZONTAL
					|| road.getOrientation() == Orientation.RIGHT_HORIZONTAL) {

				// adding VEHICLE_LEFT_MARGIN to place the car in the middle
				// of the lane
				p = new Point((int) move,
						(int) road.getStartCoordinates().getY() + Constant.VEHICLE_LEFT_MARGIN * Constant.PIXELS);
			} else {

				// adding VEHICLE_LEFT_MARGIN to place the car in the middle
				// of the lane
				p = new Point((int) road.getStartCoordinates().getX() + Constant.VEHICLE_LEFT_MARGIN * Constant.PIXELS,
						(int) move);
			}
		} else { // Moving through a Junction
			p = getPointInJunction(newPos.getLane(), junctionList.get(newPos.getRoad()));
		}

		vehicle.setAxom(p);
		return p;
	}
	
	/**
	 * Given an junction grid index, it calculates a point relative to the given junction.
	 * @param index
	 * @param junction
	 * @return
	 */
	public Point getPointInJunction(int index, Junction junction) {
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

	/**
	 * get TrafficLight object for road <road> at junction <junction> if
	 * there is not a traffic light at the junction <junction> for road
	 * <road>, the procedure will return NULL For example:<br>
	 * 
	 * <code>TraffiLight trafficLight = getTrafficLight(road1, junction1);<br> 
	 * if (trafficLight != null) {<br> 
	 *  if ((traiffcLight.getState() == State.GREEN)<br>
	 * 		produceroute();<br> 
	 * 	else<br> 
	 * 		vehicle.stop();<br> 
	 * }</code>
	 * @param road
	 * @param junction
	 * @return
	 */
	public TrafficLight getTrafficLight(Road road, Junction junction) {

		TrafficLight myTrafficLight = null;
		for (TrafficLight trafficLight : trafficLightList) {
			if ((trafficLight.getRoad() == road) && (trafficLight.getJunction() == junction)) {
				myTrafficLight = trafficLight;
				break;
			}
		}

		return myTrafficLight;

	}

	@Override
	public SimulationData getSimulationData() {
		return simulationData;
	}

}