/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import com.kcl.keepitclean.main.policy.Policy;
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
/*
TO DO LIST:
VALIDATION FOR TEXTFIELDS
*/
public class InitScreen extends Application {
    private SimulationRender renderer;
    private GUIComponents pscene;
    private SimulatorEngine simulation;
    private SimulationSettings simulationSettings;
    
    @Override
    public void start(Stage primaryStage) {
        
        pscene = new GUIComponents();
        
        
        
        pscene.btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Disable Start button and enable Terminate button
                pscene.btnTerminate.setDisable(false);
                pscene.btnStart.setDisable(true);
                
                //get simulation settings
                simulationSettings = new SimulationSettings(pscene.getSelectedPolicy(), pscene.getMinSpeedLimitSettings(),
                                         pscene.getMaxSpeedLimitSettings(), pscene.getMinTrafficLightSettings(), pscene.getMaxTrafficLightSettings(), 
                                         pscene.getTrafficDesity(), pscene.getSessionDuration());
                
                //pass simulation settings to simulation engin
                //Start the simulation Session
                simulation = new SimulatorEngine(simulationSettings);   
                renderer = new SimulationRender(pscene.gcontext, simulation);
                simulation.setRenderer(renderer);
                simulation.startSimulation();
                
                //roads.add(roadFactory.produceRoad("", 100, 100));
                //renderer.render();
            
            }
      
        }
        );
        
        pscene.btnTerminate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                //Terminate Simulation Session
                //Show Report
                //Enable Start button
                pscene.btnStart.setDisable(false);
                //Disable Terminate Button
                pscene.btnTerminate.setDisable(true);
                renderer.clear();
                
            }
        }
        
        );

                
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
