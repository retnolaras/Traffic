package com.kcl.keepitclean.main.roadnetwork.junction;

import com.kcl.keepitclean.main.policy.TrafficLightColour;
import com.kcl.keepitclean.main.policy.TrafficLightPolicy;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


public class TrafficLight extends PrePlannedRouteJunction {
    public TrafficLight(java.util.List<Road> roadsEnteringThisJunction, java.util.List<Road> roadsLeavingThisJunction) {
		super(roadsEnteringThisJunction, roadsLeavingThisJunction);
		// TODO Auto-generated constructor stub
	}

	public List LanePath;
	public boolean IsEnable;
	public TrafficLightPolicy tlp;
	public TrafficLightColour clr = tlp.getColour();
	
	public boolean setState(TrafficLightColour clr){
		if(clr == TrafficLightColour.GREEN) IsEnable = true;
		else IsEnable = false;
		return IsEnable;
	}
	
}