package com.kcl.keepitclean.main.roadnetwork.road;

import java.util.ArrayList;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;


/**
 * 
 * 
 * @author igalna
 * 
 * A RoadFactory is responsible for creating objects of Roads.
 * 
 * 
 */
public class RoadFactory {
	
	
	private LaneFactory lf;

	/**
	 * 
	 * Constructor takes a LaneFactory type which the RoadFactory uses to populate the lanes of the road with LaneSections
	 * 
	 * @param lf
	 * 
	 */
	public RoadFactory(LaneFactory lf) {
		this.lf = lf;
	}
	
	/**
	 * 
	 * produceRoad() is the method RoadFactory users call to generate Roads
	 * 
	 * @param roadType String value with the implementation of Road a user wants
	 * @param length int length of the road
	 * @param numberOfLanes int width of the road in Lanes
	 * @return Road is the Road type produced by the method to the users specification
	 */
	public Road produceRoad(String roadType, int length, int numberOfLanes) {
		Road road;
		
		if (roadType.equalsIgnoreCase("listoflistsroadimpl")) {
			road = new ListOfListsRoadImpl(length, numberOfLanes);
			((ListOfListsRoadImpl) road).setLaneSectionsOfRoad(generateLaneSectionArray(length, numberOfLanes));
			return road;
		}
		return null;
	}

	/**
	 * 
	 * generateLaneSectionArray() is used to create a List of Lists for the ListOfListsRoadImpl
	 * 
	 * @param length int length of each of the lanes
	 * @param numberOfLanes int width of the road
	 * @return List of List<LaneSection>s
	 */
	private List<List<LaneSection>> generateLaneSectionArray(int length, int numberOfLanes) {
		List<List<LaneSection>> listOfLaneSectionArrays = new ArrayList<List<LaneSection>>();
		
		if (numberOfLanes == 1) {
			listOfLaneSectionArrays.add(createArray(length, "singleLane"));
		}
		else {
			for (int x = 0; x < numberOfLanes; x++) {
				if (x == 0) {
					listOfLaneSectionArrays.add(createArray(length, "leftLane"));
				}
				else if (x > 0 && x < numberOfLanes - 1) {
					listOfLaneSectionArrays.add(createArray(length, "middleLane"));
				}
				else {
					listOfLaneSectionArrays.add(createArray(length, "rightLane"));
				}
			}
		}
		return listOfLaneSectionArrays;
	}

	/**
	 * 
	 * method to create individual List<LaneSection> which are then used to populate a road
	 * uses the LaneFactory to populate a Lists with objects of the Lane type.
	 * 
	 * @param length length of the lane
	 * @param laneType String to pass to the LaneFactory so it knows what type of LaneSection to produce
	 * @return returns a List<LaneSection> populated with Sections of Lane
	 */
	private List<LaneSection> createArray(int length, String laneType) {
		List<LaneSection> array = new ArrayList<LaneSection>();
		
		for (int y = 0; y < length; y++) {
			array.add(lf.produceLaneSection(laneType));
		}
		return array;
	} 
}
