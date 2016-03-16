package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;

import com.kcl.keepitclean.main.roadnetwork.road.Road;


import javafx.scene.paint.Color;


public class TrafficLight {
    private Junction junction;
    private Road road;  

    private Color clr;
    private Point trafficLightCoordinate;
    
     public TrafficLight(Road road, Junction junction) {
        this.road = road;
        this.junction = junction;
	}
	
	
    public void setColour(Color clr) {
	this.clr = clr;			
    }
	
    public Color getColour(){
	return clr;
    }
    
    public Road getRoad()
    {
        return this.road;
    }

    public Junction getJunction(){
        return this.junction;
    }
	
    public Point getTrafficLightCoordinate() {
        return trafficLightCoordinate;
    }

    public void setTrafficLightCoordinate(Point trafficLightCoordinate) {
        this.trafficLightCoordinate = trafficLightCoordinate;
    }
    
	
	
	
}