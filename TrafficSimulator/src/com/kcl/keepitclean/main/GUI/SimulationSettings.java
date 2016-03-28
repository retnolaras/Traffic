/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import java.util.ArrayList;

import com.kcl.keepitclean.main.policy.Policy;

/**
 * Wrap user inputs from GUI to transfer to simulation engine
 * @author rosiengo
 */
public class SimulationSettings {
    private Policy policy;
    private int sessionDuration;
    private TrafficDensity trafficDensity;
    private MapType mapType;
    ArrayList<Boolean> entrancePoints;    
    public SimulationSettings(int policyOption, int[] minSpeed, int[] maxSpeed, int[] minTrafficLight, int[] maxTrafficLight, String trafficDensity, int sessionDuration, String mapType, ArrayList<Boolean> entryPoints){
       
    	
    	policy = Policy.setPolicy(policyOption, minSpeed, maxSpeed, minTrafficLight, maxTrafficLight);
        this.sessionDuration = sessionDuration;
        if (trafficDensity == "High") 
            this.trafficDensity = TrafficDensity.HIGH;
        else if (trafficDensity == "Normal")
            this.trafficDensity = TrafficDensity.NORMAL;
        else 
            this.trafficDensity = TrafficDensity.LOW;
        
        if (mapType == "Junction")
            this.mapType = MapType.JUNCTION;
        else 
            this.mapType = MapType.TOWN;     
        
        entrancePoints= new ArrayList<Boolean>();
        entrancePoints= entryPoints;
        
    }
    
    
     
    public int getSessionDuration(){
        return sessionDuration;
    }
    
    public TrafficDensity getTrafficDensity(){
        return trafficDensity;
    }
    
    public MapType getMapType(){
        return mapType;
    }
    
    //gets Selected Entry Points
    public ArrayList<Boolean> getEntryPoints(){
		return entrancePoints;
    	
    }
    
}
