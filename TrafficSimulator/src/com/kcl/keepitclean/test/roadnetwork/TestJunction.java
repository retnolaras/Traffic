package com.kcl.keepitclean.test.roadnetwork;

import static org.junit.Assert.assertEquals;

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
	public void buildJunctionWithOneInputOneOutputWidthOne() {
		int widthOfJunction = 1;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfJunction, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void buildJunctionWithOneInputOneOutputWidthTwo() {
		int widthOfJunction = 2;
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfJunction, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void buildJunctionWithOneInputOneOutputWidthThree() {
		int widthOfJunction = 3;
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfJunction, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void buildJunctionWithOneInputOneOutputWidthFour() {
		int widthOfJunction = 4;
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfJunction);
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfJunction, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
	
	@Test
	public void buildJunctionWithInputWidthOneOutputWidthTwo() {
		int widthOfInput = 1;
		int widthOfOutput = 2;
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfInput);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, widthOfOutput);
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
		
		assertEquals(widthOfOutput, ((PrePlannedRouteJunction) junction).getLaneSectionsOfJunction().size());
	}
}
