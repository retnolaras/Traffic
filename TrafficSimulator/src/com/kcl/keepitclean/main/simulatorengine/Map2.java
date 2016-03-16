/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.simulatorengine;

import com.kcl.keepitclean.main.roadnetwork.junction.PrePlannedRouteJunction;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
import com.kcl.keepitclean.main.roadnetwork.road.Orientation;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;
import com.kcl.keepitclean.main.utils.Constant;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosiengo
 */
public class Map2 {
    
    Road road;
    ArrayList<Road> roads = new ArrayList();
    Constant constant = new Constant();
    RoadFactory roadFactory = new RoadFactory(new LaneFactory());
    
    
    public Map2()
    {
        generateRoads();
    }
       
    public List<Road> getRoads()
    {
        return roads;
    }
    
    private void generateRoads() {
        
        RoadFactory roadFactory = new RoadFactory(new LaneFactory());	
        Point startPoint = new Point(0,0);
        Point endPoint = new Point();
        ArrayList<Road> roadsGoingIntoJunction = new ArrayList();
        ArrayList<Road> roadsLeavingJunction = new ArrayList();
        int length = 0;
        int lanes = 0;
        
        //road[0]: top road - horizontal 
        generateRoad(new Point(0,0), 30, 2, Orientation.HORIZONTAL);
        //road[0]: top road - horizontal 
        generateRoad(new Point(33* constant.LANE_SECTION_HEIGHT * constant.PIXELS,0), 30, 2, Orientation.HORIZONTAL);
        
        generateRoad(new Point(roads.get(0).getEndCoordinates().x, roads.get(0).getEndCoordinates().y), 20, 2, Orientation.VERTICAL);
        
        PrePlannedRouteJunction junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
        /*
        //road[1]: bottom road - horizontal 
        startPoint.x = 0;
        startPoint.y = 568;
        generateRoad(new Point(startPoint.x, startPoint.y), 63, 2, Orientation.HORIZONTAL);
        //road[2]: middle road1 - horizontal 
        startPoint.x = 0;
        startPoint.y = 350;
        generateRoad(new Point(startPoint.x, startPoint.y), 20, 2, Orientation.HORIZONTAL);
        //road[3]: middle road2 - horizontal 
        startPoint.x = 500;
        startPoint.y = 450;
        generateRoad(new Point(startPoint.x, startPoint.y), 12, 2, Orientation.HORIZONTAL);
        //road[4]: first Vertical Road- joint with road 0, road 1, road 2
        startPoint.x = roads.get(2).getEndCoordinates().x;
        startPoint.y = roads.get(0).getEndCoordinates().y;
        lanes = 2;
        endPoint.x = roads.get(2).getEndCoordinates().x + lanes * constant.LANE_SIZE * constant.PIXELS;
        endPoint.y = roads.get(1).getStartCoordinates().y;
        generateRoad(new Point(startPoint.x, startPoint.y), new Point(endPoint.x, endPoint.y), Orientation.VERTICAL);
        //road[5] : second vertical road - joint with road 0, road 1, road 3
        lanes = 2;
        startPoint.y = roads.get(0).getEndCoordinates().y;
        startPoint.x = roads.get(3).getStartCoordinates().x - lanes * constant.LANE_SIZE * constant.PIXELS;
        endPoint.x = roads.get(3).getStartCoordinates().x;
        endPoint.y = roads.get(1).getStartCoordinates().y;
        generateRoad(new Point(startPoint.x, startPoint.y), new Point(endPoint.x, endPoint.y), Orientation.VERTICAL);
        //road[6]: middle road 3- horizontal
        startPoint.x = 0;
        startPoint.y = 200;
        generateRoad(new Point(startPoint.x, startPoint.y), 63, 2, Orientation.HORIZONTAL);
        //road[7]: vertical road, joint with road 0,1,3,6
        lanes = 2;
        startPoint.y = 0;
        startPoint.x = roads.get(0).getEndCoordinates().x;
        endPoint.x = roads.get(1).getEndCoordinates().x + lanes * constant.LANE_SIZE * constant.PIXELS ;
        endPoint.y = roads.get(1).getEndCoordinates().y ;
        generateRoad(new Point(startPoint.x, startPoint.y), new Point(endPoint.x, endPoint.y), Orientation.VERTICAL);
         */
        
	}
    
    private void generateRoad(Point startPoint, int length, int lanes, Orientation orientation)
    {
       /* this method generates a road with given start point and road size */
       Point endPoint = new Point();
       Road road = roadFactory.produceRoad("listoflistsroadimpl", length, lanes);
       if (orientation == Orientation.HORIZONTAL) //HORIZONTAL
       {
           endPoint.x = startPoint.x + road.getLengthOfRoad()* constant.LANE_SECTION_HEIGHT* constant.PIXELS;
           endPoint.y = startPoint.y + road.getNumberOfLanes() * constant.LANE_SIZE * constant.PIXELS; 
           
       }
       
       else if (orientation == Orientation.VERTICAL) //VERTICAL
       {
        endPoint.x = startPoint.x + road.getNumberOfLanes() * constant.LANE_SIZE * constant.PIXELS; 
        endPoint.y = startPoint.y + road.getLengthOfRoad()* constant.LANE_SECTION_HEIGHT* constant.PIXELS;
       }
       
       
       ((ListOfListsRoadImpl) road).setStartCoordinate(new Point(startPoint.x, startPoint.y));
       ((ListOfListsRoadImpl) road).setEndCoordinate(new Point(endPoint.x, endPoint.y));
       ((ListOfListsRoadImpl) road).setOrientation(orientation);
               
       roads.add(road);
        
    }
    
    private void generateRoad(Point startPoint, Point endPoint, Orientation orientation)
    {
       /* this method generates a road with given startPoint and endPoint */
       int length = 0;
       int lanes = 0;
       
       if (orientation == Orientation.HORIZONTAL)  //HORIZONTAL 
       {
           length = Math.round((endPoint.x - startPoint.x)/(constant.LANE_SECTION_HEIGHT * constant.PIXELS));
           lanes = Math.round((endPoint.y - startPoint.y)/(constant.LANE_SIZE * constant.PIXELS));
       }
       else if (orientation == Orientation.VERTICAL) //VERTICAL 
       {
           lanes = Math.round((endPoint.x - startPoint.x)/(constant.LANE_SECTION_HEIGHT * constant.PIXELS));
           length = Math.round((endPoint.y - startPoint.y)/(constant.LANE_SIZE * constant.PIXELS));
       }
       Road road = roadFactory.produceRoad("listoflistsroadimpl", length, lanes);
       ((ListOfListsRoadImpl) road).setStartCoordinate(new Point(startPoint.x, startPoint.y));
       ((ListOfListsRoadImpl) road).setEndCoordinate(new Point(endPoint.x, endPoint.y));
       ((ListOfListsRoadImpl) road).setOrientation(orientation);
        
       roads.add(road);
       
       
               
    }
    
    private void generateJunctions()
    {
       ArrayList<Road> roadsGoingIntoJunction = new ArrayList();
       ArrayList<Road> roadsLeavingJunction = new ArrayList();
       
       roadsGoingIntoJunction.add(roads.get(0));
       roadsLeavingJunction.add(roads.get(1));
       roadsLeavingJunction.add(roads.get(2));
       PrePlannedRouteJunction junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
        
         
    }
    
    private void generateTrafficLight()
    {
        
    }
    
}
