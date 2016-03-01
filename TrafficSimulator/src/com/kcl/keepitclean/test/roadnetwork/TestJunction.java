package com.kcl.keepitclean.test.roadnetwork;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
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
	
	private RoadFactory rf = new RoadFactory(new LaneFactory());
	
	@Before
	public void buildBefore() {
		List<Road> roadsGoingIntoJunction = new ArrayList<Road>();
		List<Road> roadsLeavingJunction = new ArrayList<Road>();
		
		inputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, 1);
		outputRoad = rf.produceRoad("ListOfListsRoadImpl", 10, 1);
		
		((ListOfListsRoadImpl) inputRoad).setEndCoordinate(new Point(10, 10));
		((ListOfListsRoadImpl) outputRoad).setStartCoordinate(new Point(12, 10));
		
		roadsGoingIntoJunction.add(inputRoad);
		roadsLeavingJunction.add(outputRoad);
		
		junction = new PrePlannedRouteJunction(roadsGoingIntoJunction, roadsLeavingJunction);
	}
	
	@Test
	public void testConnectTwoRoads() {
		Map<String, List<LaneSection>> maps = ((PrePlannedRouteJunction) junction).getMappings();
		assertEquals(maps.size(), 1);
	}
	@Test(expected = NullPointerException.class)
	public void testThatEmptyJunctionHasNoMappings() {
		PrePlannedRouteJunction j = new PrePlannedRouteJunction(null, null);
		assertEquals(j.getMappings().size(), 0);
	}
}
