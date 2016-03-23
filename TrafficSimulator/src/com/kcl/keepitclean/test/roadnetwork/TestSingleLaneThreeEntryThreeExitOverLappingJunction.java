package com.kcl.keepitclean.test.roadnetwork;

import static org.junit.Assert.*;

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
 * Test class for a 'T-Junction' with input and output roads of 1 width
 * 
 * 
 * For example the junction would appear like this.
 * 
 * 			 ^	 |
 * 			 |	 |
 * 			 |	 |
 * 			 |	 V
 * -------->[J2][J3]
 * <--------[J0][J1]
 * 			 ^	 |
 * 			 |	 |
 * 			 |	 |
 * 			 |	 V
 * 			 
 * 
 * @author igalna
 *
 */
public class TestSingleLaneThreeEntryThreeExitOverLappingJunction {

	RoadFactory rf;
	
	private Road inputRoad;
	private Road inputRoad1;
	private Road inputRoad2;
	
	private Road outputRoad;
	private Road outputRoad1;
	private Road outputRoad2;
	
	List<Road> roadsGoingIntoJunction;
	List<Road> roadsLeavingJunction;
	
	Junction junction;
	
	List<LaneSection> r1;
	List<LaneSection> r2;
	
	LaneSection expected;
	LaneSection actual;
	
	@Before
	public void buildSingleLaneOverlappingJunction() {
		
		rf = new RoadFactory(new LaneFactory());
		
		roadsGoingIntoJunction = new ArrayList<Road>();
		roadsLeavingJunction = new ArrayList<Road>();
		
		int width = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		inputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		inputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) inputRoad1).setEndCoordinate(new Point(1,3));
		((ListOfListsRoadImpl) inputRoad2).setEndCoordinate(new Point(3,3));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(3,1));
		((ListOfListsRoadImpl) outputRoad1).setStartCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad2).setStartCoordinate(new Point(1,3));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsGoingIntoJunction.add(inputRoad1);
		roadsGoingIntoJunction.add(inputRoad2);
		roadsLeavingJunction.add(outputRoad);
		roadsLeavingJunction.add(outputRoad1);
		roadsLeavingJunction.add(outputRoad2);
		
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
		outputRoad = null;
		outputRoad1 = null;
		outputRoad2 = null;
		
		junction = null;
	}
	
	@Test
	public void testOverLapBetweenZerothEntryFirstExitAndSecondExit() {
		List<LaneSection> r1 = junction.produceRoute(new Point(1,1), new Point(1,1));
		List<LaneSection> r2 = junction.produceRoute(new Point(1,1), new Point(1,3));
		
		expected = r1.get(0);
		
		assertEquals(expected, r2.get(0));
	}

	@Test
	public void testOverLapBetweenFirsthEntryZerothExitAndSecondEntryZerothExit() {
		List<LaneSection> r1 = junction.produceRoute(new Point(1,3), new Point(3,1));
		List<LaneSection> r2 = junction.produceRoute(new Point(3,3), new Point(3,1));
		
		expected = r1.get(1);
		
		assertEquals(expected, r2.get(1));
	}
	
}
