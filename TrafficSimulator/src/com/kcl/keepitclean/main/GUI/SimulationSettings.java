/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import com.kcl.keepitclean.main.policy.Policy;

/**
 * Wrap user inputs from GUI to transfer to simulation engine
 * @author rosiengo
 */
public class SimulationSettings {
    private Policy policy;
    private int sessionDuration;
    
    public SimulationSettings(int policyOption, int[] minSpeed, int[] maxSpeed, int[] minTrafficLight, int[] maxTrafficLight, String trafficDensity, int sessionDuration){
        policy = Policy.setPolicy(policyOption, minSpeed, maxSpeed, minTrafficLight, maxTrafficLight);
        this.sessionDuration = sessionDuration;
        
    }
    
    public int getSessionDuration(){
        return sessionDuration;
    }
    
}
