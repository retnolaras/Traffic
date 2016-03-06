/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;
import com.kcl.keepitclean.main.simulatorengine.SimulatorEngine;
import com.kcl.keepitclean.main.vehicle.VehicleFactory;
import com.kcl.keepitclean.main.vehicle.VehicleType;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author rosiengo
 */
public class InitScreen extends Application {
    private SimulationRender renderer;
    private GUIComponents pscene;
    private SimulatorEngine simulation;
    
    @Override
    public void start(Stage primaryStage) {
        
        pscene = new GUIComponents();
        simulation = new SimulatorEngine(null);
        
        renderer = new SimulationRender(pscene.gcontext, simulation);
        //roads.add(roadFactory.produceRoad("", 100, 100));
        renderer.render();

                
        Scene scene = new Scene(pscene,1300 ,700 );
        
        primaryStage.setTitle("KeepItClean- Traffic Simulation");
        primaryStage.setScene(scene);
        
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
