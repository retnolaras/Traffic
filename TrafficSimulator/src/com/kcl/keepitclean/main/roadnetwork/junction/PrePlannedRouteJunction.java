package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.Road;

/**
 * 
 * Class for PrePlannedRouteJunction
 * 
 * Is a node for connecting different Roads together.
 * Allowing Vehicles to pass from Road to Road by providing them a PrePlannedRoute across a junction.
 * 
 * Dynamically builds junctions based on input roads and output roads
 * 
 * 
 * @author igalna
 *
 */
public class PrePlannedRouteJunction implements Junction {

	private List<Road> roadsLeavingJunction;
	private List<Road> roadsEnteringJunction;
	
	List<LaneSection> sectionsOfJunction;
	
	private Map<String, List<LaneSection>> mapOfInputRoadsToOutputRoads;
	
	public PrePlannedRouteJunction(List<Road> roadsEnteringThisJunction, List<Road> roadsLeavingThisJunction) {
		this.roadsEnteringJunction = roadsEnteringThisJunction;
		this.roadsLeavingJunction = roadsLeavingThisJunction;
		
		generateSectionsOfJunction();
		createMappings();
	}
	
	private void createMappings() {
		mapOfInputRoadsToOutputRoads = new HashMap<>();
		Point inputRoadCoords;
		Point exitRoadCoords;
		for (Road road : roadsEnteringJunction) {
			inputRoadCoords = road.getEndCoordinates();
			for (Road exitRoad : roadsLeavingJunction) {
				exitRoadCoords = exitRoad.getStartCoordinates();
				mapOfInputRoadsToOutputRoads.put("" + inputRoadCoords + exitRoadCoords, produceRoute(inputRoadCoords, exitRoadCoords));
			}
		}
		
	}

	private void generateSectionsOfJunction() {
		this.sectionsOfJunction = new ArrayList<LaneSection>();
		LaneFactory lf = new LaneFactory();
		
		int widthOfJunction;
		
		if (roadsEnteringJunction.size() == 1 && roadsLeavingJunction.size() == 1) {
			if (roadsEnteringJunction.get(0).getNumberOfLanes() == roadsLeavingJunction.get(0).getNumberOfLanes()) {
				widthOfJunction = roadsEnteringJunction.get(0).getNumberOfLanes();
				for (int x = 0; x < widthOfJunction; x++) {
					sectionsOfJunction.add(lf.produceLaneSection("SingleLane"));
				}
			}
			else {
				if (roadsEnteringJunction.get(0).getNumberOfLanes() < roadsLeavingJunction.get(0).getNumberOfLanes()) {
					widthOfJunction = roadsLeavingJunction.get(0).getNumberOfLanes();
					for (int x = 0; x < widthOfJunction; x++) {
						sectionsOfJunction.add(lf.produceLaneSection("SingleLane"));
					}
				}
				else {
					widthOfJunction = roadsEnteringJunction.get(0).getNumberOfLanes();
					for (int x = 0; x < widthOfJunction; x++) {
						sectionsOfJunction.add(lf.produceLaneSection("SingleLane"));
					}
				}
			}
		}
		else {
			System.out.println("Nope I'm HERE");
		}
	}

	@Override
	public List<LaneSection> produceRoute(Point endCoordinateOfCurrentRoad, Point startCoordinateOfNextRoad) {
		return sectionsOfJunction;
	}
	
	public Map<String, List<LaneSection>> getMappings() {
		return mapOfInputRoadsToOutputRoads;
	}
	
	public List<LaneSection> getLaneSectionsOfJunction() {
		return sectionsOfJunction;
	}
}