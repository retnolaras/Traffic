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
    protected  int junction;
    protected  int straightRoad;
    protected  int curvyRoad;

    protected  SpeedLimit(int junctionLimit, int straightRoadLimit, int curvyRoadLimit){
        this.junction = junctionLimit;
        this.straightRoad = straightRoadLimit;
        this.curvyRoad = curvyRoadLimit;
        
    }
}

