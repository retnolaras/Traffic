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
public class TrafficLight {
    protected int green;
    protected int amber;
    protected int red;

    
    protected  TrafficLight(int greenTime, int amberTime, int redTime){
        this.green = greenTime;
        this.amber = amberTime;
        this.red = redTime;    
}
}

