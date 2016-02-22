package com.kcl.keepitclean.main.roadnetwork.laneSection;

/**
 * 
 * @author igalna
 * 
 * LaneFactory factory class. Produces objects of the LaneSection Type.
 *
 */
public class LaneFactory {

	public LaneSection produceLaneSection(String laneType) {
		if (laneType == null) {
			return null;
		}
		if (laneType.equalsIgnoreCase("leftLane")) {
			return new LeftLane();
		}
		else if (laneType.equalsIgnoreCase("rightLane")) {
			return new RightLane();
		}
		else if (laneType.equalsIgnoreCase("middleLane")) {
			return new MiddleLane();
		}
		else if (laneType.equalsIgnoreCase("singleLane")) {
			return new SingleLane();
		}
		return null;
	}
}