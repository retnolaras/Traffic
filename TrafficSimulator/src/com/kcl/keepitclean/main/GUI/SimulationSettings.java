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
    public int policyOption;
    public int[] minSpeed;
    public int[] maxSpeed;
    public int[] minTrafficLight;
    public int[] maxTrafficLight;
    public String trafficDensity;
    public int sessionDuration;
    
    public SimulationSettings(int policyOption, int[] minSpeed, int[] maxSpeed, int[] minTrafficLight, int[] maxTrafficLight, String trafficDensity, int sessionDuration){
        this.policyOption = policyOption;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.minTrafficLight = minTrafficLight;
        this.maxTrafficLight = maxTrafficLight;
        this.trafficDensity = trafficDensity;
        this.sessionDuration = sessionDuration;
        
    }
    
}
