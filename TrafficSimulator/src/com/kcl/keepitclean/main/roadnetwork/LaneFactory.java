package com.kcl.keepitclean.main.roadnetwork;

/**
 * 
 * @author igalna
 * 
 * LaneFactory factory class. Produces objects of the LaneSection Type.
 *
 */
public class LaneFactory {

	public LaneSection getLaneSection(String laneType) {
		if (laneType == null) {
			return null;
		}
		if (laneType.equalsIgnoreCase("leftlane")) {
			return new LeftLane();
		}
		else if (laneType.equalsIgnoreCase("rightlane")) {
			return new RightLane();
		}
		else if (laneType.equalsIgnoreCase("middlelane")) {
			return new MiddleLane();
		}
		else if (laneType.equalsIgnoreCase("singleLane")) {
			return new SingleLane();
		}
		return null;
	}
}