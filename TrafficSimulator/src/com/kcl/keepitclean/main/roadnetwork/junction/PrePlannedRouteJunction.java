package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.Road;

public class PrePlannedRouteJunction implements Junction {

	private List<Road> roadsLeavingJunction;
	private List<Road> roadsEnteringJunction;
	
	List<LaneSection> sectionsOfJunction;
	
	private Map<String, List<LaneSection>> mapOfInputRoadsToOutputRoads;
	
	public PrePlannedRouteJunction(List<Road> roadsEnteringThisJunction, List<Road> roadsLeavingThisJunction) {
		this.roadsEnteringJunction = roadsEnteringThisJunction;
		this.roadsLeavingJunction = roadsLeavingThisJunction;
		
		generateSectionsOfJunction(roadsLeavingThisJunction.size());
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

	private void generateSectionsOfJunction(int numberOfSections) {
		this.sectionsOfJunction = new ArrayList<LaneSection>();
		LaneFactory lf = new LaneFactory();
		for (int index = 0; index < numberOfSections;index++) {
			sectionsOfJunction.add(lf.produceLaneSection("SingleLane"));
		}
	}

	@Override
	public List<LaneSection> produceRoute(Point endCoordinateOfCurrentRoad, Point startCoordinateOfNextRoad) {
		return sectionsOfJunction;
	}
	
	public Map<String, List<LaneSection>> getMappings() {
		return mapOfInputRoadsToOutputRoads;
	}
}