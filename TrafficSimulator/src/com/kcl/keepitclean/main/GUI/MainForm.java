/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

/**
 *
 * @author rosiengo
 */
public class MainForm extends Application {
    
  GridPane settingsPane;
  GridPane simulationPane;
  protected Canvas canvas;
  
  public GraphicsContext graphicsContext;
  
  protected HBox policySettingsBox;
  protected VBox policyOptionsBox;
    
  protected ToggleGroup policies_options;
  protected RadioButton defaultPolicy;
  protected RadioButton randomPolicy;
  protected ToggleGroup customisedPolicy;
 

  protected HBox durationBox;
  public TextField durationTxt;


  protected BorderPane button_pane;
  protected HBox button_box;
  public Button startBtn;
  public Button reportBtn;
  public Button terminateBtn;
  public Button pauseBtn;
  public Button resumeBtn;
  

  protected VBox container;
  
  public ToggleGroup policySelector;
  
  
   protected IRenderer renderer;  //TO BE REMOVED- TEST RENDERER
   
  
    @Override
    public void start(Stage primaryStage) {
        
       

        
        GridPane leftPane = new GridPane();
        GridPane rightPane = new GridPane();
        StackPane root = new StackPane();
        
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        */
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Simulation!");
        primaryStage.setScene(scene);
        /* TO BE REMOVED- TEST RENDERER */
        if (renderer != null) {
            renderer.render();  
        /* END TEST RENDERER */
    }
        primaryStage.show();
    }

    
     /* START TEST RENDERER- TO BE REMOVED*/
    public IRenderer getRenderer() {
        return renderer;
    }

     public void setRenderer(IRenderer renderer) {
        this.renderer = renderer;
    }
    /* END TEST RENDERER */
     
     
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
