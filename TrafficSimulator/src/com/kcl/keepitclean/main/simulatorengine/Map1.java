/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.simulatorengine;

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
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author rosiengo
 */
public class Map1 {
    
    Road road;
    ArrayList<Road> roads = new ArrayList();
    ArrayList<Junction> junctions = new ArrayList();
    ArrayList<TrafficLight> trafficLights = new ArrayList();
    Constant constant = new Constant();
    RoadFactory roadFactory = new RoadFactory(new LaneFactory());
    
    
    public Map1()
    {
        generateRoads();
        generateJunctions();
        generateTrafficLights();
    }
       
    public ArrayList<Junction> getJunctions()
    {
        return junctions;
    }
    public List<Road> getRoads()
    {
        return roads;
    }
    public List<TrafficLight> getTrafficLights()
    {
        return trafficLights;
    }
    private void generateRoads() {
        
        RoadFactory roadFactory = new RoadFactory(new LaneFactory());	
        Point startPoint = new Point(0,0);
        Point endPoint = new Point();
        int length = 0;
        int lanes = 0;
        
        //road[0]: top road - 01- horizontal
        generateRoad(new Point(0,0), 20, 1, Orientation.RIGHT_HORIZONTAL);
        ((ListOfListsRoadImpl) roads.get(0)).setJuctionEndCoordinates(new Point(roads.get(0).getEndCoordinates().x, roads.get(0).getStartCoordinates().y));
        //road[1]: top road  02- horizontal 
        generateRoad(new Point(0,constant.LANE_SIZE*constant.PIXELS), 20, 1, Orientation.LEFT_HORIZONTAL);
        ((ListOfListsRoadImpl) roads.get(1)).setJuctionStartCoordinates(new Point(roads.get(1).getEndCoordinates().x, roads.get(1).getEndCoordinates().y));
        
        //road 2 - first vertical road - 01
       
        generateRoad(new Point(roads.get(1).getEndCoordinates().x, roads.get(1).getEndCoordinates().y), 15, 1, Orientation.UP_VERTICAL);
        ((ListOfListsRoadImpl) roads.get(2)).setJuctionEndCoordinates(new Point(roads.get(2).getStartCoordinates().x, 
                                                roads.get(2).getStartCoordinates().y));
        
        ((ListOfListsRoadImpl) roads.get(2)).setJuctionStartCoordinates(new Point(roads.get(2).getStartCoordinates().x, 
                                                roads.get(2).getEndCoordinates().y));
        
        //road 3- first vertical road 02
        generateRoad(new Point(roads.get(2).getStartCoordinates().x + 16, roads.get(2).getStartCoordinates().y), 15, 1, Orientation.DOWN_VERTICAL);
        ((ListOfListsRoadImpl) roads.get(3)).setJuctionEndCoordinates(new Point(roads.get(3).getEndCoordinates().x, 
                                                roads.get(3).getEndCoordinates().y));
        ((ListOfListsRoadImpl) roads.get(3)).setJuctionStartCoordinates(new Point(roads.get(3).getEndCoordinates().x, 
                                                roads.get(3).getStartCoordinates().y));
        
        //road[4]: top road 03
        startPoint.x = roads.get(3).getEndCoordinates().x ;
        startPoint.y = 0 ;
        
        generateRoad(new Point(startPoint.x, startPoint.y), 20, 1, Orientation.RIGHT_HORIZONTAL) ;
        
        ((ListOfListsRoadImpl) roads.get(4)).setJuctionStartCoordinates(new Point(roads.get(4).getStartCoordinates().x, 
                                                roads.get(4).getStartCoordinates().y));
        
        //road[5] - top road 04
        startPoint.x = roads.get(3).getEndCoordinates().x ;
        startPoint.y = roads.get(3).getStartCoordinates().y - constant.LANE_SECTION_HEIGHT * constant.PIXELS -4;
        //startPoint.x = roads.get(3).getEndCoordinates().x ;
        //startPoint.y = roads.get(3).getStartCoordinates().y - constant.LANE_SECTION_HEIGHT * constant.PIXELS;
        
        generateRoad(new Point(startPoint.x, startPoint.y), 20, 1, Orientation.LEFT_HORIZONTAL) ;
        ((ListOfListsRoadImpl) roads.get(5)).setJuctionEndCoordinates(new Point(roads.get(5).getStartCoordinates().x, 
                                                roads.get(5).getEndCoordinates().y));
        
        //road 6- first middle road 01
        startPoint.x = 0;
        startPoint.y = roads.get(2).getEndCoordinates().y;
        generateRoad(new Point(startPoint.x,startPoint.y),20,1,Orientation.RIGHT_HORIZONTAL);
        ((ListOfListsRoadImpl) roads.get(6)).setJuctionEndCoordinates(new Point(roads.get(6).getEndCoordinates().x, 
                                                roads.get(6).getStartCoordinates().y));
        
        //road 7- first middleroad 02
        startPoint.x = 0;
        startPoint.y = roads.get(6).getStartCoordinates().y + constant.LANE_SIZE* constant.PIXELS;
        generateRoad(new Point(startPoint.x, startPoint.y),20,1,Orientation.LEFT_HORIZONTAL);
        
        ((ListOfListsRoadImpl) roads.get(7)).setJuctionStartCoordinates(new Point(roads.get(7).getEndCoordinates().x, 
                                                roads.get(5).getEndCoordinates().y));
               //road 8- first middle road 03
        startPoint.x = roads.get(3).getEndCoordinates().x;
        startPoint.y = roads.get(3).getEndCoordinates().y;
        generateRoad(new Point(startPoint.x,startPoint.y),20,1,Orientation.RIGHT_HORIZONTAL);
        
        ((ListOfListsRoadImpl) roads.get(8)).setJuctionStartCoordinates(new Point(roads.get(8).getStartCoordinates().x, 
                                                roads.get(5).getStartCoordinates().y));
        
        //road 9- first middleroad 04
        startPoint.x = roads.get(8).getStartCoordinates().x;
        startPoint.y = roads.get(8).getStartCoordinates().y + constant.LANE_SIZE* constant.PIXELS;
        generateRoad(new Point(startPoint.x, startPoint.y),20,1,Orientation.LEFT_HORIZONTAL);
        ((ListOfListsRoadImpl) roads.get(9)).setJuctionEndCoordinates(new Point(roads.get(9).getStartCoordinates().x, 
                                                roads.get(9).getEndCoordinates().y));
        
        //road 10 - first vertical road - 03
       
        generateRoad(new Point(roads.get(7).getEndCoordinates().x, roads.get(7).getEndCoordinates().y), 15, 1, Orientation.UP_VERTICAL);
        //road 11- first vertical road 04
        generateRoad(new Point(roads.get(10).getStartCoordinates().x + constant.LANE_SIZE* constant.PIXELS, 
                    roads.get(10).getStartCoordinates().y), 15, 1, Orientation.DOWN_VERTICAL);
        
        /*
        //road 12- second middle road 01
        startPoint.x = 0;
        startPoint.y = roads.get(10).getEndCoordinates().y;
        generateRoad(new Point(startPoint.x,startPoint.y),20,1,Orientation.RIGHT_HORIZONTAL);
        //road 13- second middleroad 02
        startPoint.x = 0;
        startPoint.y = roads.get(12).getEndCoordinates().y;
        generateRoad(new Point(startPoint.x, startPoint.y),20,1,Orientation.LEFT_HORIZONTAL);
        
        //road 14 - first vertical road - 03
       
        generateRoad(new Point(roads.get(13).getEndCoordinates().x, roads.get(13).getEndCoordinates().y), 15, 1, Orientation.UP_VERTICAL);
        //road 15- first vertical road 04
        generateRoad(new Point(roads.get(14).getEndCoordinates().x,
                    roads.get(14).getStartCoordinates().y), 15, 1, Orientation.DOWN_VERTICAL);
        
        */
        
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
       if (orientation == Orientation.HORIZONTAL ||
           orientation == Orientation.LEFT_HORIZONTAL ||
           orientation == Orientation.RIGHT_HORIZONTAL) //HORIZONTAL
       {
           endPoint.x = startPoint.x + road.getLengthOfRoad()* constant.LANE_SECTION_HEIGHT* constant.PIXELS;
           endPoint.y = startPoint.y + road.getNumberOfLanes() * constant.LANE_SIZE * constant.PIXELS; 
           
       }
       
       else if (orientation == Orientation.VERTICAL ||
               orientation == Orientation.DOWN_VERTICAL ||
               orientation == Orientation.UP_VERTICAL) //VERTICAL
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
       
       if (orientation == Orientation.HORIZONTAL ||
           orientation == Orientation.LEFT_HORIZONTAL ||
           orientation == Orientation.RIGHT_HORIZONTAL) //HORIZONTAL
       {
           length = Math.round((endPoint.x - startPoint.x)/(constant.LANE_SECTION_HEIGHT * constant.PIXELS));
           lanes = Math.round((endPoint.y - startPoint.y)/(constant.LANE_SIZE * constant.PIXELS));
       }
       else if (orientation == Orientation.VERTICAL ||
               orientation == Orientation.DOWN_VERTICAL ||
               orientation == Orientation.UP_VERTICAL) //VERTICAL
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
       
       roadsGoingIntoJunction.add(roads.get(5));
       roadsGoingIntoJunction.add(roads.get(2));
       roadsGoingIntoJunction.add(roads.get(0));
       
       roadsLeavingJunction.add(roads.get(4));
       roadsLeavingJunction.add(roads.get(3));
       roadsLeavingJunction.add(roads.get(1));
       
       Junction junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
       System.out.println("Junction Coordinate 0: " + junction.getCoordinates().get(0));
       System.out.println("Junction Coordinate 1: " + junction.getCoordinates().get(1));
       System.out.println("Junction Coordinate 2: " + junction.getCoordinates().get(2));
       
       System.out.println("Junction Coordinate 3: " + junction.getCoordinates().get(3));
       
       
       junctions.add(junction);  //junction (0)
        
         
    }
    
    private void generateTrafficLights()
    {
        TrafficLight trafficLight;
        trafficLight = new TrafficLight(roads.get(2), junctions.get(0));
        trafficLight.setState(State.RED);
        trafficLight.setTrafficLightCoordinate(new Point(trafficLight.getRoad().getStartCoordinates().x + 10, 
                                                         trafficLight.getRoad().getStartCoordinates().y + 10));
        trafficLights.add(trafficLight);
        
        
        trafficLight = new TrafficLight(roads.get(0), junctions.get(0));
        trafficLight.setState(State.GREEN);
        trafficLight.setTrafficLightCoordinate(new Point(trafficLight.getRoad().getEndCoordinates().x, 
                                                         trafficLight.getRoad().getEndCoordinates().y - 6));
        trafficLights.add(trafficLight);
        
    }
    
}
