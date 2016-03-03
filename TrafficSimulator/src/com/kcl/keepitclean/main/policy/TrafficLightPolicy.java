/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.policy;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

/**
 *
 * @author Huong
 */
public class TrafficLightPolicy {
	private Timer trLightTimer = new Timer(1500, null);
	private TrafficLightColour colour;
		
		
	public TrafficLightPolicy()
	{	
		setColour(TrafficLightColour.GREEN);
		
		//this.start();
	}
		
	
	public void setColour(TrafficLightColour colour) {
		this.colour = colour;
	}

	
	public TrafficLightColour getColour() {
		if(colour!=null){
			
		return colour;}
		else {
		
			return null;}
	}
	
	public void actionPerformed (ActionEvent e)
		{	
			if(getColour()==TrafficLightColour.RED)
			{
				setColour(TrafficLightColour.GREEN);
			}
			else if(getColour()==TrafficLightColour.RED)
			{
				setColour(TrafficLightColour.GREEN);
			}
		} 
}
