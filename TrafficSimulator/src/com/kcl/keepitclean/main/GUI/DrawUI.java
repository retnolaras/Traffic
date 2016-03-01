/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import javafx.scene.shape.Rectangle;
import com.kcl.keepitclean.main.GUI.UIComponent;

/**
 *
 * Draws elements on screen based on the 2d mapping array
 */
public class DrawUI {
  
//if array has a 1, draw a 100 by 100 triangle, a 2, then make it blue.
public static void Draw(int[][] a, int min, int max){
    for(int i=0; i<a[0].length; i++){
    for(int j=0; j<a.length; j++){
         switch (a[i][j]){
            case 2:
               Rectangle blue= new Rectangle(50.0,50.0);
               blue.setFill(Color.BLUE);
               UIComponent.grid.add(blue,i,j);
             break;
            
            case 1:
                //draw blue rectangle
               Rectangle green= new Rectangle(50.0,50.0);
               green.setFill(Color.GREEN);
               UIComponent.grid.add(green,i,j);
                break;
                

            default:
                Rectangle white= new Rectangle(50.0,50.0);
               white.setFill(Color.WHITE);
               UIComponent.grid.add(white,i,j);
                break;
             
        }
    }}    
    
    
    
        
    }
    
    
}

