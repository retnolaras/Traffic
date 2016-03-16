package com.kcl.keepitclean.test.roadnetwork;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.PrePlannedRouteJunction;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;

/**
 * 
 * Overlapping Junctions are ones where the sections of the junction are used by multiple routes through the junction.
 * 
 * For Example
 * 
 * 			   ^
 * 			   ^
 * 			  [R1]
 * 		    >>[R2][J2][J3][R2]>>
 * 		 	  [J0][J1]
 * 			  [R1]
 * 			   ^
 * 			   ^
 * 
 * Here is a Junction with four sections and two example routes through the junction 'R1' and 'R2'.
 * The arrows show the direction of travel.
 * The routes R1 and R2 both go over junction section 'J2'.
 * 
 * @author igalna
 *
 */

public class TestSingleLaneFourEntryFourExitOverlappingJunction {

	RoadFactory rf;
	
	private Road inputRoad;
	private Road inputRoad1;
	private Road inputRoad2;
	private Road inputRoad3;
	
	private Road outputRoad;
	private Road outputRoad1;
	private Road outputRoad2;
	private Road outputRoad3;
	
	List<Road> roadsGoingIntoJunction;
	List<Road> roadsLeavingJunction;
	
	Junction junction;
	
	List<LaneSection> r1;
	List<LaneSection> r2;
	
	@Before
	public void buildSingleLaneOverlappingJunction() {
		
		rf = new RoadFactory(new LaneFactory());
		
		roadsGoingIntoJunction = new ArrayList<Road>();
		roadsLeavingJunction = new ArrayList<Road>();
		
		int width = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		inputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		inputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		inputRoad3 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad3 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) inputRoad1).setEndCoordinate(new Point(1,3));
		((ListOfListsRoadImpl) inputRoad2).setEndCoordinate(new Point(3,3));
		((ListOfListsRoadImpl) inputRoad3).setEndCoordinate(new Point(3,1));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(3,1));
		((ListOfListsRoadImpl) outputRoad1).setStartCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad2).setStartCoordinate(new Point(1,3));
		((ListOfListsRoadImpl) outputRoad3).setStartCoordinate(new Point(3,3));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsGoingIntoJunction.add(inputRoad1);
		roadsGoingIntoJunction.add(inputRoad2);
		roadsGoingIntoJunction.add(inputRoad3);
		roadsLeavingJunction.add(outputRoad);
		roadsLeavingJunction.add(outputRoad1);
		roadsLeavingJunction.add(outputRoad2);
		roadsLeavingJunction.add(outputRoad3);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
	}
	
	@After
	public void tearDownSingleLaneOverlappingJunction() {
		rf = null;
		
		roadsGoingIntoJunction = null;
		roadsLeavingJunction = null;
		
		inputRoad = null;
		inputRoad1 = null;
		inputRoad2 = null;
		inputRoad3 = null;
		outputRoad = null;
		outputRoad1 = null;
		outputRoad2 = null;
		outputRoad3 = null;
		
		junction = null;
	}
	
	/**
	 * 
	 * Test Example map
	 * 
	 * 			 ^
	 * 			[R2]
	 * 			[J2][J3]
	 * 	   <[R1][J0][J1]
	 * 			[R1]
	 * 			[R2]
	 * 			 ^
	 * 
	 */
	@Test
	public void testOverLapBetweenZerothEntryFirstExitAndSecondExit() {
		List<LaneSection> r1 = junction.produceRoute(new Point(1,1), new Point(1,1));
		List<LaneSection> r2 = junction.produceRoute(new Point(1,1), new Point(1,3));
		
		assertEquals(r1.get(0), r2.get(0));
	}
	
	
	/**
	 * 
	 * Test Example map
	 * 
	 * 			 
	 * 			
	 * 			[J2][J3][R2]>
	 * 	   <[R1][J0][J1]
	 * 			[R1]
	 * 			[R2]
	 * 			 ^
	 * 
	 */
	@Test
	public void testOverLapBetweenZerothEntryFirstExitAndThirdExit() {
		List<LaneSection> r1 = junction.produceRoute(new Point(1,1), new Point(1,1));
		List<LaneSection> r2 = junction.produceRoute(new Point(1,1), new Point(3,3));
		
		assertEquals(r1.get(0), r2.get(0));
	}
	
	/**
	 * 
	 * Test Example map
	 * 
	 * 			  ^
	 * 			 [R1]
	 * 	>[R2][R1][J2][J3][R2]>
	 * 	   		 [J0][J1]			
	 * 
	 */
	@Test
	public void testOverLapBetweenFirstEntrySecondExitAndThirdExit() {
		List<LaneSection> route = junction.produceRoute(new Point(1,3), new Point(1,3));
		List<LaneSection> anotherRoute = junction.produceRoute(new Point(1,3), new Point(3,3));
		
		assertEquals(route.get(0), anotherRoute.get(0));
	}
	
	/**
	 * 
	 * Test Example map
	 * 
	 * 			 ^
	 * 			[R1]
	 * 	[R2][R1][J2][J3]
	 * 	   		[J0][J1]
	 * 				[R2]
	 * 				 V
	 * 
	 */
	@Test
	public void testOverLapBetweenFirstEntrySecondExitAndZerothExit() {
		List<LaneSection> route = junction.produceRoute(new Point(1,3), new Point(1,3));
		List<LaneSection> anotherRoute = junction.produceRoute(new Point(1,3), new Point(3,1));
		
		assertEquals(route.get(0), anotherRoute.get(0));
	}
	
	/**
	 * 
	 * Test Example map
	 * 
	 * 			
	 * 	   >[R1][J2][J3]
	 * 	   <[R2][J0][J1][R2]<
	 * 				[R1]
	 * 				 V
	 * 
	 */
	@Test
	public void testOverLapBetweenFirstEntryZerothExitAndThirdEntryFirstExit() {
		List<LaneSection> route = junction.produceRoute(new Point(1,3), new Point(3,1));
		List<LaneSection> anotherRoute = junction.produceRoute(new Point(3,1), new Point(3,1));
		
		assertEquals(route.get(1), anotherRoute.get(0));
	}
	
	/**
	 * 
	 * Test Example map
	 * 			
	 * 			 ^
	 * 			[R2]
	 * 			[R1]
	 * 			[J2][J3]
	 * 	   		[J0][J1][R2]<
	 * 			[R1]
	 * 			 ^	
	 * 
	 */
	@Test
	public void testOverLapBetweenZerothEntrySecondExitAndThirdEntrySecondExit() {
		List<LaneSection> route = junction.produceRoute(new Point(1,1), new Point(1,3));
		List<LaneSection> anotherRoute = junction.produceRoute(new Point(3,1), new Point(1,3));
		
		assertEquals(route.get(1), anotherRoute.get(1));
	}
	
	/**
	 * 
	 * Test Example map
	 * 
	 * 			 ^
	 * 			[R1]
	 * 	   >[R2][J2][J3]
	 * 	   		[J0][J1][R2]>
	 * 			[R1]
	 * 			 ^
	 * 
	 */
	@Test
	public void testOverLapBetweenZerothEntrySecondExitAndFirstEntryThirdExit() {
		List<LaneSection> route = junction.produceRoute(new Point(1,1), new Point(1,3));
		List<LaneSection> anotherRoute = junction.produceRoute(new Point(1,3), new Point(3,3));
		
		assertEquals(route.get(1), anotherRoute.get(0));
	}
}
