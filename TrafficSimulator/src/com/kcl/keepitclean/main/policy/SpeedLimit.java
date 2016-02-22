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
    protected double junction;
    protected double straightRoad;
    protected double curvyRoad;

    protected  SpeedLimit(double junctionLimit, double straightRoadLimit, double curvyRoadLimit){
        this.junction = junctionLimit;
        this.straightRoad = straightRoadLimit;
        this.curvyRoad = curvyRoadLimit;
        
    }
}

