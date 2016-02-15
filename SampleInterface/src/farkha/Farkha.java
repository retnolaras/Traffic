/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farkha;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import  javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Abdo
 */
public class Farkha extends Application {
    
    private Pane root;
    private Node frog;
    private List<Node> cars = new ArrayList<>();  
    
   private AnimationTimer timer;
   
    @Override
    public void start(Stage stage)throws Exception {
        stage.setScene(new Scene(createContent()));
        
        
        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case W :
                    frog.setTranslateY( frog.getTranslateY()-40);
                    break;
                case S :
                    frog.setTranslateY( frog.getTranslateY()+40);

                    break;
                case A : 
                    frog.setTranslateX( frog.getTranslateX()-40);

                    break;
                case D:
                    frog.setTranslateX( frog.getTranslateX  ()+40);

                    break; 
                default: 
                        break;
            }
            
               
            });
        
        stage.show();   
        
    }
   
    
    
    private Parent createContent(){
        root= new Pane();
        root.setPrefSize(800, 600);
        frog=  initFrog();
        root.getChildren().add(frog);      //frog (Node) instance is now a child pf root
        timer = new AnimationTimer(){
            @Override
            public void handle(long now){
            onUpdate();
            }
            
            
        };
        
     timer.start();
        return root;
        
    }
    
    private Node initFrog(){
         Rectangle rect = new Rectangle (38,38, Color.GREEN);
        rect.setTranslateY(600-39);
        return rect;
    }
    
    
    private Node spawnCar(){
        Rectangle rect = new Rectangle( 40, 40, Color.RED);
        rect.setTranslateY((int)(Math.random()*14)*40) ;
        root.getChildren().add(rect);
        return rect;     
        
    }
    
    
    private void onUpdate(){
    for (Node car: cars)
        car.setTranslateX(car.getTranslateX()+ Math.random()*10);
    if (Math.random() < 0.075){
    cars.add(spawnCar()); // CAll spawn car and add it to the cars ArrayList
    }
    
    
    CheckState();
    }
    
    
    
    
  
    
    
    
    private void CheckState(){
        for (Node car: cars){
            if (car.getBoundsInParent().intersects(frog.getBoundsInParent())){
                frog.setTranslateX(0);
                frog.setTranslateY(600-39);
                return;
            };
        }
        
        // create box place it and add it to tree
        HBox hbox= new HBox();
        hbox.setTranslateX(350);
        hbox.setTranslateY(250);
        root.getChildren().add(hbox);
        
        
        
        
        
        if (frog.getTranslateY() <= 0){
            timer.stop();
            String win= "YOU WIN";
            for (int i=0 ;  i< win.toCharArray().length ; i++){
                char letter = win.charAt(i); 
                Text text= new Text(String.valueOf(letter));
                text.setFont(Font.font(48));
                text.setOpacity(0);
                
                
          hbox.getChildren().add(text);
          
                FadeTransition ft = new FadeTransition (Duration.seconds(0.66), text);
                ft.setToValue(1);
                ft.setDelay(Duration.seconds(i* 0.15));
                ft.play();
                
                
                
                
            }
            
        }
        }
    
    
    
    }
    
   

