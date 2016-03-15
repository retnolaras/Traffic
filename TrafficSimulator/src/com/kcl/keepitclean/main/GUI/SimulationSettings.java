/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

/**
 *
 * @author rosiengo
 */
public class SimulationSettings {
    private int policyOption;
    private int[] minSpeed;
    private int[] maxSpeed;
    private int[] minTrafficLight;
    private int[] maxTrafficLight;
    private String trafficDensity;
    private int sessionDuration;
    
    public SimulationSettings(int policyOption, int[] minSpeed, int[] maxSpeed, int[] minTrafficLight, int[] maxTrafficLight, String trafficDensity, int sessionDuration){
        this.policyOption = policyOption;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.minTrafficLight = minTrafficLight;
        this.maxTrafficLight = maxTrafficLight;
        this.trafficDensity = trafficDensity;
        this.sessionDuration = sessionDuration;
        
    }
    
    public int getPolicyOption(){
        return this.policyOption;
    }
    public int[] getMinSpeeds(){
        return this.minSpeed;
    }
    public int[] getMaxSpeeds(){
        return this.maxSpeed;
    }
    public int[] getMinTrafficLightDuration(){
        return this.minTrafficLight;
    }
    public int[] getMaxTrafficLightDuration(){
        return this.maxTrafficLight;
    }
    public String getTrafficDensity(){
        return this.trafficDensity;
    }
    public int getSessionDuration(){
        return this.sessionDuration;
    }
           
}
