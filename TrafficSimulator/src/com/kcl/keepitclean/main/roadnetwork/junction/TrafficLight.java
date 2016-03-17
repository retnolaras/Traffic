package com.kcl.keepitclean.main.roadnetwork.junction;

import com.kcl.keepitclean.main.policy.Policy;
import java.awt.Point;

import com.kcl.keepitclean.main.roadnetwork.road.Road;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


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
            timer.setDelay(getDelay());
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
        
        timer = new Timer(getDelay(),taskPerformer );
        timer.start();
        
               
    }
    
    private int getDelay(){
        int delay = 0;
        switch(state){
            case GREEN:
                delay = policy.getGreenTrafficLightTime();
                break;
            case RED:
                delay = policy.getRedTrafficLightTime();
                break;
            case AMBER:
                delay = policy.getAmberTrafficLightTime();
                break;
            default:
                delay = policy.getRedTrafficLightTime();
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
                setState(state.RED);
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