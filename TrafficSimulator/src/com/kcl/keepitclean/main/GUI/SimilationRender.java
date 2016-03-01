/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import com.kcl.keepitclean.main.policy.Policy;
import com.kcl.keepitclean.main.roadnetwork.road.*;
import com.kcl.keepitclean.main.roadnetwork.laneSection.*;
import com.kcl.keepitclean.main.roadnetwork.trafficLight.*;
import com.kcl.keepitclean.main.vehicle.*;
import com.kcl.keepitclean.main.simulatorengine.*;
import java.util.List;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLUE;
import javafx.stage.Stage;

/**
 *
 * @author rosiengo
 */
public class SimilationRender extends Application {
    private GraphicsContext gc;
    private SimulatorEngine simulation;
    private List<Road> roads;
    private List<Vehicle> vehicles;
    private List<TrafficLightSection> trafficLights;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        
        
        StackPane root = new StackPane();
       // root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    public static void SimulationRender(GraphicsContext gc, SimulatorEngine simulation)
    {
        
    }
    
    //GENERATE FAKE DATA FOR TESTING
    private void fakeData()
    {
      LaneFactory laneFactory =  new LaneFactory();
      RoadFactory roadFactory =  new RoadFactory(laneFactory);
      VehicleFactory vehicleFactory = new VehicleFactory();
      roads.add(roadFactory.produceRoad("", 5, 5));
      vehicles.add(vehicleFactory.getVehicle(VehicleType.CAR));
	
    }
    
    
    public void render() {
      Platform.runLater(new Runnable() {

      @Override
      public void run() {
        clear();
    
      }
    }
    );

  }

    /*Clear canvas before painting updated components*/
    private void clear() {
        gc.clearRect(0, 0, 700, 700);
    }
    //DRAW ROADS
    public  void drawRoads(List<Road> roads)
    {
        
        /* draw list of roads generated from simulation engine 
        parameters: List of Roads, that is provided by simulation engine
        */
       int LANE_SIZE = 3;
       java.awt.Point rightStartPoint;
       java.awt.Point rightEndPoint;
       int rightStartPointX;
       int rightStartPointY;
       int rightEndPointX;
       int rightEndPointY;
       
       
       for (Road road: roads)
       {
        java.awt.Point leftStartPoint = road.getStartCoordinates();
        //Point rightStartPoint = road.getRightStartPoint();
        java.awt.Point leftEndPoint = road.getEndCoordinates();
        
        //calculate right starting point
        rightStartPointX = leftStartPoint.x + road.getNumberOfLanes()* LANE_SIZE;
        rightStartPointY = leftStartPoint.y;
        rightStartPoint = new java.awt.Point(rightStartPointX, rightStartPointY);
        
        //calculate right end point
        rightEndPointX = leftEndPoint.x + road.getNumberOfLanes()* LANE_SIZE;
        rightEndPointY = leftEndPoint.y;
        rightEndPoint = new java.awt.Point(rightEndPointX, rightEndPointY);
        
        //draw road
        gc.setFill(Color.GRAY);
        gc.fillPolygon(new double[]{leftStartPoint.getX(), leftEndPoint.getX(), rightEndPoint.getX(), rightStartPoint.getX()}, new double[]{leftStartPoint.getY(), leftEndPoint.getY(), rightEndPoint.getY(), rightStartPoint.getY()}, 4);
    
       }
    }
    
    //DRAW VEHICLES
    public void drawVehicles(List<Vehicle> vehicles)
    {
      int VEHICLE_WIDTH = 1;
      int VEHICLE_LENGTH = 2;
      java.awt.Point leftStartPoint;
      java.awt.Point rightStartPoint;
      java.awt.Point leftEndPoint;
      java.awt.Point rightEndPoint;
      
      for (Vehicle vehicle:vehicles)
      {
        if (Car.class.isInstance(vehicle)) {
        //    Double angle = vehicle.getDirectionVector().angleVectorDegree();
        //gc.getFill(BLUE);
        //gc.drawImage(img, vehicle., VEHICLE_WIDTH, VEHICLE_WIDTH, VEHICLE_WIDTH);
        //drawRotatedImage(gc, car, angle, (vehicle.getPosition().getX() - car.getWidth() / 2), (vehicle.getPosition().getY() - car.getHeight() / 2));
      } else if (Emergency.class.isInstance(vehicle)) {
         // gc.getFill(RED);
        //Double angle = vehicle.getDirectionVector().angleVectorDegree();
        //drawRotatedImage(gc, bus, angle, (vehicle.getPosition().getX() - bus.getWidth() / 2), (vehicle.getPosition().getY() - bus.getHeight() / 2));
      }
      }
    }
    public void drawTrafficLight(List<TrafficLightSection>  trafficLights)
    {
        
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
