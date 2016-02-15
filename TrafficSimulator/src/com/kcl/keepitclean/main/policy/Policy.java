/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.policy;

/**
 *
 * @author RosieNgo
 */
public class Policy {
    /*
     The policy is to define:
     - the speed limit which may be different at junction, curvy road section, straight road section of the same road; using fixed speed limit control
     - Traffic Light Timing at Green, Amber, Red status; using fixed time control 
     - current version: not distinguis policies for different type roads (Single/dual carriageware, urban road, rural road..)
    
      
     */ 
    private SpeedLimit speedLimit;
    private TrafficLight trafficLight;
    private Policy instance = new Policy();
    public Policy(){
        /* Creates  policy 
        current version: default speed limit and traffic light timing
        next version:  random generation */
        
        SpeedLimit defaultSpeedLimit = new SpeedLimit(30,40,60); //Junction 30mph,  Straight Road 60mph, Curvy Road 40mph
        TrafficLight defaultTrafficLight = new TrafficLight(30,5,10); //green 30s, amber 5s, red 10s)
        this.speedLimit = defaultSpeedLimit;
        this.trafficLight = defaultTrafficLight;
        
    }
    public Policy(SpeedLimit speedP, TrafficLight trafficLightP){
        /* Create variable Policy 
        for example 
           Policy urbanRoadPolicy = new Policy(new SpeedLimit(16, 20, 30), new TrafficLight(30,5,15));
           Policy ruralRoadPolicy = new Policy(new SpeedLimit(20, 30, 40), new TrafficLight(30,5,10));
        */
        this.speedLimit = speedP;
        this.trafficLight = trafficLightP;
    }   
 
public double getJunctionSpeedLimit(){
    return this.speedLimit.junction;
   
}    
public double getStraightRoadSpeedLimit(){
    return this.speedLimit.straightRoad;
    
} 
public double getCurvyRoadSpeedLimit(){
    return this.speedLimit.curvyRoad;
    
}
public int getGreenTrafficLightTime(){
     return this.trafficLight.green;
 }
public int getAmberTrafficLightTime(){
    return this.trafficLight.amber;
}
public int getRedTrafficLightTime(){
    return this.trafficLight.red;
}
}

