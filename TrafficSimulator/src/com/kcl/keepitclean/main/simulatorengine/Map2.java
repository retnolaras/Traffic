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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 *
 * @author rosiengo
 */
public class Map2 {

    Road road;
    ArrayList<Road> roads = new ArrayList();
    ArrayList<Junction> junctions = new ArrayList();
    ArrayList<TrafficLight> trafficLights = new ArrayList();
    Constant constant = new Constant();
    RoadFactory roadFactory = new RoadFactory(new LaneFactory());
    private Map<Point, Road > startPointMap = new HashMap<>() ;


    public Map2()
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
        generateRoad(new Point(30,270), 80, 1, Orientation.RIGHT_HORIZONTAL);
        ((ListOfListsRoadImpl) roads.get(0)).setJuctionEndCoordinates(new Point(roads.get(0).getEndCoordinates().x, roads.get(0).getStartCoordinates().y));
        //road[1]: top road  02- horizontal
        generateRoad(new Point(30,270+ constant.LANE_SIZE*constant.PIXELS), 80, 1, Orientation.LEFT_HORIZONTAL);
        ((ListOfListsRoadImpl) roads.get(1)).setJuctionStartCoordinates(new Point(roads.get(1).getEndCoordinates().x, roads.get(1).getEndCoordinates().y));

        //road 2 - first vertical road - 01

        generateRoad(new Point(roads.get(1).getEndCoordinates().x, roads.get(1).getEndCoordinates().y), 60, 1, Orientation.UP_VERTICAL);
        ((ListOfListsRoadImpl) roads.get(2)).setJuctionEndCoordinates(new Point(roads.get(2).getStartCoordinates().x,
                                                roads.get(2).getStartCoordinates().y));

        ((ListOfListsRoadImpl) roads.get(2)).setJuctionStartCoordinates(new Point(roads.get(2).getStartCoordinates().x,
                                                roads.get(2).getEndCoordinates().y));

        //road 3- first vertical road 02
        generateRoad(new Point(roads.get(2).getStartCoordinates().x + 16, roads.get(2).getStartCoordinates().y), 60, 1, Orientation.DOWN_VERTICAL);
        ((ListOfListsRoadImpl) roads.get(3)).setJuctionEndCoordinates(new Point(roads.get(3).getEndCoordinates().x,
                                                roads.get(3).getEndCoordinates().y));
        ((ListOfListsRoadImpl) roads.get(3)).setJuctionStartCoordinates(new Point(roads.get(3).getEndCoordinates().x,
                                                roads.get(3).getStartCoordinates().y));

        //road[4]: top road 03
        startPoint.x = roads.get(3).getEndCoordinates().x ;
        startPoint.y = 270 ;

        generateRoad(new Point(startPoint.x, startPoint.y), 80, 1, Orientation.RIGHT_HORIZONTAL) ;

        ((ListOfListsRoadImpl) roads.get(4)).setJuctionStartCoordinates(new Point(roads.get(4).getStartCoordinates().x,
                                                roads.get(4).getStartCoordinates().y));
        ((ListOfListsRoadImpl) roads.get(4)).setJuctionEndCoordinates(new Point(roads.get(4).getEndCoordinates().x,
                                                roads.get(4).getStartCoordinates().y));

        //road[5] - top road 04
        startPoint.x = roads.get(4).getStartCoordinates().x ;
        startPoint.y = roads.get(4).getEndCoordinates().y ;
        //startPoint.x = roads.get(3).getEndCoordinates().x ;
        //startPoint.y = roads.get(3).getStartCoordinates().y - constant.LANE_SECTION_HEIGHT * constant.PIXELS;

        generateRoad(new Point(startPoint.x, startPoint.y), 80, 1, Orientation.LEFT_HORIZONTAL) ;
        ((ListOfListsRoadImpl) roads.get(5)).setJuctionEndCoordinates(new Point(roads.get(5).getStartCoordinates().x,
                                                roads.get(5).getEndCoordinates().y));
        ((ListOfListsRoadImpl) roads.get(5)).setJuctionStartCoordinates(new Point(roads.get(5).getEndCoordinates().x,
                                                roads.get(5).getEndCoordinates().y));
        
        //road 6 - first vertical road - 03
        startPoint.x = roads.get(0).getEndCoordinates().x;
        startPoint.y = roads.get(0).getStartCoordinates().y - 60* constant.LANE_SECTION_HEIGHT*constant.PIXELS;

        generateRoad(new Point(startPoint.x, startPoint.y), 60, 1, Orientation.UP_VERTICAL);
        ((ListOfListsRoadImpl) roads.get(6)).setJuctionStartCoordinates(new Point(roads.get(6).getStartCoordinates().x,
                                                roads.get(6).getEndCoordinates().y));

        

        //road 7- first vertical road 04
        startPoint.x = roads.get(6).getEndCoordinates().x;
        startPoint.y = roads.get(6).getStartCoordinates().y;
        
        generateRoad(new Point(startPoint.x, startPoint.y), 60, 1, Orientation.DOWN_VERTICAL);
        ((ListOfListsRoadImpl) roads.get(7)).setJuctionEndCoordinates(new Point(roads.get(7).getEndCoordinates().x,
                                                roads.get(7).getEndCoordinates().y));
        



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
       
       //assign all roads their indexes in the list
       for( int i=0; i < roads.size(); i++){
    	   roads.get(i).setIndex(i);
       }
       
       for( int i=0; i < roads.size(); i++){
    	   Point point= new Point();
    	   point=roads.get(i).getStartCoordinates();
    	   startPointMap.put(point, roads.get(i));
    	   
       }
    
       
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

       generateJunction(new int[] {0,7,5,2}, new int[]{6,4,3,1});  //J0

       
    }

    private void generateJunction(int[] in, int[] out){
       ArrayList<Road> roadsGoingIntoJunction = new ArrayList();
       ArrayList<Road> roadsLeavingJunction = new ArrayList();
       Junction junction;

        for (int i = 0; i <in.length; i++){
            roadsGoingIntoJunction.add(roads.get(in[i]));


        }
        for (int j = 0; j <out.length; j++){
            roadsLeavingJunction.add(roads.get(out[j]));

        }

       junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);

       junctions.add(junction);

       for (int i = 0; i <in.length; i++){
           roads.get(in[i]).setEndJunction(junction);

       }

       for (int j = 0; j <out.length; j++){
           roads.get(in[j]).setStartJunction(junction);

       }

    }
    private void generateTrafficLights()
    {
        generateTrafficLight(0,0,State.GREEN);
        generateTrafficLight(7,0,State.RED);
        generateTrafficLight(2,0, State.RED);
        generateTrafficLight(5,0,State.GREEN);

        


    }

    private void generateTrafficLight(int roadIndex, int junctionIndex, State state){
        TrafficLight trafficLight;
        trafficLight = new TrafficLight(roads.get(roadIndex), junctions.get(junctionIndex));
        trafficLight.setState(state);
        trafficLight.setTrafficLightCoordinate(new Point(trafficLight.getRoad().getJuctionEndCoordinates().x ,
                                                         trafficLight.getRoad().getJuctionEndCoordinates().y));
        trafficLights.add(trafficLight);
    }

	public Map<Point, Road > getStartPointMap() {
		return startPointMap;
	}

	public void setStartPointMap(Map<Point, Road > startPointMap) {
		this.startPointMap = startPointMap;
	}
    
    

}
