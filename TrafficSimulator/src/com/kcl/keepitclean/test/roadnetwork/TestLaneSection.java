package com.kcl.keepitclean.test.roadnetwork;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LeftLane;
import com.kcl.keepitclean.main.roadnetwork.laneSection.MiddleLane;
import com.kcl.keepitclean.main.roadnetwork.laneSection.RightLane;
import com.kcl.keepitclean.main.vehicle.Vehicle;

/**
 * 
 * 
 * @author igalna
 * 
 * Test class for the RoadNetwork
 *
 */
public class TestLaneSection {
	
	private Vehicle vehicle;
	private LaneSection laneSection;
	
	private LaneFactory lf = new LaneFactory();

	@Before
	public void buildBeforeEach() {
		vehicle = new Vehicle();
	}
	
	@After
	public void destroyAfterEach() {
		vehicle = null;
		laneSection = null;
	}
	
	@Test
	public void testVehicleOnLaneSection() {
		laneSection = lf.produceLaneSection("singlelane");
		laneSection.putVehicleOnSection(vehicle);
		assertEquals(laneSection.hasVehicleOnSeciton(), true);
	}
	
	@Test
	public void testRemoveVehicleFromSection() {
		laneSection = lf.produceLaneSection("singlelane");
		laneSection.putVehicleOnSection(vehicle);
		laneSection.removeVehicleFromSection();
		assertEquals(laneSection.hasVehicleOnSeciton(), false);
	}
	
	@Test
	public void testLaneFactoryBuildMiddleLane() {
		laneSection = lf.produceLaneSection("MIDDLELANE");
		assertEquals(laneSection.getClass().getName(), MiddleLane.class.getName());
	}
	
	@Test
	public void testLaneFactoryBuildRightLane() {
		laneSection = lf.produceLaneSection("RIGHTLANE");
		assertEquals(laneSection.getClass().getName(), RightLane.class.getName());
	}
	
	@Test
	public void testLaneFactoryBuildLeftLane() {
		laneSection = lf.produceLaneSection("LEFTLANE");
		assertEquals(laneSection.getClass().getName(), LeftLane.class.getName());
	}

}
