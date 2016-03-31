/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.policy;
import java.util.Random;


/**
 *
 * @author RosieNgo
 */
public class Policy {
    /*
    1) SIMULATION ENGINE: AT THE BEGINNING OF EACH SESSION, CALL setPolicy(PARAMEMTERS) METHOD TO GENERATE POLICY BASED ON USER INPUTS
    
    PARAMETERS:
    
    - MODE: 0 (DEFAULT), 1(CUSTOMISED), 2 (RANDOM)
    
    - SPEEDLIMITFROM/ SPEEDLIMITTO: ARRAY OF SPEED LIMIT RANGE SETTINGS  [1]: JUNCTION SPEED LIMIT, [2] STRAIGHT ROAD SPEED LIMIT, [3]: CURVY ROAD SPEED LIMIT
    IN CASE OF CUSTOMISED POLICY, SPEEDLIMITTO  = NULL;
    
   -  TRAFFICLIGHTFROM/TRAFFICLIGHTTO: ARRAY OF TRAFFIC LIGHT TIMING SETTINGS: [1]: GREEN; [2]: AMBER; [3]: RED
    IN CASE OF CUSTOMISED POLICY: TRAFFICLIGHTTO = NULL;
    
    2) OTHER CLASSES: CALL getPolicyInstance() TO GET POLICY INSTANCE.
    
    INDIVIDUAL POLICY SETTINGS CAN BE ACCESSED VIA RELEVANT GET METHODS    
      
     */ 
    
    private SpeedLimit speedLimit;
    private TrafficLightPolicy trafficLight;
    private static Policy instance;
    
    
private Policy(int[] speedLimitFrom, int[] speedLimitTo,int[] trafficLightFrom, int[] trafficLightTo) /* random generated policy*/
{
    //TO-DO: EXCEPTION HANDLING 
     int speedLimit;
     //int curvyRoadLimit;
     //int junctionLimit;
     int greenTiming;
     int amberTiming;
     int redTiming;
     Random generator = new Random();
     
     speedLimit = speedLimitFrom[0] + generator.nextInt(speedLimitTo[0] - speedLimitFrom[0] + 1 );
     //straightRoadLimit = speedLimitFrom[1] + generator.nextInt(speedLimitTo[1] - speedLimitFrom[1] + 1 );
     //curvyRoadLimit = speedLimitFrom[2] + generator.nextInt(speedLimitTo[2] - speedLimitFrom[2] + 1 ) ;
     greenTiming = trafficLightFrom[0] + generator.nextInt(trafficLightFrom[0] - trafficLightFrom[0] + 1 );
     amberTiming = trafficLightFrom[1] + generator.nextInt(trafficLightFrom[1] - trafficLightFrom[1] + 1 );
     redTiming = trafficLightFrom[2] + generator.nextInt(trafficLightFrom[2] - trafficLightFrom[2] + 1 );
        
    this.speedLimit = new SpeedLimit(speedLimit);
    trafficLight = new TrafficLightPolicy(greenTiming,amberTiming,redTiming);
    
}

private Policy()  //default policy
      
{
  speedLimit = new SpeedLimit(60); //Junction 30mph,  Straight Road 60mph, Curvy Road 40mph
  trafficLight = new TrafficLightPolicy(3,3,3); //green 3s, amber 3s, red 3s)   
}

private Policy(int[] userSpeedLimit, int[] userTrafficLight) //customised policy
{
    //TO DO LIST: EXCEPTION HANDLER
    speedLimit = new SpeedLimit(userSpeedLimit[0]);
    trafficLight = new TrafficLightPolicy(userTrafficLight[0], userTrafficLight[1],userTrafficLight[2]);
    
}

public static Policy setPolicy(int mode,int[] speedLimitFrom, int[] speedLimitTo,int[] trafficLightFrom, int[] trafficLightTo){
    
    //TO DO: EXCEPTION HANDLING
    
    //this.resetPolicy();
    instance = null;
    switch (mode) {
        case 0:  //DEEFAULT POLICY
            instance = new Policy();
            break;
        case 2:    //CUSTOMISED POLICY
            instance = new Policy(speedLimitFrom,trafficLightFrom);
            break;
        case 1:   //RANDOM POLICY
            instance = new Policy(speedLimitFrom, speedLimitTo,trafficLightFrom,trafficLightTo);
            break;
        default:  //DEFAULT POLICY
            instance = new Policy();
            break;
    }
  
    return instance;
}
public static Policy getPolicyInstance()
{
    if (instance == null)
    {
        instance = new Policy();  //default policy
    }
    return instance;
}
public int getSpeedLimit()
{
    return this.speedLimit.speedLimit;
   
}  
/*
public int getStraightRoadSpeedLimit(){
    return this.speedLimit.straightRoad;
    
} 
public int getCurvyRoadSpeedLimit(){
    return this.speedLimit.curvyRoad;
    
}
*/
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
