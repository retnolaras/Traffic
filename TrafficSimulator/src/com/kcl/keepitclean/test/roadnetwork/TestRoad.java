package com.kcl.keepitclean.test.roadnetwork;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LeftLane;
import com.kcl.keepitclean.main.roadnetwork.laneSection.MiddleLane;
import com.kcl.keepitclean.main.roadnetwork.laneSection.RightLane;
import com.kcl.keepitclean.main.roadnetwork.laneSection.SingleLane;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;

public class TestRoad {
	
	private RoadFactory rf = new RoadFactory(new LaneFactory());
	private Road singleLaneRoad;
	private Road twoLaneRoad;
	private Road threeLaneRoad;
	private Road fourLaneRoad;
	
	private String classOfRoad;
	private String typeOfLaneSection;
	
	private static final double DELTA = 1e-15;
	
	@Before
	public void setupTestRoad() {
		singleLaneRoad = rf.produceRoad("listoflistsroadimpl", 10, 1);
		twoLaneRoad = rf.produceRoad("listoflistsroadimpl", 10, 2);
		threeLaneRoad = rf.produceRoad("listoflistsroadimpl", 10, 3);
		fourLaneRoad = rf.produceRoad("listoflistsroadimpl", 10, 4);
		
	}
	
	@After
	public void tearDownRoads() {
		singleLaneRoad = null;
		twoLaneRoad = null;
		threeLaneRoad = null;
		fourLaneRoad = null;
	}
	
	/*
	 * 
	 * test the creation of a ListOfListsRoadImpl
	 * 
	 */
	@Test
	public void testCreateRoadImplRoad() {
		classOfRoad = singleLaneRoad.getClass().getName();
		assertEquals(classOfRoad, ListOfListsRoadImpl.class.getName());
	}
	
	/*
	 * 
	 * tests for a road with a single lane
	 * 
	 */
	@Test
	public void testCreateSingleLaneRoad() {
		typeOfLaneSection = ((ListOfListsRoadImpl) singleLaneRoad).getLaneSectionsOfRoad().get(0).get(0).getClass().getName();
		assertEquals(typeOfLaneSection, SingleLane.class.getName());
	}
	
	// A Single Lane ListOfLists road should have a List of one List<LaneSection>.
	// If the road is 10 long the List<LaneSection> should be .size() == 10.
	@Test
	public void testSingleLaneRoadLengthOfRoad() {
		assertEquals(((ListOfListsRoadImpl) singleLaneRoad).getLaneSectionsOfRoad().get(0).size(), 10);
	}
	
	// A Single Lane ListOfLists road should have a width of 1
	@Test
	public void testSingleLaneRoadWidthOfRoad() {
		assertEquals(((ListOfListsRoadImpl) singleLaneRoad).getLaneSectionsOfRoad().size(), 1);
	}
	
	/*
	 * 
	 * tests for a two lane road
	 * 
	 */
	@Test
	public void testCreateTwoLaneRoadLeftLane() {
		typeOfLaneSection = ((ListOfListsRoadImpl) twoLaneRoad).getLaneSectionsOfRoad().get(0).get(0).getClass().getName();
		assertEquals(typeOfLaneSection, LeftLane.class.getName());
	}
	
	@Test
	public void testCreateTwoLaneRoadRightLane() {
		typeOfLaneSection = ((ListOfListsRoadImpl) twoLaneRoad).getLaneSectionsOfRoad().get(1).get(4).getClass().getName();
		assertEquals(typeOfLaneSection, RightLane.class.getName());
	}
	
	// A TwoLane ListOfListsRoad should have two lanes each with a .size() of whatever the length of the road is.
	@Test
	public void testTwoLaneRoadLengthOfRoad() {
		assertEquals(((ListOfListsRoadImpl) twoLaneRoad).getLaneSectionsOfRoad().get(0).size(), 10);
		assertEquals(((ListOfListsRoadImpl) twoLaneRoad).getLaneSectionsOfRoad().get(1).size(), 10);
	}
	
	// A Two Lane ListOfListsRoad should have a width of two
	@Test
	public void testTwoLaneRoadWidthOfRoad() {
		assertEquals(((ListOfListsRoadImpl) twoLaneRoad).getLaneSectionsOfRoad().size(), 2);
	}
	
	/*
	 * 
	 * tests for a three lane road
	 * 
	 */
	@Test
	public void testCreateThreeLaneRoadLeftLane() {
		typeOfLaneSection = ((ListOfListsRoadImpl) threeLaneRoad).getLaneSectionsOfRoad().get(0).get(0).getClass().getName();
		assertEquals(typeOfLaneSection, LeftLane.class.getName());
	}
	
	@Test
	public void testCreateThreeLaneRoadMiddleLane() {
		typeOfLaneSection = ((ListOfListsRoadImpl) threeLaneRoad).getLaneSectionsOfRoad().get(1).get(4).getClass().getName();
		assertEquals(typeOfLaneSection, MiddleLane.class.getName());
	}
	
	@Test
	public void testCreateThreeLaneRoadRightLane() {
		typeOfLaneSection = ((ListOfListsRoadImpl) threeLaneRoad).getLaneSectionsOfRoad().get(2).get(9).getClass().getName();
		assertEquals(typeOfLaneSection, RightLane.class.getName());
	}

	// A Three Lane ListOfLists Road should have three lanes each with a .size() of however long the road is.
	@Test
	public void testThreeLaneRoadLengthOfRoad() {
		assertEquals(((ListOfListsRoadImpl) threeLaneRoad).getLaneSectionsOfRoad().get(0).size(), 10);
		assertEquals(((ListOfListsRoadImpl) threeLaneRoad).getLaneSectionsOfRoad().get(1).size(), 10);
		assertEquals(((ListOfListsRoadImpl) threeLaneRoad).getLaneSectionsOfRoad().get(2).size(), 10);
	}
	
	// A Three Lane ListOfLists Road should have a width of three
	@Test
	public void testThreeLaneRoadWidthOfRoad() {
		assertEquals(((ListOfListsRoadImpl) threeLaneRoad).getLaneSectionsOfRoad().size(), 3);
	}
	
	/*
	 * 
	 * Tests for creating a road with four lanes
	 * 
	 */
	
	@Test
	public void testCreateFourLaneRoadLeftLane() {
		typeOfLaneSection = ((ListOfListsRoadImpl) fourLaneRoad).getLaneSectionsOfRoad().get(0).get(1).getClass().getName();
		assertEquals(typeOfLaneSection, LeftLane.class.getName());
	}
	
	@Test
	public void testCreateFourLaneRoadFirstMiddleLane() {
		typeOfLaneSection = ((ListOfListsRoadImpl) fourLaneRoad).getLaneSectionsOfRoad().get(1).get(3).getClass().getName();
		assertEquals(typeOfLaneSection, MiddleLane.class.getName());
	}
	@Test
	public void testCreateFourLaneRoadSecondMiddleLane() {
		typeOfLaneSection = ((ListOfListsRoadImpl) fourLaneRoad).getLaneSectionsOfRoad().get(2).get(2).getClass().getName();
		assertEquals(typeOfLaneSection, MiddleLane.class.getName());
	}
	
	@Test
	public void testCreateFourLaneRoadRightLane() {
		typeOfLaneSection = ((ListOfListsRoadImpl) fourLaneRoad).getLaneSectionsOfRoad().get(3).get(2).getClass().getName();
		assertEquals(typeOfLaneSection, RightLane.class.getName());
	}
	
	/*
	 * 
	 * tests for adding start and end coordinates to roads
	 * 
	 */
	@Test
	public void testSetStartCoordinatesForRoad() {
		Point point = new Point(1,1);
		((ListOfListsRoadImpl) singleLaneRoad).setStartCoordinates(point);
		int expected = 1;
		assertEquals(expected, singleLaneRoad.getStartCoordinates().getX(), DELTA);
		assertEquals(expected, singleLaneRoad.getStartCoordinates().getY(), DELTA);
	}
	
	@Test
	public void testSetEndCoordinatesForRoad() {
		Point point = new Point(221, 564);
		((ListOfListsRoadImpl) twoLaneRoad).setStartCoordinates(point);
		int expectedX = 221;
		int expectedY = 564;
		assertEquals(expectedX, twoLaneRoad.getStartCoordinates().getX(), DELTA);
		assertEquals(expectedY, twoLaneRoad.getStartCoordinates().getY(), DELTA);
	}
	
	
	@Test
	public void testSetSpeedLimit() {
		((ListOfListsRoadImpl) singleLaneRoad).setSpeedLimit(70);
		assertEquals(singleLaneRoad.getSpeedLimit(), 70);
	}
}