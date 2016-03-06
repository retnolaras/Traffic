/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author rosiengo
 */
public class GUIComponents extends BorderPane{
  protected StackPane settingsPane;
  protected StackPane simulationPane;
  public final Canvas canvas = new Canvas(800,600);
  public GraphicsContext gcontext;
    
  protected HBox policyOptionsBox;  
  protected VBox policySettings;
  protected Label lblSelectPolicy = new Label("Select Policy Option:");
  protected ToggleGroup policiesOptions;
  protected RadioButton defaultPolicy = new RadioButton("Default");
  protected RadioButton randomPolicy = new RadioButton("Random");
  protected RadioButton customisedPolicy = new RadioButton("Customised") ;
  
  protected GridPane policyPane;
  protected Label lblMin = new Label("Min");
  protected Label lblMax = new Label("Max");
  protected Label lblStraight = new Label("Straight Road Speed Limit:");
  protected Label lblJunction = new Label("Junction Speed Limit:");
  protected Label lblCurvy = new Label("Curvy Road Speed Limit:");
  protected Label lblGreen = new Label("Green Light Duration:");
  protected Label lblAmber = new Label("Amber Light Duration:");
  protected Label lblRed = new Label("Red Light Duration:");
  
  protected TextField txtMinStraight = new TextField();
  protected TextField txtMaxStraight  = new TextField() ;
  protected TextField txtMinJunction = new TextField();
  protected TextField txtMaxJunction  = new TextField() ;  
  protected TextField txtMinCurvy = new TextField();
  protected TextField txtMaxCurvy  = new TextField() ;
  protected TextField txtMinGreen = new TextField();
  protected TextField txtMaxGreen = new TextField();
  protected TextField txtMinAmber = new TextField();
  protected TextField txtMaxAmber = new TextField();
  protected TextField txtMinRed = new TextField();
  protected TextField txtMaxRed = new TextField();
  
  
  protected Label lblDensity = new Label("Traffic Density:");
  protected ComboBox txtDensity = new ComboBox();
  protected Label lblDuration = new Label("Session Duration (s):");
  protected TextField txtDuration =  new TextField();
  
  

  protected HBox button_box = new HBox(20);
  public Button btnStart = new Button("Start");
  public Button btnTerminate = new Button("Terminate");
  public Button btnReport = new Button("Report");
  
 public GUIComponents()
 {
     this.setLeft(setSimulationPanel());
     this.setRight(setCofigurationPanel());
 }
  public StackPane setSimulationPanel(){
    simulationPane = new StackPane();       
    simulationPane.setStyle("-fx-background-color: darkgreen");
    simulationPane.getChildren().add(canvas);
    gcontext = canvas.getGraphicsContext2D();
    return simulationPane;        
  }
  
  public StackPane setCofigurationPanel(){
      settingsPane = new StackPane();
      settingsPane.getChildren().add(setPolicyBox());
      
      return settingsPane;
           
  }
  
  public VBox setPolicyBox(){
      defaultPolicy.setToggleGroup(policiesOptions);
      randomPolicy.setToggleGroup(policiesOptions);
      customisedPolicy.setToggleGroup(policiesOptions);
      defaultPolicy.setSelected(true);
      policyOptionsBox = new HBox(8);
      policyOptionsBox.getChildren().addAll(defaultPolicy, randomPolicy, customisedPolicy);
      policySettings = new VBox();
      policySettings.setPadding(new Insets(20,20,20,20));
      policySettings.getChildren().addAll(lblSelectPolicy, policyOptionsBox);
      
      policyPane = new GridPane();
      policyPane.setPadding(new Insets(10,10,10,10));
      policyPane.add(lblMin, 1, 0);
      policyPane.add(lblMax, 2, 0);
      policyPane.add(lblJunction, 0, 1);
      policyPane.add(lblStraight,0,2);
      policyPane.add(lblCurvy, 0, 3);
      policyPane.add(txtMinJunction,1,1);
      policyPane.add(txtMaxJunction, 2, 1);
      policyPane.add(txtMinStraight,1,2);
      policyPane.add(txtMaxStraight, 2, 2);
      policyPane.add(txtMinCurvy,1,3);
      policyPane.add(txtMaxCurvy, 2, 3);
      policyPane.add(lblGreen,0,4);
      policyPane.add(lblAmber,0,5);
      policyPane.add(lblRed,0,6);
      policyPane.add(txtMinGreen,1,4);
      policyPane.add(txtMaxGreen, 2, 4);
      policyPane.add(txtMinAmber,1,5);
      policyPane.add(txtMaxAmber, 2, 5);
      policyPane.add(txtMinRed,1,6);
      policyPane.add(txtMaxRed, 2,6);
      
      Label blank = new Label("");
      policyPane.add(blank, 0, 7);
      
      txtDensity.getItems().addAll("High", "Normal", "Low");
      
      policyPane.add(lblDensity, 0, 8);
      txtDensity.setValue("Normal");
      policyPane.add(txtDensity, 1, 8);
      
      Label blank2 = new Label("");
      policyPane.add(blank2, 0, 9);
      policyPane.add(lblDuration, 0, 10);
      policyPane.add(txtDuration,1,10);
      
          
      policySettings.getChildren().add(policyPane);
      
      btnTerminate.setDisable(true);
     
      button_box.getChildren().addAll(btnStart, btnTerminate, btnReport);
      
     
      policySettings.getChildren().add(button_box);
      
   
      return policySettings;
             
      
  }
  
  
     public int getSelectedPolicy()
    {
        int selected = 0;
        if (defaultPolicy.isSelected())
            selected = 0;
        else if (customisedPolicy.isSelected())
            selected = 1;
        else if (randomPolicy.isSelected())
            selected = 2;
        
        return selected;
    }
    
    public int[] getMinSpeedLimitSettings()
    {
        int[] speedLimits = new int[3];
        speedLimits[1] = Integer.parseInt(txtMinJunction.getText());
        speedLimits[2] = Integer.parseInt(txtMinStraight.getText());
        speedLimits[3] = Integer.parseInt(txtMinCurvy.getText());
        return speedLimits;
              
    }
    
    public int[] getMaxSpeedLimitSettings()
    {
        int[] speedLimits = new int[3];
        speedLimits[1] = Integer.parseInt(txtMaxJunction.getText());
        speedLimits[2] = Integer.parseInt(txtMaxStraight.getText());
        speedLimits[3] = Integer.parseInt(txtMaxCurvy.getText());
        return speedLimits;
              
    }
    
    public int[] getMinTrafficLightSettings()
    {
        int[] trafficLights = new int[3];
        trafficLights [1] = Integer.parseInt(txtMinGreen.getText());
        trafficLights [2] = Integer.parseInt(txtMinAmber.getText());
        trafficLights [3] = Integer.parseInt(txtMinRed.getText());
        return trafficLights;
              
    }
    public int[] getMaxTrafficLightSettings()
    {
        int[] trafficLights = new int[3];
        trafficLights [1] = Integer.parseInt(txtMaxGreen.getText());
        trafficLights [2] = Integer.parseInt(txtMaxAmber.getText());
        trafficLights [3] = Integer.parseInt(txtMaxRed.getText());
        return trafficLights;
              
    }
    
    public int getSessionDuration()
    {
        return Integer.parseInt(txtDuration.getText());
    }
 
  
    public String getTrafficDesity()
    {
        return txtDensity.getValue().toString();
    }
 
  
}
