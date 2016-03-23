/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.test.policy;

import org.junit.Test;
import com.kcl.keepitclean.main.policy.Policy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;


/**
 *
 * @author Rosie
 */
public class TestPolicy {
    Policy testPolicy;
    
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
    public void testSetDefaultPolicy()
    {
        int speedLimit;
        int greenTime;
        int redTime;
        int amberTime;
        int expectedSpeedLimit = 60;
        
        int expectedGreenTime = 3;
        int expectedRedTime = 3;
        int expectedAmberTime = 3;
        testPolicy = Policy.setPolicy(0, null, null, null, null);
       
        Assert.assertNotNull("Failure - must be not null policy", testPolicy);
        if (testPolicy != null)
        {
            speedLimit = testPolicy.getSpeedLimit();
            
            greenTime = testPolicy.getGreenTrafficLightTime();
            redTime = testPolicy.getRedTrafficLightTime();
            amberTime = testPolicy.getAmberTrafficLightTime();
            Assert.assertEquals("Failure- speed limit  not correct",  expectedSpeedLimit, speedLimit);
            
            Assert.assertEquals("Failure- timing for green light not correct", expectedGreenTime, greenTime);
            Assert.assertEquals("Failure- timing  for red light not correct", expectedRedTime, redTime);
            Assert.assertEquals("Failure- timing  for amber light not correct", expectedAmberTime, amberTime);
        }

                
    }
    @Test
    public void testSetRandomPolicy()
    {
        int speedLimit;
       
        int greenTime;
        int redTime;
        int amberTime;
        int[] minSpeedLimit = {40};
        int[] maxSpeedLimit = {60};
        int[] minTrafficLight = {30,5,10};
        int[] maxTrafficLight = {60,15,30};
                          
        testPolicy = Policy.setPolicy(2, minSpeedLimit, maxSpeedLimit, minTrafficLight, maxTrafficLight);
        Assert.assertNotNull("Failure - must be not null policy", testPolicy);
        if (testPolicy != null)
        {
            speedLimit = testPolicy.getSpeedLimit();
            greenTime = testPolicy.getGreenTrafficLightTime();
            redTime = testPolicy.getRedTrafficLightTime();
            amberTime = testPolicy.getAmberTrafficLightTime();
            Assert.assertTrue("Failure- speed limit  not in range: " + testPolicy.getSpeedLimit(), (speedLimit >= minSpeedLimit[0]) && (speedLimit <= maxSpeedLimit[0]) );
            Assert.assertTrue(("Failure- speed limit for green light not in range: " + testPolicy.getGreenTrafficLightTime()), (greenTime >= minTrafficLight[0]) && (greenTime <= maxTrafficLight[0]) );
            Assert.assertTrue("Failure- speed limit for amber light not in range: " + testPolicy.getAmberTrafficLightTime(), (amberTime >= minTrafficLight[1]) && (amberTime <= maxTrafficLight[1]) );
            Assert.assertTrue("Failure- speed limit for red light not in range: " + testPolicy.getRedTrafficLightTime(), (redTime >= minTrafficLight[2]) && (redTime <= maxTrafficLight[2]) );
            
        }
                
    }
    @Test
    public void testSetCustomisedPolicy()
    {
        int speedLimit;
        int greenTime;
        int redTime;
        int amberTime;
        int[] minSpeedLimit = {20,30,20};
        int[] minTrafficLight = {30,5,10};
        
                         
        testPolicy = Policy.setPolicy(1, minSpeedLimit, null, minTrafficLight, null);
        Assert.assertNotNull("Failure - must be not null policy", testPolicy);
        if (testPolicy != null)
        {
            speedLimit = testPolicy.getSpeedLimit();
            greenTime = testPolicy.getGreenTrafficLightTime();
            redTime = testPolicy.getRedTrafficLightTime();
            amberTime = testPolicy.getAmberTrafficLightTime();
            Assert.assertTrue("Failure- speed limit not correct", (speedLimit == minSpeedLimit[0]));
            Assert.assertTrue("Failure- speed limit for green light not correct", (greenTime == minTrafficLight[0])  );
            Assert.assertTrue("Failure- speed limit for amber light not correct", (amberTime == minTrafficLight[1]) );
            Assert.assertTrue("Failure- speed limit for red light not correct", (redTime == minTrafficLight[2]));
            
        }
                
    }
    @Test
    public void testGetPolicyInstance_Uninitiated()
    {
        
        
        Policy expectedPolicy = Policy.setPolicy(0, null, null, null, null);
        testPolicy = Policy.getPolicyInstance();
        
        Assert.assertNotNull("Failure - must be not null policy", testPolicy);
        Assert.assertEquals("Failure- must return the default policy instance", expectedPolicy, testPolicy);
                
    }
    @Test
    public void testGetPolicyInstance_Initiated()
    {
        int[] minSpeedLimit = {60};
        int[] minTrafficLight = {30,5,10};
                          
        Policy expectedPolicy = Policy.setPolicy(1, minSpeedLimit, null, minTrafficLight, null);
        
        testPolicy = Policy.getPolicyInstance();
        
        Assert.assertNotNull("Failure - must be not null policy", testPolicy);
        Assert.assertEquals("Failure- must return the predefined policy", expectedPolicy, testPolicy);
       
                
    }
}
