/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.test.simulation;

import org.junit.Test;

import com.kcl.keepitclean.main.simulatorengine.SimulationData;
import com.kcl.keepitclean.main.simulatorengine.SimulatorEngine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestSimulationData{
    SimulationData simulationDataTest;
    SimulatorEngine dummySimulation;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCalculateAvgSpeed()
    {
        List<Integer> speed = new ArrayList<>();
        speed.add(2);
        speed.add(4);
        
        double avgSpeed;
        double expectedAvgSpeed = 3;
    	
    	simulationDataTest = new SimulationData();
       
        if (simulationDataTest != null)
        {
            avgSpeed = simulationDataTest.calculateAverageSpeed(speed);
            		
            Assert.assertTrue(expectedAvgSpeed == avgSpeed);
        }
                
    }
    
	@Test
    public void testConvertSpeed()
    {
        double expectedSpeed = 32.72;
        double convertedSpeed;
    	       
        simulationDataTest = new SimulationData();
        if (simulationDataTest != null)
        {
            convertedSpeed = simulationDataTest.convertSpeed(1);
            Assert.assertTrue(convertedSpeed >= expectedSpeed);
        }
                
    }
	
	@Test
    public void testConvertSessionDuration()
    {
        int expectedConvertDuration = 1;
        int convertedDuration;
    	       
        simulationDataTest = new SimulationData();
        if (simulationDataTest != null)
        {
            convertedDuration = simulationDataTest.convertSessionDuration(3);
            Assert.assertEquals("Failure- converted session duration is not correct", convertedDuration, expectedConvertDuration);
        }
                
    }
	
	 @Test
	    public void testCalculateEstimation()
	    {		
	        double estimation;
	        double expectedEstimation = 94608;
	    	
	    	simulationDataTest = new SimulationData();
	       
	        if (simulationDataTest != null)
	        {
	            estimation = simulationDataTest.calculateTrafficEstimation(1, 1, 1);
	            		
	            Assert.assertTrue(expectedEstimation == estimation);
	        }
	                
	    }
    
}
