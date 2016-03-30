/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.kcl.keepitclean.main.session.SessionManager;
import com.kcl.keepitclean.main.simulatorengine.SimulationData;
import com.kcl.keepitclean.main.simulatorengine.SimulatorEngine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Main form for the app
 * 
 * @author rosiengo
 */

public class InitScreen extends Application {
	private SimulationRender renderer;
	private GUIComponents pscene;
	private SimulatorEngine simulation;
	private SimulationSettings simulationSettings;
	private SimulationData simulationData = new SimulationData();
	private ArrayList<Boolean> entrancePoints;

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {

		pscene = new GUIComponents();
		


		pscene.btnStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Disable Start button and enable Terminate button
			
				pscene.resetReport();
				getEntrancePoints();
				// get simulation settings
				pscene.blank3.setText("");
				if (!pscene.validateBlank()) {
					pscene.blank3.setText("Please enter all required values");
					pscene.blank3.setStyle("-fx-text-fill: red");
				} else if (!pscene.validateRange()) {
					pscene.blank3.setText("Max values must be greater than min values");
					pscene.blank3.setStyle("-fx-text-fill: red");
				} else {
					simulationSettings = new SimulationSettings(pscene.getSelectedPolicy(),
							pscene.getMinSpeedLimitSettings(), pscene.getMaxSpeedLimitSettings(),
							pscene.getMinTrafficLightSettings(), pscene.getMaxTrafficLightSettings(),
							pscene.getTrafficDesity(), pscene.getSessionDuration(), pscene.getMap(), entrancePoints);

					// pass simulation settings to simulation engine
					// Start the simulation Session
					simulation = new SimulatorEngine(simulationSettings);
					renderer = new SimulationRender(pscene.gcontext, simulation);
					simulation.setRenderer(renderer);
					simulation.startSimulation();
					pscene.btnStart.setDisable(true);

					pscene.txtDensity.setDisable(true);
					pscene.txtDuration.setDisable(true);
					pscene.txtMap.setDisable(true);

					pscene.btnTerminate.setDisable(false);
					pscene.btnDecrease.setDisable(false);
					pscene.btnIncrease.setDisable(false);
					pscene.btnPause.setDisable(false);
					pscene.btnResume.setDisable(false);
					// simulationData =
					// simulation.getContext().getSimulationData();
					
					pscene.cb1.setDisable(true);
					pscene.cb2.setDisable(true);
					pscene.cb3.setDisable(true);
					pscene.cb4.setDisable(true);
					pscene.cb5.setDisable(true);
					pscene.cb6.setDisable(true);
				

					//pscene.updateReport(simulationData);
					pscene.resetReport();

				}

			}

			private void getEntrancePoints() {
				entrancePoints= new ArrayList<>();
				if (!pscene.cb1.isDisabled() && pscene.cb1.isSelected() ){
					entrancePoints.add(true);
				}
				else{
					entrancePoints.add(false);
				}
				if (!pscene.cb2.isDisabled() && pscene.cb2.isSelected() ){
					entrancePoints.add(true);
				}
				else{
					entrancePoints.add(false);
				}
				if (!pscene.cb3.isDisabled() && pscene.cb3.isSelected() ){
					entrancePoints.add(true);
				}
				else{
					entrancePoints.add(false);
				}
				if (!pscene.cb4.isDisabled() && pscene.cb4.isSelected() ){
					entrancePoints.add(true);
				}
				else{
					entrancePoints.add(false);
				}
				if (!pscene.cb5.isDisabled() && pscene.cb5.isSelected() ){
					entrancePoints.add(true);
				}
				else{
					entrancePoints.add(false);
				}
				if (!pscene.cb6.isDisabled() && pscene.cb6.isSelected() ){
					entrancePoints.add(true);
				}
				else{
					entrancePoints.add(false);
				}
				
			}

		});

		pscene.btnTerminate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Terminate Simulation Session
				simulationData.setSimulationData(simulation);
				simulation.stopSimulation();

				simulation = null;
				// Show Report
				// Enable Start button
				pscene.btnStart.setDisable(false);

				pscene.txtDensity.setDisable(false);
				pscene.txtDuration.setDisable(false);
				pscene.txtMap.setDisable(false);

				// Disable Terminate Button
				pscene.btnTerminate.setDisable(true);

				pscene.btnDecrease.setDisable(true);
				pscene.btnIncrease.setDisable(true);
				pscene.btnPause.setDisable(true);
				pscene.btnResume.setDisable(true);
			
				pscene.cb1.setDisable(false);
				pscene.cb2.setDisable(false);
				pscene.cb3.setDisable(false);
				pscene.cb4.setDisable(false);
			
				if(pscene.getMap() == "Town"){

				pscene.cb5.setDisable(false);
				pscene.cb6.setDisable(false);
				}
				pscene.updateReport(simulationData);


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

		Scene scene = new Scene(pscene, 1300, 700);

		primaryStage.setTitle("KeepItClean - Traffic Simulation");
		primaryStage.setScene(scene);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	String eventstring = event.getEventType().toString();
		    	if ("WINDOW_CLOSE_REQUEST".equals(eventstring)) {
		    		if(simulation != null){
		    			simulation.stopSimulation();
		    		}
		    		primaryStage.close();            
		        }
		    }
		});
		
		
		if (pscene.getMap() == "Town"){
			
			pscene.cb5.setDisable(false);
			pscene.cb6.setDisable(false);

			
		
	}
		
	
		
		if (pscene.getMap() == "Junction"){
			
			pscene.cb5.setDisable(true);
			pscene.cb6.setDisable(true);

			
		
	}
	    
		 pscene.txtMap.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
		   
				@Override
				public void changed(ObservableValue observable, Object oldValue, Object newValue) {
					if (newValue.toString() == "Town"){
						pscene.cb5.setDisable(false);
					     pscene.cb6.setDisable(false);
					}
					if (newValue.toString() == "Junction"){
						pscene.cb5.setDisable(true);
					     pscene.cb6.setDisable(true);
					   
					}
					
				}
		    });
		 
//		ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
//	    
//
//			@Override
//			public void actionPerformed(java.awt.event.ActionEvent e) {
//
//
//                String s = (String) pscene.txtMap.getValue().toString();//get the selected item
//
//                switch (s) {//check for a match
//                    case "Town":
//                    	pscene.cb5.setDisable(false);
//            			pscene.cb6.setDisable(false);
//                        break;
//                    case "Junction":
//                    	pscene.cb5.setDisable(true);
//            			pscene.cb6.setDisable(true);
//
//                        break;
//                  
//                    default:
//                    	pscene.cb5.setDisable(false);
//            			pscene.cb6.setDisable(false);
//                        break;
//               				
//			}
//			}
//	        };
	     
	     
		// primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
