/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.policy;

import javafx.scene.paint.Color;

/**
 *
 * @author Huong
 */
public class TrafficLightPolicy {
	public Color colour;
	public int green;
	public int amber;
	public int red;

	    
	public  TrafficLightPolicy(int greenTime, int amberTime, int redTime){
	        this.green = greenTime;
	        this.amber = amberTime;
	        this.red = redTime;    
	}


	
	
	
}
