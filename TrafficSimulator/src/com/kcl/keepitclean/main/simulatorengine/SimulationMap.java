/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.simulatorengine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.PrePlannedRouteJunction;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight.State;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
import com.kcl.keepitclean.main.roadnetwork.road.Orientation;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;
import com.kcl.keepitclean.main.utils.Constant;

/**
 *
 * @author rosiengo
 */
public class SimulationMap {

	Road road;
	ArrayList<Road> roads = new ArrayList();
	ArrayList<Junction> junctions = new ArrayList();
	ArrayList<TrafficLight> trafficLights = new ArrayList();
	Constant constant = new Constant();
	RoadFactory roadFactory = new RoadFactory(new LaneFactory());
	private Map<Point, Road> startPointMap = new HashMap<>();

	public ArrayList<Junction> getJunctions() {
		return junctions;
	}

	public List<Road> getRoads() {
		return roads;
	}

	public List<TrafficLight> getTrafficLights() {
		return trafficLights;
	}

	public void generateRoad(Point startPoint, int length, int lanes, Orientation orientation) {
		/* this method generates a road with given start point and road size */
		Point endPoint = new Point();
		Road road = roadFactory.produceRoad("listoflistsroadimpl", length, lanes);
		if (orientation == Orientation.HORIZONTAL || orientation == Orientation.LEFT_HORIZONTAL
				|| orientation == Orientation.RIGHT_HORIZONTAL) // HORIZONTAL
		{
			endPoint.x = startPoint.x + road.getLengthOfRoad() * constant.LANE_SECTION_HEIGHT * constant.PIXELS;
			endPoint.y = startPoint.y + road.getNumberOfLanes() * constant.LANE_SIZE * constant.PIXELS;

		}

		else if (orientation == Orientation.VERTICAL || orientation == Orientation.DOWN_VERTICAL
				|| orientation == Orientation.UP_VERTICAL) // VERTICAL
		{
			endPoint.x = startPoint.x + road.getNumberOfLanes() * constant.LANE_SIZE * constant.PIXELS;
			endPoint.y = startPoint.y + road.getLengthOfRoad() * constant.LANE_SECTION_HEIGHT * constant.PIXELS;
		}

		((ListOfListsRoadImpl) road).setStartCoordinate(new Point(startPoint.x, startPoint.y));
		((ListOfListsRoadImpl) road).setEndCoordinate(new Point(endPoint.x, endPoint.y));
		((ListOfListsRoadImpl) road).setOrientation(orientation);

		roads.add(road);

		// assign all roads their indexes in the list
		for (int i = 0; i < roads.size(); i++) {
			roads.get(i).setIndex(i);
		}

		for (int i = 0; i < roads.size(); i++) {
			Point point = new Point();
			point = roads.get(i).getStartCoordinates();
			startPointMap.put(point, roads.get(i));

		}

	}

	public void generateRoad(Point startPoint, Point endPoint, Orientation orientation) {
		/* this method generates a road with given startPoint and endPoint */
		int length = 0;
		int lanes = 0;

		if (orientation == Orientation.HORIZONTAL || orientation == Orientation.LEFT_HORIZONTAL
				|| orientation == Orientation.RIGHT_HORIZONTAL) // HORIZONTAL
		{
			length = Math.round((endPoint.x - startPoint.x) / (constant.LANE_SECTION_HEIGHT * constant.PIXELS));
			lanes = Math.round((endPoint.y - startPoint.y) / (constant.LANE_SIZE * constant.PIXELS));
		} else if (orientation == Orientation.VERTICAL || orientation == Orientation.DOWN_VERTICAL
				|| orientation == Orientation.UP_VERTICAL) // VERTICAL
		{
			lanes = Math.round((endPoint.x - startPoint.x) / (constant.LANE_SECTION_HEIGHT * constant.PIXELS));
			length = Math.round((endPoint.y - startPoint.y) / (constant.LANE_SIZE * constant.PIXELS));
		}
		Road road = roadFactory.produceRoad("listoflistsroadimpl", length, lanes);
		((ListOfListsRoadImpl) road).setStartCoordinate(new Point(startPoint.x, startPoint.y));
		((ListOfListsRoadImpl) road).setEndCoordinate(new Point(endPoint.x, endPoint.y));
		((ListOfListsRoadImpl) road).setOrientation(orientation);

		roads.add(road);

	}

	public void generateJunction(int[] in, int[] out) {
		ArrayList<Road> roadsGoingIntoJunction = new ArrayList();
		ArrayList<Road> roadsLeavingJunction = new ArrayList();
		Junction junction;

		for (int i = 0; i < in.length; i++) {
			roadsGoingIntoJunction.add(roads.get(in[i]));

		}
		for (int j = 0; j < out.length; j++) {
			roadsLeavingJunction.add(roads.get(out[j]));

		}

		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);

		junctions.add(junction);

		for (int i = 0; i < in.length; i++) {
			roads.get(in[i]).setEndJunction(junction);

		}

		for (int j = 0; j < out.length; j++) {
			roads.get(in[j]).setStartJunction(junction);

		}

	}

	public void generateTrafficLight(int roadIndex, int junctionIndex, State state) {
		TrafficLight trafficLight;
		trafficLight = new TrafficLight(roads.get(roadIndex), junctions.get(junctionIndex));
		trafficLight.setState(state);
		trafficLight.setTrafficLightCoordinate(new Point(trafficLight.getRoad().getJuctionEndCoordinates().x,
				trafficLight.getRoad().getJuctionEndCoordinates().y));
		trafficLights.add(trafficLight);
	}

	public Map<Point, Road> getStartPointMap() {
		return startPointMap;
	}

	public void setStartPointMap(Map<Point, Road> startPointMap) {
		this.startPointMap = startPointMap;
	}

}
