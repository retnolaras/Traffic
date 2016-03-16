package com.kcl.keepitclean.test.roadnetwork;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.PrePlannedRouteJunction;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;

public class TestJunction {

	private Junction junction;
	
	private Road inputRoad;
	private Road outputRoad;
	
	private List<Road> roadsGoingIntoJunction;
	private List<Road> roadsLeavingJunction;
	
	private RoadFactory rf = new RoadFactory(new LaneFactory());
	
	
	@Before
	public void buildBefore() {
		roadsGoingIntoJunction = new ArrayList<Road>();
		roadsLeavingJunction = new ArrayList<Road>();
	}
	
	@After
	public void tearDownAfter() {
		junction = null;
		
		inputRoad = null;
		outputRoad = null;
		
		roadsGoingIntoJunction = null;
		roadsLeavingJunction = null;
	}
	
	@Ignore
	@Test
	public void testConnectTwoRoads() {
		Map<String, List<LaneSection>> maps = ((PrePlannedRouteJunction) junction).getMappings();
		assertEquals(maps.size(), 1);
	}
	@Ignore
	@Test(expected = NullPointerException.class)
	public void testThatEmptyJunctionHasNoMappings() {
		PrePlannedRouteJunction j = new PrePlannedRouteJunction(null, null);
		assertEquals(j.getMappings().size(), 0);
	}
	
	@Test
	public void testBuildJunctionWithOneInputOneOutputWidthOne() {
		int widthOfJunction = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(5,5));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfJunction, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void testBuildJunctionWithOneInputOneOutputWidthTwo() {
		int widthOfJunction = 2;
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(5,5));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfJunction, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void testBuildJunctionWithOneInputOneOutputWidthThree() {
		int widthOfJunction = 3;
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(5,5));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfJunction, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void testBuildJunctionWithOneInputOneOutputWidthFour() {
		int widthOfJunction = 4;
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(5,5));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfJunction, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void testBuildJunctionWithInputWidthOneOutputWidthTwo() {
		int widthOfInput = 1;
		int widthOfOutput = 2;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfInput);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfOutput);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(5,5));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfOutput, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void testBuildJunctionWithInputWidthTwoOutputWidthOne() {
		int widthOfInput = 2;
		int widthOfOutput = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfInput);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfOutput);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(5,5));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfInput, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void testBuildJunctionWithThreeConnections() {
		int width = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road inputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road inputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road outputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road outputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) inputRoad1).setEndCoordinate(new Point(2,2));
		((ListOfListsRoadImpl) inputRoad2).setEndCoordinate(new Point(3,3));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(5,5));
		((ListOfListsRoadImpl) outputRoad1).setStartCoordinate(new Point(6,6));
		((ListOfListsRoadImpl) outputRoad2).setStartCoordinate(new Point(7,7));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsGoingIntoJunction.add(inputRoad1);
		roadsGoingIntoJunction.add(inputRoad2);
		roadsLeavingJunction.add(outputRoad);
		roadsLeavingJunction.add(outputRoad1);
		roadsLeavingJunction.add(outputRoad2);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(4, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void testBuildJunctionWithFourConnections() {
		int width = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road inputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road inputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road inputRoad3 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road outputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road outputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road outputRoad3 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) inputRoad1).setEndCoordinate(new Point(2,2));
		((ListOfListsRoadImpl) inputRoad2).setEndCoordinate(new Point(3,3));
		((ListOfListsRoadImpl) inputRoad3).setEndCoordinate(new Point(4,4));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(5,5));
		((ListOfListsRoadImpl) outputRoad1).setStartCoordinate(new Point(6,6));
		((ListOfListsRoadImpl) outputRoad2).setStartCoordinate(new Point(7,7));
		((ListOfListsRoadImpl) outputRoad3).setStartCoordinate(new Point(8,8));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsGoingIntoJunction.add(inputRoad1);
		roadsGoingIntoJunction.add(inputRoad2);
		roadsGoingIntoJunction.add(inputRoad3);
		roadsLeavingJunction.add(outputRoad);
		roadsLeavingJunction.add(outputRoad1);
		roadsLeavingJunction.add(outputRoad2);
		roadsLeavingJunction.add(outputRoad3);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(4, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void testBuildJunctionWithFourConnectionsWidthTwo() {
		int width = 2;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road inputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road inputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road inputRoad3 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road outputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road outputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		Road outputRoad3 = rf.produceRoad("ListOfListsRoadImpl", 10, width);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) inputRoad1).setEndCoordinate(new Point(2,2));
		((ListOfListsRoadImpl) inputRoad2).setEndCoordinate(new Point(3,3));
		((ListOfListsRoadImpl) inputRoad3).setEndCoordinate(new Point(4,4));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(5,5));
		((ListOfListsRoadImpl) outputRoad1).setStartCoordinate(new Point(6,6));
		((ListOfListsRoadImpl) outputRoad2).setStartCoordinate(new Point(7,7));
		((ListOfListsRoadImpl) outputRoad3).setStartCoordinate(new Point(8,8));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsGoingIntoJunction.add(inputRoad1);
		roadsGoingIntoJunction.add(inputRoad2);
		roadsGoingIntoJunction.add(inputRoad3);
		roadsLeavingJunction.add(outputRoad);
		roadsLeavingJunction.add(outputRoad1);
		roadsLeavingJunction.add(outputRoad2);
		roadsLeavingJunction.add(outputRoad3);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(16, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	/*
	 * Tests for the createMappings method of PreplannedRouteJunction
	 */
	
	@Test
	public void testCreateMappingOneToOne() {
		int widthOfJunction = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(2,2));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		Map<String, List<LaneSection>> map = ((PrePlannedRouteJunction) junction).getMappings();
		
		assertEquals(1, map.size());
		assertEquals(true, map.containsKey("1.0,1.0-2.0,2.0"));
	}
	
	@Test
	public void testCreateMappingOneToOneWidthTwo() {
		int widthOfJunction = 2;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(4,4));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(3,3));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		Map<String, List<LaneSection>> map = ((PrePlannedRouteJunction) junction).getMappings();
		
		assertEquals(1, map.size());
		assertEquals(true, map.containsKey("4.0,4.0-3.0,3.0"));
	}
	
	@Test
	public void testCreateMappingsTwoInputsTwoOutputs() {
		int widthOfJunction = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		Road inputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		Road outputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) inputRoad1).setEndCoordinate(new Point(2,2));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(3,3));
		((ListOfListsRoadImpl) outputRoad1).setStartCoordinate(new Point(4,4));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsGoingIntoJunction.add(inputRoad1);
		roadsLeavingJunction.add(outputRoad);
		roadsLeavingJunction.add(outputRoad1);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		Map<String, List<LaneSection>> map = ((PrePlannedRouteJunction) junction).getMappings();
		
		assertEquals(4, map.size());
		assertEquals(true, map.containsKey("1.0,1.0-3.0,3.0"));
		assertEquals(true, map.containsKey("1.0,1.0-4.0,4.0"));
		assertEquals(true, map.containsKey("2.0,2.0-3.0,3.0"));
		assertEquals(true, map.containsKey("2.0,2.0-4.0,4.0"));
	}
	
	@Test
	public void testCreateMappingsSingleInputMultipleOutputs() {
		int widthOfJunction = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		Road outputRoad1 = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		Road outputRoad2 = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(1,1));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(3,3));
		((ListOfListsRoadImpl) outputRoad1).setStartCoordinate(new Point(4,4));
		((ListOfListsRoadImpl) outputRoad2).setStartCoordinate(new Point(5,5));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		roadsLeavingJunction.add(outputRoad1);
		roadsLeavingJunction.add(outputRoad2);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		Map<String, List<LaneSection>> map = ((PrePlannedRouteJunction) junction).getMappings();
		
		assertEquals(3, map.size());
		assertEquals(true, map.containsKey("1.0,1.0-3.0,3.0"));
		assertEquals(true, map.containsKey("1.0,1.0-4.0,4.0"));
		assertEquals(true, map.containsKey("1.0,1.0-5.0,5.0"));
	}
}