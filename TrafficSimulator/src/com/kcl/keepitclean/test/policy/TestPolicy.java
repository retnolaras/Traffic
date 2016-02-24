/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.test.policy;

import org.junit.Test;
import com.kcl.keepitclean.main.policy.Policy;
<<<<<<< HEAD
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

=======
import org.junit.Assert;
import org.junit.runner.Runner;
>>>>>>> refs/remotes/retnolaras/master

/**
 *
 * @author Rosie
 */
public class TestPolicy {
    Policy testPolicy;
<<<<<<< HEAD
    
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
    
=======
    
>>>>>>> refs/remotes/retnolaras/master
    @Test
    public void testSetDefaultPolicy()
    {
        int speedLimitJunction;
        int speedLimitStraight;
        int speedLimitCurve;
        int greenTime;
        int redTime;
        int amberTime;
        int expectedSpeedLimitJunction = 30;
        int expectedSpeedLimitStraight = 60;
        int expectedSpeedLimitCurve = 40;
        int expectedGreenTime = 30;
        int expectedRedTime = 10;
        int expectedAmberTime = 5;
        testPolicy = Policy.setPolicy(0, null, null, null, null);
<<<<<<< HEAD
       
=======
>>>>>>> refs/remotes/retnolaras/master
        Assert.assertNotNull("Failure - must be not null policy", testPolicy);
        if (testPolicy != null)
        {
            speedLimitJunction = testPolicy.getJunctionSpeedLimit();
            speedLimitStraight = testPolicy.getStraightRoadSpeedLimit();
            speedLimitCurve = testPolicy.getCurvyRoadSpeedLimit();
            greenTime = testPolicy.getGreenTrafficLightTime();
            redTime = testPolicy.getRedTrafficLightTime();
            amberTime = testPolicy.getAmberTrafficLightTime();
            Assert.assertEquals("Failure- speed limit for junction not correct",  expectedSpeedLimitJunction, speedLimitJunction);
            Assert.assertEquals("Failure- speed limit for straight road not correct", expectedSpeedLimitStraight, speedLimitStraight);
            Assert.assertEquals("Failure- speed limit for curvy road not correct", expectedSpeedLimitCurve, speedLimitCurve);
            Assert.assertEquals("Failure- timing for green light not correct", expectedGreenTime, greenTime);
            Assert.assertEquals("Failure- timing  for red light not correct", expectedRedTime, redTime);
            Assert.assertEquals("Failure- timing  for amber light not correct", expectedAmberTime, amberTime);
        }
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/retnolaras/master
                
    }
    @Test
    public void testSetRandomPolicy()
    {
        int speedLimitJunction;
        int speedLimitStraight;
        int speedLimitCurve;
        int greenTime;
        int redTime;
        int amberTime;
        int[] minSpeedLimit = {20,30,20};
        int[] maxSpeedLimit = {40,70,40};
        int[] minTrafficLight = {30,5,10};
        int[] maxTrafficLight = {60,15,30};
                          
        testPolicy = Policy.setPolicy(2, minSpeedLimit, maxSpeedLimit, minTrafficLight, maxTrafficLight);
        Assert.assertNotNull("Failure - must be not null policy", testPolicy);
        if (testPolicy != null)
        {
            speedLimitJunction = testPolicy.getJunctionSpeedLimit();
            speedLimitStraight = testPolicy.getStraightRoadSpeedLimit();
            speedLimitCurve = testPolicy.getCurvyRoadSpeedLimit();
            greenTime = testPolicy.getGreenTrafficLightTime();
            redTime = testPolicy.getRedTrafficLightTime();
            amberTime = testPolicy.getAmberTrafficLightTime();
<<<<<<< HEAD
            Assert.assertTrue("Failure- speed limit for junction not in range", (speedLimitJunction >= minSpeedLimit[0]) && (speedLimitJunction <= maxSpeedLimit[0]) );
            Assert.assertTrue("Failure- speed limit for straight road not in range", (speedLimitStraight >= minSpeedLimit[1]) && (speedLimitStraight <= maxSpeedLimit[1]) );
            Assert.assertTrue("Failure- speed limit for curvy road not in range", (speedLimitCurve>= minSpeedLimit[2]) && (speedLimitCurve <= maxSpeedLimit[2]) );
            Assert.assertTrue("Failure- speed limit for green light not in range", (greenTime >= minTrafficLight[0]) && (greenTime <= maxTrafficLight[0]) );
            Assert.assertTrue("Failure- speed limit for amber light not in range", (amberTime >= minTrafficLight[1]) && (amberTime <= maxTrafficLight[1]) );
            Assert.assertTrue("Failure- speed limit for red light not in range", (redTime >= minTrafficLight[2]) && (redTime <= maxTrafficLight[2]) );
=======
            Assert.assertTrue("Failure- speed limit for junction not in range", (speedLimitJunction >= minSpeedLimit[1]) && (speedLimitJunction <= maxSpeedLimit[1]) );
            Assert.assertTrue("Failure- speed limit for straight road not in range", (speedLimitStraight >= minSpeedLimit[2]) && (speedLimitStraight <= maxSpeedLimit[2]) );
            Assert.assertTrue("Failure- speed limit for curvy road not in range", (speedLimitCurve>= minSpeedLimit[3]) && (speedLimitCurve <= maxSpeedLimit[3]) );
            Assert.assertTrue("Failure- speed limit for green light not in range", (greenTime >= minTrafficLight[1]) && (greenTime <= maxTrafficLight[1]) );
            Assert.assertTrue("Failure- speed limit for amber light not in range", (amberTime >= minTrafficLight[2]) && (amberTime <= maxTrafficLight[2]) );
            Assert.assertTrue("Failure- speed limit for red light not in range", (redTime >= minTrafficLight[3]) && (redTime <= maxTrafficLight[3]) );
>>>>>>> refs/remotes/retnolaras/master
            
        }
                
    }
    @Test
    public void testSetCustomisedPolicy()
    {
        int speedLimitJunction;
        int speedLimitStraight;
        int speedLimitCurve;
        int greenTime;
        int redTime;
        int amberTime;
        int[] minSpeedLimit = {20,30,20};
<<<<<<< HEAD
        int[] minTrafficLight = {30,5,10};
        
                         
        testPolicy = Policy.setPolicy(1, minSpeedLimit, null, minTrafficLight, null);
=======
        int[] maxSpeedLimit = null;
        int[] minTrafficLight = {30,5,10};
        int[] maxTrafficLight = null;
                          
        testPolicy = Policy.setPolicy(2, minSpeedLimit, maxSpeedLimit, minTrafficLight, maxTrafficLight);
>>>>>>> refs/remotes/retnolaras/master
        Assert.assertNotNull("Failure - must be not null policy", testPolicy);
        if (testPolicy != null)
        {
            speedLimitJunction = testPolicy.getJunctionSpeedLimit();
            speedLimitStraight = testPolicy.getStraightRoadSpeedLimit();
            speedLimitCurve = testPolicy.getCurvyRoadSpeedLimit();
            greenTime = testPolicy.getGreenTrafficLightTime();
            redTime = testPolicy.getRedTrafficLightTime();
            amberTime = testPolicy.getAmberTrafficLightTime();
<<<<<<< HEAD
            Assert.assertTrue("Failure- speed limit for junction not correct", (speedLimitJunction == minSpeedLimit[0]));
            Assert.assertTrue("Failure- speed limit for straight road not correct", (speedLimitStraight== minSpeedLimit[1]) );
            Assert.assertTrue("Failure- speed limit for curvy road not correct", (speedLimitCurve == minSpeedLimit[2]) );
            Assert.assertTrue("Failure- speed limit for green light not correct", (greenTime == minTrafficLight[0])  );
            Assert.assertTrue("Failure- speed limit for amber light not correct", (amberTime == minTrafficLight[1]) );
            Assert.assertTrue("Failure- speed limit for red light not correct", (redTime == minTrafficLight[2]));
=======
            Assert.assertTrue("Failure- speed limit for junction not correct", (speedLimitJunction == minSpeedLimit[1]));
            Assert.assertTrue("Failure- speed limit for straight road not correct", (speedLimitStraight== minSpeedLimit[2]) );
            Assert.assertTrue("Failure- speed limit for curvy road not correct", (speedLimitCurve == minSpeedLimit[3]) );
            Assert.assertTrue("Failure- speed limit for green light not correct", (greenTime == minTrafficLight[1])  );
            Assert.assertTrue("Failure- speed limit for amber light not correct", (amberTime == minTrafficLight[2]) );
            Assert.assertTrue("Failure- speed limit for red light not correct", (redTime == minTrafficLight[3]));
>>>>>>> refs/remotes/retnolaras/master
            
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
        int[] minSpeedLimit = {20,30,20};
<<<<<<< HEAD
        int[] minTrafficLight = {30,5,10};
                          
        Policy expectedPolicy = Policy.setPolicy(1, minSpeedLimit, null, minTrafficLight, null);
=======
        int[] maxSpeedLimit = null;
        int[] minTrafficLight = {30,5,10};
        int[] maxTrafficLight = null;
                          
        Policy expectedPolicy = Policy.setPolicy(2, minSpeedLimit, maxSpeedLimit, minTrafficLight, maxTrafficLight);
>>>>>>> refs/remotes/retnolaras/master
        
        testPolicy = Policy.getPolicyInstance();
        
        Assert.assertNotNull("Failure - must be not null policy", testPolicy);
        Assert.assertEquals("Failure- must return the predefined policy", expectedPolicy, testPolicy);
       
                
    }
}
