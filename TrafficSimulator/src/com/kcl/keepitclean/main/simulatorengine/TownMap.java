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
public class TownMap extends SimulationMap {
	public TownMap() {
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
		generateRoad(new Point(30, 0), 70, 1, Orientation.RIGHT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(0)).setJuctionEndCoordinates(
				new Point(roads.get(0).getEndCoordinates().x, roads.get(0).getStartCoordinates().y));
		// road[1]: top road 02- horizontal
		generateRoad(new Point(30, constant.LANE_SIZE * constant.PIXELS), 70, 1, Orientation.LEFT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(1)).setJuctionStartCoordinates(
				new Point(roads.get(1).getEndCoordinates().x, roads.get(1).getEndCoordinates().y));

		// road 2 - first vertical road - 01

		generateRoad(new Point(roads.get(1).getEndCoordinates().x, roads.get(1).getEndCoordinates().y), 40, 1,
				Orientation.UP_VERTICAL);
		((ListOfListsRoadImpl) roads.get(2)).setJuctionEndCoordinates(
				new Point(roads.get(2).getStartCoordinates().x, roads.get(2).getStartCoordinates().y));

		((ListOfListsRoadImpl) roads.get(2)).setJuctionStartCoordinates(
				new Point(roads.get(2).getStartCoordinates().x, roads.get(2).getEndCoordinates().y));

		// road 3- first vertical road 02
		generateRoad(new Point(roads.get(2).getStartCoordinates().x + 16, roads.get(2).getStartCoordinates().y), 40, 1,
				Orientation.DOWN_VERTICAL);
		((ListOfListsRoadImpl) roads.get(3)).setJuctionEndCoordinates(
				new Point(roads.get(3).getEndCoordinates().x, roads.get(3).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(3)).setJuctionStartCoordinates(
				new Point(roads.get(3).getEndCoordinates().x, roads.get(3).getStartCoordinates().y));

		// road[4]: top road 03
		startPoint.x = roads.get(3).getEndCoordinates().x;
		startPoint.y = 0;

		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.RIGHT_HORIZONTAL);

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

		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.LEFT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(5)).setJuctionEndCoordinates(
				new Point(roads.get(5).getStartCoordinates().x, roads.get(5).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(5)).setJuctionStartCoordinates(
				new Point(roads.get(5).getEndCoordinates().x, roads.get(5).getEndCoordinates().y));

		// road 6- first middle road 01
		startPoint.x = 30;
		startPoint.y = roads.get(2).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.RIGHT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(6)).setJuctionEndCoordinates(
				new Point(roads.get(6).getEndCoordinates().x, roads.get(6).getStartCoordinates().y));

		// road 7- first middleroad 02
		startPoint.x = 30;
		startPoint.y = roads.get(6).getStartCoordinates().y + Constant.LANE_SIZE * Constant.PIXELS;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.LEFT_HORIZONTAL);

		((ListOfListsRoadImpl) roads.get(7)).setJuctionStartCoordinates(
				new Point(roads.get(7).getEndCoordinates().x, roads.get(7).getEndCoordinates().y));
		// road 8- first middle road 03
		startPoint.x = roads.get(3).getEndCoordinates().x;
		startPoint.y = roads.get(3).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.RIGHT_HORIZONTAL);

		((ListOfListsRoadImpl) roads.get(8)).setJuctionStartCoordinates(
				new Point(roads.get(8).getStartCoordinates().x, roads.get(8).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(8)).setJuctionEndCoordinates(
				new Point(roads.get(8).getEndCoordinates().x, roads.get(8).getStartCoordinates().y));

		// road 9- first middleroad 04
		startPoint.x = roads.get(8).getStartCoordinates().x;
		startPoint.y = roads.get(8).getStartCoordinates().y + Constant.LANE_SIZE * Constant.PIXELS;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.LEFT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(9)).setJuctionEndCoordinates(
				new Point(roads.get(9).getStartCoordinates().x, roads.get(9).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(9)).setJuctionStartCoordinates(
				new Point(roads.get(9).getEndCoordinates().x, roads.get(9).getEndCoordinates().y));
		// road 10 - first vertical road - 03

		generateRoad(new Point(roads.get(7).getEndCoordinates().x, roads.get(7).getEndCoordinates().y), 40, 1,
				Orientation.UP_VERTICAL);
		((ListOfListsRoadImpl) roads.get(10)).setJuctionEndCoordinates(
				new Point(roads.get(10).getStartCoordinates().x, roads.get(10).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(10)).setJuctionStartCoordinates(
				new Point(roads.get(10).getStartCoordinates().x, roads.get(10).getEndCoordinates().y));
		// road 11- first vertical road 04
		generateRoad(new Point(roads.get(10).getStartCoordinates().x + Constant.LANE_SIZE * Constant.PIXELS,
				roads.get(10).getStartCoordinates().y), 40, 1, Orientation.DOWN_VERTICAL);

		((ListOfListsRoadImpl) roads.get(11)).setJuctionEndCoordinates(
				new Point(roads.get(11).getEndCoordinates().x, roads.get(11).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(11)).setJuctionStartCoordinates(
				new Point(roads.get(11).getEndCoordinates().x, roads.get(11).getStartCoordinates().y));

		// road 12- second middle road 01
		startPoint.x = 30;
		startPoint.y = roads.get(10).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.RIGHT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(12)).setJuctionEndCoordinates(
				new Point(roads.get(12).getEndCoordinates().x, roads.get(12).getStartCoordinates().y));

		// road 13- second middleroad 02
		startPoint.x = 30;
		startPoint.y = roads.get(12).getStartCoordinates().y + Constant.LANE_SIZE * Constant.PIXELS;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.LEFT_HORIZONTAL);

		((ListOfListsRoadImpl) roads.get(13)).setJuctionStartCoordinates(
				new Point(roads.get(13).getEndCoordinates().x, roads.get(13).getEndCoordinates().y));
		// road 14- second middle road 03
		startPoint.x = roads.get(11).getEndCoordinates().x;
		startPoint.y = roads.get(11).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.RIGHT_HORIZONTAL);

		((ListOfListsRoadImpl) roads.get(14)).setJuctionStartCoordinates(
				new Point(roads.get(14).getStartCoordinates().x, roads.get(14).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(14)).setJuctionEndCoordinates(
				new Point(roads.get(14).getEndCoordinates().x, roads.get(14).getStartCoordinates().y));

		// road 15- second middleroad 04
		startPoint.x = roads.get(14).getStartCoordinates().x;
		startPoint.y = roads.get(14).getStartCoordinates().y + Constant.LANE_SIZE * Constant.PIXELS;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.LEFT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(15)).setJuctionEndCoordinates(
				new Point(roads.get(15).getStartCoordinates().x, roads.get(15).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(15)).setJuctionStartCoordinates(
				new Point(roads.get(15).getEndCoordinates().x, roads.get(15).getEndCoordinates().y));
		// road 16 - first vertical road - 05

		generateRoad(new Point(roads.get(13).getEndCoordinates().x, roads.get(13).getEndCoordinates().y), 38, 1,
				Orientation.UP_VERTICAL);
		((ListOfListsRoadImpl) roads.get(16)).setJuctionEndCoordinates(
				new Point(roads.get(16).getStartCoordinates().x, roads.get(16).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(16)).setJuctionStartCoordinates(
				new Point(roads.get(16).getStartCoordinates().x, roads.get(16).getEndCoordinates().y));
		// road 17- first vertical road 06
		generateRoad(new Point(roads.get(16).getStartCoordinates().x + Constant.LANE_SIZE * Constant.PIXELS,
				roads.get(16).getStartCoordinates().y), 38, 1, Orientation.DOWN_VERTICAL);

		((ListOfListsRoadImpl) roads.get(17)).setJuctionEndCoordinates(
				new Point(roads.get(17).getEndCoordinates().x, roads.get(17).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(17)).setJuctionStartCoordinates(
				new Point(roads.get(17).getEndCoordinates().x, roads.get(17).getStartCoordinates().y));

		// road 18- bottom road 01
		startPoint.x = 30;
		startPoint.y = roads.get(16).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.RIGHT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(18)).setJuctionEndCoordinates(
				new Point(roads.get(18).getEndCoordinates().x, roads.get(18).getStartCoordinates().y));

		// road 19- bottom road 02
		startPoint.x = 30;
		startPoint.y = roads.get(18).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.LEFT_HORIZONTAL);

		((ListOfListsRoadImpl) roads.get(19)).setJuctionStartCoordinates(
				new Point(roads.get(19).getEndCoordinates().x, roads.get(19).getEndCoordinates().y));
		// road 20- bottom road 03
		startPoint.x = roads.get(17).getEndCoordinates().x;
		startPoint.y = roads.get(17).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.RIGHT_HORIZONTAL);

		((ListOfListsRoadImpl) roads.get(20)).setJuctionStartCoordinates(
				new Point(roads.get(20).getStartCoordinates().x, roads.get(20).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(20)).setJuctionEndCoordinates(
				new Point(roads.get(20).getEndCoordinates().x, roads.get(20).getStartCoordinates().y));

		// road 21- bottom road 04
		startPoint.x = roads.get(20).getStartCoordinates().x;
		startPoint.y = roads.get(20).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 70, 1, Orientation.LEFT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(21)).setJuctionEndCoordinates(
				new Point(roads.get(21).getStartCoordinates().x, roads.get(21).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(21)).setJuctionStartCoordinates(
				new Point(roads.get(21).getEndCoordinates().x, roads.get(21).getEndCoordinates().y));

		// road 22 second vertical road - 01

		generateRoad(new Point(roads.get(5).getEndCoordinates().x, roads.get(5).getEndCoordinates().y), 40, 1,
				Orientation.UP_VERTICAL);
		((ListOfListsRoadImpl) roads.get(22)).setJuctionEndCoordinates(
				new Point(roads.get(22).getStartCoordinates().x, roads.get(22).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(22)).setJuctionStartCoordinates(
				new Point(roads.get(22).getStartCoordinates().x, roads.get(22).getEndCoordinates().y));
		// road 23- second vertical road 02
		generateRoad(new Point(roads.get(22).getEndCoordinates().x, roads.get(22).getStartCoordinates().y), 40, 1,
				Orientation.DOWN_VERTICAL);

		((ListOfListsRoadImpl) roads.get(23)).setJuctionEndCoordinates(
				new Point(roads.get(23).getEndCoordinates().x, roads.get(23).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(23)).setJuctionStartCoordinates(
				new Point(roads.get(23).getEndCoordinates().x, roads.get(23).getStartCoordinates().y));

		// road 24 second vertical road - 03

		generateRoad(new Point(roads.get(9).getEndCoordinates().x, roads.get(9).getEndCoordinates().y), 40, 1,
				Orientation.UP_VERTICAL);
		((ListOfListsRoadImpl) roads.get(24)).setJuctionEndCoordinates(
				new Point(roads.get(24).getStartCoordinates().x, roads.get(24).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(24)).setJuctionStartCoordinates(
				new Point(roads.get(24).getStartCoordinates().x, roads.get(24).getEndCoordinates().y));
		// road 25- second vertical road 04
		generateRoad(new Point(roads.get(24).getEndCoordinates().x, roads.get(24).getStartCoordinates().y), 40, 1,
				Orientation.DOWN_VERTICAL);

		((ListOfListsRoadImpl) roads.get(25)).setJuctionEndCoordinates(
				new Point(roads.get(25).getEndCoordinates().x, roads.get(25).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(25)).setJuctionStartCoordinates(
				new Point(roads.get(25).getEndCoordinates().x, roads.get(25).getStartCoordinates().y));

		// road 26 second vertical road - 04

		generateRoad(new Point(roads.get(15).getEndCoordinates().x, roads.get(15).getEndCoordinates().y), 38, 1,
				Orientation.UP_VERTICAL);
		((ListOfListsRoadImpl) roads.get(26)).setJuctionEndCoordinates(
				new Point(roads.get(26).getStartCoordinates().x, roads.get(26).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(26)).setJuctionStartCoordinates(
				new Point(roads.get(26).getStartCoordinates().x, roads.get(26).getEndCoordinates().y));
		// road 27- second vertical road 05
		generateRoad(new Point(roads.get(26).getEndCoordinates().x, roads.get(26).getStartCoordinates().y), 38, 1,
				Orientation.DOWN_VERTICAL);

		((ListOfListsRoadImpl) roads.get(27)).setJuctionEndCoordinates(
				new Point(roads.get(27).getEndCoordinates().x, roads.get(27).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(27)).setJuctionStartCoordinates(
				new Point(roads.get(27).getEndCoordinates().x, roads.get(27).getStartCoordinates().y));

		// road 28- bottom road 05
		startPoint.x = roads.get(27).getEndCoordinates().x;
		startPoint.y = roads.get(27).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 30, 1, Orientation.RIGHT_HORIZONTAL);

		((ListOfListsRoadImpl) roads.get(28)).setJuctionStartCoordinates(
				new Point(roads.get(28).getStartCoordinates().x, roads.get(28).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(28)).setJuctionEndCoordinates(
				new Point(roads.get(28).getEndCoordinates().x, roads.get(28).getStartCoordinates().y));

		// road 29- bottom road 06
		startPoint.x = roads.get(28).getStartCoordinates().x;
		startPoint.y = roads.get(28).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 30, 1, Orientation.LEFT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(29)).setJuctionEndCoordinates(
				new Point(roads.get(29).getStartCoordinates().x, roads.get(29).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(29)).setJuctionStartCoordinates(
				new Point(roads.get(29).getEndCoordinates().x, roads.get(29).getEndCoordinates().y));

		// road 30- top road 05
		startPoint.x = roads.get(23).getEndCoordinates().x;
		startPoint.y = roads.get(4).getStartCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 30, 1, Orientation.RIGHT_HORIZONTAL);

		((ListOfListsRoadImpl) roads.get(30)).setJuctionStartCoordinates(
				new Point(roads.get(30).getStartCoordinates().x, roads.get(30).getStartCoordinates().y));
		((ListOfListsRoadImpl) roads.get(30)).setJuctionEndCoordinates(
				new Point(roads.get(30).getEndCoordinates().x, roads.get(30).getStartCoordinates().y));

		// road 31- top road 06
		startPoint.x = roads.get(30).getStartCoordinates().x;
		startPoint.y = roads.get(30).getEndCoordinates().y;
		generateRoad(new Point(startPoint.x, startPoint.y), 30, 1, Orientation.LEFT_HORIZONTAL);
		((ListOfListsRoadImpl) roads.get(31)).setJuctionEndCoordinates(
				new Point(roads.get(31).getStartCoordinates().x, roads.get(31).getEndCoordinates().y));
		((ListOfListsRoadImpl) roads.get(31)).setJuctionStartCoordinates(
				new Point(roads.get(31).getEndCoordinates().x, roads.get(31).getEndCoordinates().y));

	}

	public void generateJunctions() {

		generateJunction(new int[] { 5, 2, 0 }, new int[] { 4, 3, 1 }); // J0

		generateJunction(new int[] { 9, 10, 6, 3 }, new int[] { 8, 11, 7, 2 }); // J1

		generateJunction(new int[] { 15, 16, 12, 11 }, new int[] { 14, 17, 13, 10 });// J2

		generateJunction(new int[] { 18, 17, 21 }, new int[] { 19, 16, 20 }); // J3

		generateJunction(new int[] { 31, 22, 4 }, new int[] { 30, 23, 5 }); // J4

		generateJunction(new int[] { 24, 8, 23 }, new int[] { 25, 9, 22 }); // J4
		generateJunction(new int[] { 26, 14, 25 }, new int[] { 27, 15, 24 }); // J6
		generateJunction(new int[] { 20, 27, 29 }, new int[] { 21, 26, 28 }); // J7

	}

	public void generateTrafficLights() {
		generateTrafficLight(6, 1, TrafficLight.State.GREEN);
		generateTrafficLight(10, 1, TrafficLight.State.RED);
		generateTrafficLight(3, 1, TrafficLight.State.RED);
		generateTrafficLight(9, 1, TrafficLight.State.GREEN);

		generateTrafficLight(12, 2, TrafficLight.State.RED);
		generateTrafficLight(11, 2, TrafficLight.State.GREEN);
		generateTrafficLight(16, 2, TrafficLight.State.GREEN);
		generateTrafficLight(15, 2, TrafficLight.State.RED);

	}

}
