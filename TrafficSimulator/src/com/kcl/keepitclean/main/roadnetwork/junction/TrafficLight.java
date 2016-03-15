package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;

import com.kcl.keepitclean.main.roadnetwork.road.Road;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import javafx.scene.paint.Color;


public class TrafficLight extends PrePlannedRouteJunction {
    public TrafficLight(java.util.List<Road> roadsEnteringThisJunction, java.util.List<Road> roadsLeavingThisJunction) {
		super(roadsEnteringThisJunction, roadsLeavingThisJunction);
	}

	public List LanePath;
	public boolean IsEnable;
	public Color clr;
	private Point trafficLightCoordinate;
	
	
	public void setColour(Color clr) {
		this.clr = clr;			
		}
	
	public Color getColour(){
		return clr;
	}

	public void setState(Color clr){
		if(clr == Color.GREEN) IsEnable = true;
		else IsEnable = false;
	}
	
	public boolean getState(){
		return IsEnable;
	}

	public Point getTrafficLightCoordinate() {
		return trafficLightCoordinate;
	}

	public void setTrafficLightCoordinate(Point trafficLightCoordinate) {
		this.trafficLightCoordinate = trafficLightCoordinate;
	}
	
	
	
}