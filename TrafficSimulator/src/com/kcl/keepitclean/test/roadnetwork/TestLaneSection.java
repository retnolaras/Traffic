package com.kcl.keepitclean.test.roadnetwork;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kcl.keepitclean.main.roadnetwork.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.LeftLane;
import com.kcl.keepitclean.main.roadnetwork.MiddleLane;
import com.kcl.keepitclean.main.roadnetwork.RightLane;
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
		laneSection = lf.getLaneSection("singlelane");
		laneSection.putVehicleOnSection(vehicle);
		assertEquals(laneSection.hasVehicleOnSeciton(), true);
	}
	
	@Test
	public void testRemoveVehicleFromSection() {
		laneSection = lf.getLaneSection("singlelane");
		laneSection.putVehicleOnSection(vehicle);
		laneSection.removeVehicleFromSection();
		assertEquals(laneSection.hasVehicleOnSeciton(), false);
	}
	
	@Test
	public void testLaneFactoryBuildMiddleLane() {
		laneSection = lf.getLaneSection("MIDDLELANE");
		assertEquals(laneSection.getClass().getName(), MiddleLane.class.getName());
	}
	
	@Test
	public void testLaneFactoryBuildRightLane() {
		laneSection = lf.getLaneSection("RIGHTLANE");
		assertEquals(laneSection.getClass().getName(), RightLane.class.getName());
	}
	
	@Test
	public void testLaneFactoryBuildLeftLane() {
		laneSection = lf.getLaneSection("LEFTLANE");
		assertEquals(laneSection.getClass().getName(), LeftLane.class.getName());
	}

}
