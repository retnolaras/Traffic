/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import com.kcl.keepitclean.main.policy.Policy;
import com.kcl.keepitclean.main.roadnetwork.road.*;
import com.kcl.keepitclean.main.roadnetwork.laneSection.*;
import com.kcl.keepitclean.main.vehicle.*;
import com.kcl.keepitclean.main.simulatorengine.*;
import com.kcl.keepitclean.main.utils.Constant;
import java.awt.Point;
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
    private Stage stage;
    Constant constant = new Constant();
   
        
   public SimulationRender (GraphicsContext gc, SimulatorEngine simulation)
   {
       this.gc = gc;
       this.simulation = simulation;
       this.stage = stage;       
   }
    
     
    
    //GENERATE FAKE DATA FOR TESTING
    //TO BE REMOVED
    private void fakeData()
    {
      LaneFactory laneFactory =  new LaneFactory();
      RoadFactory roadFactory =  new RoadFactory(laneFactory);
      VehicleFactory vehicleFactory = new VehicleFactory();
      roads.add(roadFactory.produceRoad("", 100, 100));
      vehicles.add(vehicleFactory.getVehicle(VehicleType.CAR));
	
    }
    
    
    public void render() {
      Platform.runLater(new Runnable() {

      @Override
      public void run() {
        clear();
        roads = simulation.getContext().getRoadList();
        vehicles = simulation.getContext().getVehicleList();
        drawRoads();
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
      
       
       for (Road road: roads)
       {
        Point leftStartPoint = road.getStartCoordinates();
        
        
        //draw road
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(leftStartPoint.x * constant.PIXELS, leftStartPoint.y * constant.PIXELS, road.getNumberOfLanes()* constant.LANE_SIZE, road.getLengthOfRoad() * constant.LANE_SECTION_HEIGHT );
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
      Point leftStartPoint;
      Point rightStartPoint;
      Point leftEndPoint;
      Point rightEndPoint;
      
      for (Vehicle vehicle:vehicles)
      {
        if (Car.class.isInstance(vehicle)) 
        //    Double angle = vehicle.getDirectionVector().angleVectorDegree();
        //gc.getFill(BLUE);
          gc.setFill(Color.BLUE);
        else if (Emergency.class.isInstance(vehicle))
           gc.setFill(Color.RED);
        
        gc.fillRect(vehicle.getAxom().x * constant.PIXELS, vehicle.getAxom().y * constant.PIXELS, constant.VEHICLE_WIDTH * constant.PIXELS, constant.VEHICLE_HEIGHT * constant.PIXELS);
      }
      
    }
    
    

    
}
