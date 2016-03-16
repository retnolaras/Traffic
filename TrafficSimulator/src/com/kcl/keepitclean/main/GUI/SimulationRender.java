/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import com.kcl.keepitclean.main.policy.Policy;
import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight;
import com.kcl.keepitclean.main.roadnetwork.road.*;
import com.kcl.keepitclean.main.roadnetwork.laneSection.*;
import com.kcl.keepitclean.main.vehicle.*;
import com.kcl.keepitclean.main.simulatorengine.*;
import com.kcl.keepitclean.main.utils.Constant;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;

/**
 *
 * @author rosiengo
 */
public class SimulationRender  implements IRenderer {
    private GraphicsContext gc;
    private SimulatorEngine simulation;
    private List<Road> roads;
    private List<Vehicle> vehicles;
    private List<TrafficLight> trafficLights;
    private List<Junction> junctions;
    private Stage stage;
    Constant constant = new Constant();
   
        
   public SimulationRender (GraphicsContext gc, SimulatorEngine simulation)
   {
       this.gc = gc;
       this.simulation = simulation;
       this.stage = stage;       
       
   }
     
    
    public void render() {
      Platform.runLater(new Runnable() {

      @Override
      public void run() {
        clear();
        roads = simulation.getContext().getRoadList();
        vehicles = simulation.getContext().getVehicleList();
        //junctions = simulation.getContext().getJunctionList().;
                
        System.out.println("GUI- number of vehicles:" + vehicles.size());
        drawRoads();
        drawTrafficLights();
        drawVehicles();
        //drawTest();
        //drawTrafficLights(trafficLights);
    
      }
    }
    );

  }

    /*Clear canvas before painting updated components*/
    public void clear() {
        gc.clearRect(0, 0, 800, 600);
    }
    //DRAW ROADS
    public  void drawRoads()
    {
        
        /* draw list of roads generated from simulation engine 
        parameters: List of Roads, that is provided by simulation engine
        */
      
       int j = 0;
       for (Road road: roads)
       {
        j++;
        System.out.println("Road " + j + " start: (" + road.getStartCoordinates().x + "," +  road.getStartCoordinates().y + ")");
        System.out.println("Road " + j + " end: (" + road.getEndCoordinates().x + "," +  road.getEndCoordinates().y + ")");
        
        //draw road
        gc.setFill(Color.DARKGRAY);
        
        gc.fillPolygon(new double[] {road.getStartCoordinates().x, road.getEndCoordinates().x, road.getEndCoordinates().x, road.getStartCoordinates().x},
                new double[] {road.getStartCoordinates().y, road.getStartCoordinates().y, road.getEndCoordinates().y, road.getEndCoordinates().y},
                4);
        
        
        
        //draw stroke lane line
        for (int i = 1; i<= road.getNumberOfLanes() -1; i++)
        {
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            gc.setLineDashes(new double[]{25d, 10d});
            if (road.getOrientation() == Orientation.HORIZONTAL)
            {
                gc.strokeLine(road.getStartCoordinates().x, 
                          road.getStartCoordinates().y + i * constant.LANE_SIZE * constant.PIXELS, 
                          road.getEndCoordinates().x,
                          road.getStartCoordinates().y + i * constant.LANE_SIZE * constant.PIXELS
                          );
            }
            else if (road.getOrientation() == Orientation.VERTICAL)
            {
                gc.strokeLine(road.getStartCoordinates().x + i * constant.LANE_SIZE * constant.PIXELS, 
                          road.getStartCoordinates().y, 
                          road.getStartCoordinates().x + i * constant.LANE_SIZE * constant.PIXELS,
                          road.getEndCoordinates().y
                          );
            }
                    
        }
        /*gc.fillRect(leftStartPoint.x * constant.PIXELS, 
                    leftStartPoint.y * constant.PIXELS, 
                    road.getNumberOfLanes()* constant.LANE_SIZE * constant.PIXELS, 
                    road.getLengthOfRoad() * constant.LANE_SECTION_HEIGHT * constant.PIXELS 
               
        );*/
        
       }
                       
    }
    
    //TEST - TO BE REMOVED
    public void drawTest()
    {
        gc.setFill(Color.DARKGREY);
        gc.fillRect(350, 0, 60,900);
        gc.setFill(Color.DARKGREY);
        gc.fillRect(100, 250, 600,60);
        gc.setFill(Color.RED);
        gc.fillRect(370, 0, 20, 20);
        gc.setFill(Color.BLUE);
        gc.fillRect(370, 30, 20, 20);
    }
    
    //DRAW VEHICLES
    public void drawVehicles()
    {
        
      for (Vehicle vehicle:vehicles)
      {
        if (Car.class.isInstance(vehicle)) 
        //    Double angle = vehicle.getDirectionVector().angleVectorDegree();
        //gc.getFill(BLUE);
          gc.setFill(Color.BLUE);
        else if (Emergency.class.isInstance(vehicle))
           gc.setFill(Color.RED);
        
        gc.fillRect(vehicle.getAxom().x * constant.PIXELS, 
                    vehicle.getAxom().y * constant.PIXELS, 
                    constant.VEHICLE_WIDTH * constant.PIXELS, 
                    constant.VEHICLE_HEIGHT * constant.PIXELS);
      }
      
    }
    
    //DRAW TRAFFIC LIGHT
    public void drawTrafficLights(){
        
        /* test data */
        TrafficLight trafficLight1 = new TrafficLight(roads, roads);
        trafficLight1.setState(Color.GREEN);
        trafficLight1.setColour(Color.GREEN);
        trafficLight1.setTrafficLightCoordinate(new Point(410,200));
        
        trafficLights = new ArrayList<>();
        
        trafficLights.add(trafficLight1);
        /* end test data */
        
        
        for (TrafficLight trafficLight:trafficLights)
        {
            if (trafficLight.getColour() == Color.RED)
                gc.setFill(Color.RED);
            else 
                gc.setFill(Color.GREY);
            gc.fillOval(trafficLight.getTrafficLightCoordinate().x - 30, trafficLight.getTrafficLightCoordinate().y, 10, 10);
            
            if (trafficLight.getColour() == Color.YELLOW )
                gc.setFill(Color.YELLOW);
            else
                gc.setFill(Color.GREY);
            gc.fillOval(trafficLight.getTrafficLightCoordinate().x - 20, trafficLight.getTrafficLightCoordinate().y, 10, 10);
            
            if (trafficLight.getColour() == Color.GREEN )
                gc.setFill(Color.GREEN);
            else
                gc.setFill(Color.GREY);
            gc.fillOval(trafficLight.getTrafficLightCoordinate().x - 10, trafficLight.getTrafficLightCoordinate().y, 10, 10);
                
        }
        /* TEST
        gc.setFill(Color.RED);
        gc.fillOval(400,200,10,10);
        gc.setFill(Color.YELLOW);
        gc.fillOval(390, 200, 10, 10);
        gc.setFill(Color.GREEN);
        gc.fillOval(380, 200, 10, 10);  
        */
        
    }
        
 }
    

    

