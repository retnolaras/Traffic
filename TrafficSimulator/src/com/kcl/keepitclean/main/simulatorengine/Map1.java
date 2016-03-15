/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.simulatorengine;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
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
public class Map1 {
    
    Road road;
    ArrayList<Road> roads = new ArrayList();
    Constant constant = new Constant();
    RoadFactory roadFactory = new RoadFactory(new LaneFactory());	
    
    public Map1()
    {
        generateRoads();
    }
       
    public List<Road> getRoads()
    {
        return roads;
    }
    
    private void generateRoads() {
        
        RoadFactory roadFactory = new RoadFactory(new LaneFactory());	
        Point startPoint = new Point(10,0);
        Point endPoint = new Point();
        int length = 0;
        int lanes = 0;
        
        //road[0]: top road - horizontal 
        generateRoad(startPoint, 65, 2, 1);
        
        //road[1]: bottom road - horizontal 
        startPoint.x = 10;
        startPoint.y = 568;
        generateRoad(startPoint, 65, 2, 1);
        
        //road[2]: middle road1 - horizontal 
        startPoint.x = 10;
        startPoint.y = 300;
        generateRoad(startPoint, 30, 2, 1);
        
        //road[3]: middle road2 - horizontal 
        startPoint.x = 500;
        startPoint.y = 350;
        generateRoad(startPoint, 20, 2, 1);
        
        //road[4]: first Vertical Road- joint with road 0, road 1, road 2
        startPoint.x = roads.get(2).getEndCoordinates().x;
        startPoint.y = roads.get(0).getEndCoordinates().y;
        lanes = 2;
        endPoint.x = roads.get(2).getEndCoordinates().x + lanes * constant.LANE_SIZE * constant.PIXELS;
        endPoint.y = roads.get(1).getStartCoordinates().y;
        generateRoad(startPoint, endPoint, 2);
        
        //road[5] : second vertical road - joint with road 0, road 1, road 3
        lanes = 2;
        startPoint.y = roads.get(0).getEndCoordinates().y;
        startPoint.x = roads.get(3).getStartCoordinates().x - lanes * constant.LANE_SIZE * constant.PIXELS;
        endPoint.x = roads.get(3).getStartCoordinates().x;
        endPoint.y = roads.get(1).getStartCoordinates().y;
        
	}
    
    private void generateRoad(Point startPoint, int length, int lanes, int orientation)
    {
       /* this method generates a road with given start point and road size */
       Point endPoint = new Point();
       Road road = roadFactory.produceRoad("listoflistsroadimpl", length, lanes);
       if (orientation == 1) //HORIZONTAL
       {
           endPoint.x = startPoint.x + road.getLengthOfRoad()* constant.LANE_SECTION_HEIGHT* constant.PIXELS;
           endPoint.y = startPoint.y + road.getNumberOfLanes() * constant.LANE_SIZE * constant.PIXELS; 
           
       }
       
       else if (orientation == 2) //VERTICAL
       {
        endPoint.x = startPoint.x + road.getNumberOfLanes() * constant.LANE_SIZE * constant.PIXELS; 
        endPoint.y = startPoint.y + road.getLengthOfRoad()* constant.LANE_SECTION_HEIGHT* constant.PIXELS;
       }
       
       
       ((ListOfListsRoadImpl) road).setStartCoordinate(new Point(startPoint.x, startPoint.y));
       ((ListOfListsRoadImpl) road).setEndCoordinate(new Point(endPoint.x, endPoint.y));
       ((ListOfListsRoadImpl) road).setOrientation(orientation);
               
       roads.add(road);
        
    }
    
    private void generateRoad(Point startPoint, Point endPoint, int orientation)
    {
       /* this method generates a road with given startPoint and endPoint */
       int length = 0;
       int lanes = 0;
       
       if (orientation == 1)  //HORIZONTAL 
       {
           length = Math.round((endPoint.x - startPoint.x)/(constant.LANE_SECTION_HEIGHT * constant.PIXELS));
           lanes = Math.round((endPoint.y - startPoint.y)/(constant.LANE_SIZE * constant.PIXELS));
       }
       else if (orientation == 2) //VERTICAL 
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
        
    }
    
    private void generateTrafficLight()
    {
        
    }
    
}
