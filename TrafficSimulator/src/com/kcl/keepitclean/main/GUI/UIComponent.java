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
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class UIComponent extends Application {
    
    public int tileMin=9;
    public int tileMax=9;
    public StackPane root = new StackPane();         
    public int[][] dataInput= new int[tileMin][tileMax]; //assumed external input
    public static StackPane rightPane= new StackPane(); // right frame to hold  components
    public static StackPane leftPane= new StackPane(); // left frame to hold simulation engine
    
    
    @Override
    public void start(Stage primaryStage) {
        
        
      //  StackPane root = new StackPane();         
        Scene scene = new Scene(root, 900, 900); //set up a window with these propotions
        
        primaryStage.setTitle("Keep it Clean simulator"); //window title
        primaryStage.setScene(scene);
        primaryStage.show();
                
        setLayout();
        
        drawUI.Draw(dataInput, tileMin, tileMax);
    }
    
    //class that takes in a 2d Array and shows it on the scree

    
    private void setLayout (){
    /* define placeholders for content*/
        HBox mainFrame= new HBox(); //the Main frame component.
        root.getChildren().add(mainFrame); //add it to the scene
                
        
        
        mainFrame.getChildren().addAll(leftPane,rightPane);
        
        Rectangle leftR= new Rectangle(600.0, 900.0);
        leftR.setFill(Color.WHITE);
        Rectangle rightR= new Rectangle(300.0, 900.0);
        rightR.setFill(Color.RED);
        rightPane.getChildren().add(rightR);
        leftPane.getChildren().add(leftR);
    
    
    };
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


