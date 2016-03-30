/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.simulatorengine;

import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
import com.kcl.keepitclean.main.roadnetwork.road.Orientation;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;
import com.kcl.keepitclean.main.utils.Constant;

import java.awt.Point;

/**
 *
 * @author rosiengo
 */
public class SimpleMap extends SimulationMap {
	public SimpleMap() {
		super();
		this.generateRoads();
		this.generateJunctions();
		this.generateTrafficLights();
	}

	public void generateRoads() {

		RoadFactory roadFactory = new RoadFactory(new LaneFactory());
		Point startPoint = new Point(0, 0);
		Point endPoint = new Point();
		int length = 0;
		int lanes = 0;

		// road[0]: top road - 01- horizontal
		generateRoad(new Point(30, 270), 80, 1, Orientation.RIGHT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(0)).setJuctionEndCoordinates(
				new Point(roads.get(0).getEndCoordinates().x, roads.get(0).getStartCoordinates().y));

		// road[1]: top road 02- horizontal
		generateRoad(new Point(30, 270 + Constant.LANE_SIZE * Constant.PIXELS), 80, 1, Orientation.LEFT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(1)).setJuctionStartCoordinates(
				new Point(roads.get(1).getEndCoordinates().x, roads.get(1).getEndCoordinates().y));

		// road 2 - first vertical road - 01

		generateRoad(new Point(roads.get(1).getEndCoordinates().x, roads.get(1).getEndCoordinates().y), 60, 1,
				Orientation.UP_VERTICAL);
		((ListOfListsRoadImpl) roads.get(2)).setJuctionEndCoordinates(
				new Point(roads.get(2).getStartCoordinates().x, roads.get(2).getStartCoordinates().y));

		((ListOfListsRoadImpl) roads.get(2)).setJuctionStartCoordinates(
				new Point(roads.get(2).getStartCoordinates().x, roads.get(2).getEndCoordinates().y));

		// road 3- first vertical road 02
		generateRoad(new Point(roads.get(2).getStartCoordinates().x + 16, roads.get(2).getStartCoordinates().y), 60, 1,
				Orientation.DOWN_VERTICAL);
		((ListOfListsRoadImpl) roads.get(3)).setJuctionEndCoordinates(
				new Point(roads.get(3).getEndCoordinates().x, roads.get(3).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(3)).setJuctionStartCoordinates(
				new Point(roads.get(3).getEndCoordinates().x, roads.get(3).getStartCoordinates().y));

		// road[4]: top road 03
		startPoint.x = roads.get(3).getEndCoordinates().x;
		startPoint.y = roads.get(0).getStartCoordinates().y;

		generateRoad(new Point(startPoint.x, startPoint.y), 80, 1, Orientation.RIGHT_HORIZONTAL);

		((ListOfListsRoadImpl) roads.get(4)).setJuctionStartCoordinates(
				new Point(roads.get(4).getStartCoordinates().x, roads.get(4).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(4)).setJuctionEndCoordinates(
				new Point(roads.get(4).getEndCoordinates().x, roads.get(4).getStartCoordinates().y));

		// road[5] - top road 04
		startPoint.x = roads.get(4).getStartCoordinates().x;
		startPoint.y = roads.get(4).getEndCoordinates().y;
		// startPoint.x = roads.get(3).getEndCoordinates().x ;
		// startPoint.y = roads.get(3).getStartCoordinates().y -
		// constant.LANE_SECTION_HEIGHT * constant.PIXELS;

		generateRoad(new Point(startPoint.x, startPoint.y), 80, 1, Orientation.LEFT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(5)).setJuctionEndCoordinates(
				new Point(roads.get(5).getStartCoordinates().x, roads.get(5).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(5)).setJuctionStartCoordinates(
				new Point(roads.get(5).getEndCoordinates().x, roads.get(5).getEndCoordinates().y));

		// road 6 - first vertical road - 03
		startPoint.x = roads.get(0).getEndCoordinates().x;
		startPoint.y = roads.get(0).getStartCoordinates().y - 60 * Constant.LANE_SECTION_HEIGHT * Constant.PIXELS;

		generateRoad(new Point(startPoint.x, startPoint.y), 60, 1, Orientation.UP_VERTICAL);
		((ListOfListsRoadImpl) roads.get(6)).setJuctionStartCoordinates(
				new Point(roads.get(6).getStartCoordinates().x, roads.get(6).getEndCoordinates().y));

		// road 7- first vertical road 04
		startPoint.x = roads.get(6).getEndCoordinates().x;
		startPoint.y = roads.get(6).getStartCoordinates().y;

		generateRoad(new Point(startPoint.x, startPoint.y), 60, 1, Orientation.DOWN_VERTICAL);
		((ListOfListsRoadImpl) roads.get(7)).setJuctionEndCoordinates(
				new Point(roads.get(7).getEndCoordinates().x, roads.get(7).getEndCoordinates().y));

	}

	public void generateJunctions() {

		generateJunction(new int[] { 0, 7, 5, 2 }, new int[] { 6, 4, 3, 1 }); // J0

	}

	public void generateTrafficLights() {
		generateTrafficLight(0, 0, TrafficLight.State.GREEN);
		generateTrafficLight(7, 0, TrafficLight.State.RED);
		generateTrafficLight(2, 0, TrafficLight.State.RED);
		generateTrafficLight(5, 0, TrafficLight.State.GREEN);

	}
}
