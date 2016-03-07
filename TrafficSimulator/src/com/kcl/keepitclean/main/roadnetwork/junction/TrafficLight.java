package com.kcl.keepitclean.main.roadnetwork.junction;

import com.kcl.keepitclean.main.policy.TrafficLightColour;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


public class TrafficLight extends PrePlannedRouteJunction {
    public TrafficLight(java.util.List<Road> roadsEnteringThisJunction, java.util.List<Road> roadsLeavingThisJunction) {
		super(roadsEnteringThisJunction, roadsLeavingThisJunction);
	}

	public List LanePath;
	public boolean IsEnable;
	public TrafficLightColour clr;
	
	public void setColour() {
		clr = TrafficLightColour.GREEN;	
		}
	
	public void setColour(int greentime, int ambertime, int redtime) {
			for (int stepcounter = 0; stepcounter < greentime ; stepcounter++) {
			    clr = TrafficLightColour.GREEN;
			}
			for (int stepcounter = 0; stepcounter <ambertime ; stepcounter++) {
			    clr = TrafficLightColour.AMBER;
			}
			for (int stepcounter = 0; stepcounter < redtime ; stepcounter++) {
			    clr = TrafficLightColour.RED;
			}			
		}

	public boolean setState(TrafficLightColour clr){
		if(clr == TrafficLightColour.GREEN) IsEnable = true;
		else IsEnable = false;
		return IsEnable;
	}
	
	
	
	
	
}