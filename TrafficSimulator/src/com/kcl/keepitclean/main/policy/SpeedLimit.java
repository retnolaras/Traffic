/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.policy;

/**
 *
 * @author Huong
 */
public class SpeedLimit {
    public  int junction;
    public  int straightRoad;
    public  int curvyRoad;

    public  SpeedLimit(int junctionLimit, int straightRoadLimit, int curvyRoadLimit){
        this.junction = junctionLimit;
        this.straightRoad = straightRoadLimit;
        this.curvyRoad = curvyRoadLimit;
        
    }
}

