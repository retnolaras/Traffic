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
import com.kcl.keepitclean.main.session.SessionManager;
import com.kcl.keepitclean.main.simulatorengine.SimulatorEngine;
import com.kcl.keepitclean.main.vehicle.VehicleFactory;
import com.kcl.keepitclean.main.vehicle.VehicleType;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
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
    private static XYChart.Series simulationEngine;
    
    @Override
    public void start(Stage primaryStage) {
        
        pscene = new GUIComponents();
        
        
        
        pscene.btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Disable Start button and enable Terminate button
                
                //get simulation settings
                pscene.blank3.setText("");
                if (!pscene.validateBlank())
                {
                    pscene.blank3.setText("please enter all required values");
                    pscene.blank3.setStyle("-fx-text-fill: red");
                }
                else if ( !pscene.validateRange())
                    {
                        pscene.blank3.setText("Max values must be greater than min values");
                        pscene.blank3.setStyle("-fx-text-fill: red");
                    }
                else
                {
                    simulationSettings = new SimulationSettings(pscene.getSelectedPolicy(), pscene.getMinSpeedLimitSettings(),
                                             pscene.getMaxSpeedLimitSettings(), pscene.getMinTrafficLightSettings(), pscene.getMaxTrafficLightSettings(), 
                                             pscene.getTrafficDesity(), pscene.getSessionDuration());

                    //pass simulation settings to simulation engin
                    //Start the simulation Session
                    simulation = new SimulatorEngine(simulationSettings);   
                    renderer = new SimulationRender(pscene.gcontext, simulation);
                    simulation.setRenderer(renderer);
                    simulation.startSimulation();
                    pscene.btnTerminate.setDisable(false);
                    pscene.btnStart.setDisable(true);
                    
                    pscene.btnDecrease.setDisable(false);
                    pscene.btnIncrease.setDisable(false);
                    pscene.btnPause.setDisable(false);
                    pscene.btnResume.setDisable(false);
                }
                
                
            
            }
      
        }
        );
        
        pscene.btnTerminate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                //Terminate Simulation Session
            	simulation.stopSimulation();
            	simulation = null;
                //Show Report
                //Enable Start button
                pscene.btnStart.setDisable(false);
                //Disable Terminate Button
                pscene.btnTerminate.setDisable(true);
                
                pscene.btnDecrease.setDisable(true);
                pscene.btnIncrease.setDisable(true);
                pscene.btnPause.setDisable(true);
                pscene.btnResume.setDisable(true);
                
                renderer.clear();
                
            }
        }
        
        );

		pscene.btnIncrease.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Terminate Simulation Session
				SessionManager.getInstance().doStepFaster();
			}
		}

		);
        
		pscene.btnDecrease.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Terminate Simulation Session
				SessionManager.getInstance().doStepSlower();
			}
		}

		);
        
		pscene.btnPause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Terminate Simulation Session
				SessionManager.getInstance().pauseSession();
			}
		}

		);
		
		pscene.btnResume.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Terminate Simulation Session
				SessionManager.getInstance().resumeSession();
			}
		}

		);
		
		
        Scene scene = new Scene(pscene,1300 ,700 );
        
        primaryStage.setTitle("KeepItClean- Traffic Simulation");
        primaryStage.setScene(scene);
        
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    
    
    
    private static void createStat(Stage stage) {
		try {
			stage.setTitle("Report");
			final NumberAxis xAxis = new NumberAxis();
	        final NumberAxis yAxis = new NumberAxis();
			final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
			xAxis.setLabel("Time");
			yAxis.setLabel("Number of Vehicles");
			
			lineChart.setTitle("Report");
			int xvalue = 10;
			int yvalue = 20;
			Scene scene = new Scene(lineChart,400,400);
			
			 simulationEngine = new XYChart.Series();
			 XYChart.Series seriesAvg = new XYChart.Series();
		     simulationEngine.setName("Volume of Cars");
		     seriesAvg.setName("Number of Cells Crossed");
		       
		     XYChart.Data<Integer, Integer> datathing = new Data<Integer, Integer>((Integer)xvalue,(Integer)yvalue);
		        
		     lineChart.getData().add(simulationEngine);
		     lineChart.getData().add(seriesAvg);
		       
			stage.setScene(scene);
			stage.show();
		        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
