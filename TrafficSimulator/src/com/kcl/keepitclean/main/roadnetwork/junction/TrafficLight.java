package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.kcl.keepitclean.main.policy.Policy;
import com.kcl.keepitclean.main.roadnetwork.road.Road;


public class TrafficLight {
    private Junction junction;
    private Road road;  

    public enum State {

    GREEN, AMBER, RED;
  }
    private Point trafficLightCoordinate;
    private Timer timer;
    
    private State state;
    private Policy policy;
    
    ActionListener taskPerformer = new ActionListener() {
   
        @Override
        public void actionPerformed(ActionEvent e) {
            nextState();
            timer.stop();
            timer.setDelay((int) getDelay());
            timer.restart();
            
        }
        };
        

    
     public TrafficLight(Road road, Junction junction) {
        this.road = road;
        this.junction = junction;
        this.policy = Policy.getPolicyInstance();
    }
     
    public void activate()
    {
        
        timer = new Timer((int) getDelay(),taskPerformer );
        timer.start();
        
               
    }
    
    private long getDelay(){
        long delay = 0;
        switch(state){
            case GREEN:
                delay = policy.getGreenTrafficLightTime() * 1_000L; // * 1000* constant.TIMER_RATIO;
                break;
            case RED:
                delay = policy.getRedTrafficLightTime()* 1_000L; //* 1000* constant.TIMER_RATIO;
                break;
            case AMBER:
                delay = policy.getAmberTrafficLightTime()* 1_000L;//* 1000* constant.TIMER_RATIO;
                break;
            default:
                delay = policy.getRedTrafficLightTime()* 1_000L;//* 1000* constant.TIMER_RATIO;
                break;
        }
        return delay;
    }
    
    public void deactivate()
    {
        this.timer.stop();
    }
    
    
    	
    public void setState(State state) {
	this.state = state;			
    }
    
	
    public State getState(){
	return this.state;
    }
    
    private State nextState(){
        switch(this.state){
            case GREEN:
                setState(State.AMBER);
                break;
            case AMBER:
                setState(State.RED);
                break;
            case RED:
                setState(State.GREEN);
                break;
            default:
                setState(State.RED);
                break;
                
        }
        
        return this.state;
                
            
    }
    
    public Road getRoad()
    {
        return this.road;
    }

    public Junction getJunction(){
        return this.junction;
    }
	
    public Point getTrafficLightCoordinate() {
        return trafficLightCoordinate;
    }

    public void setTrafficLightCoordinate(Point trafficLightCoordinate) {
        this.trafficLightCoordinate = trafficLightCoordinate;
    }
    
	
	
}