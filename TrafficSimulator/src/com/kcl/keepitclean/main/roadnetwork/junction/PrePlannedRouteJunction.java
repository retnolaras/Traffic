package com.kcl.keepitclean.main.roadnetwork.junction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import java.util.Random;

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
	
	List<Point> junctionPoints;
	
	private Map<String, List<LaneSection>> mapOfInputRoadsToOutputRoads;
	
	private LaneFactory lf;
	
	private final String junctionLaneType = "SINGLELANE";
	
	public PrePlannedRouteJunction(List<Road> roadsEnteringThisJunction, List<Road> roadsLeavingThisJunction) {
		this.roadsEnteringJunction = roadsEnteringThisJunction;
		this.roadsLeavingJunction = roadsLeavingThisJunction;
		
		generateSectionsOfJunction();
		createMappings();
		
		this.junctionPoints = new ArrayList<>();
	}
	
	private void createMappings() {
		mapOfInputRoadsToOutputRoads = new HashMap<>();
		Point inputRoadCoords;
		Point exitRoadCoords;
		for (Road road : roadsEnteringJunction) {
			inputRoadCoords = road.getJuctionEndCoordinates();
			for (Road exitRoad : roadsLeavingJunction) {
				exitRoadCoords = exitRoad.getJuctionStartCoordinates();
				mapOfInputRoadsToOutputRoads.put("" + coordinateStringFormatter(inputRoadCoords, exitRoadCoords), produceRoute(inputRoadCoords, exitRoadCoords));
			}
		}
		
	}
	
	private String coordinateStringFormatter(Point inputRoadCoords, Point exitRoadCoords) {
		return "" + inputRoadCoords.getX() + "," + inputRoadCoords.getY() + "-" + exitRoadCoords.getX() + "," + exitRoadCoords.getY();
	}

	private void generateSectionsOfJunction() {
		int widthOfJunction;
		if (roadsEnteringJunction.get(0).getNumberOfLanes() == roadsLeavingJunction.get(0).getNumberOfLanes()) {
			widthOfJunction = roadsEnteringJunction.get(0).getNumberOfLanes();
			buildJunctionSections(widthOfJunction);
		}
		if (roadsEnteringJunction.size() >= 2 && roadsLeavingJunction.size() >= 2) {
			widthOfJunction = roadsEnteringJunction.get(0).getNumberOfLanes() * 2;
			for (Road r : roadsEnteringJunction) {
				if (r.getNumberOfLanes() * 2 > widthOfJunction)
					widthOfJunction = r.getNumberOfLanes() * 2;
			}
			buildJunctionSections(widthOfJunction * widthOfJunction);
		}
		else {
			if (roadsEnteringJunction.get(0).getNumberOfLanes() < roadsLeavingJunction.get(0).getNumberOfLanes()) {
				widthOfJunction = roadsLeavingJunction.get(0).getNumberOfLanes();
				buildJunctionSections(widthOfJunction);
			}
			else {
				widthOfJunction = roadsEnteringJunction.get(0).getNumberOfLanes();
				buildJunctionSections(widthOfJunction);
			}
		}
	}
	
	private void buildJunctionSections(int widthOfJunction) {
		lf = new LaneFactory();
		this.sectionsOfJunction = new ArrayList<LaneSection>();
		
		for (int index = 0; index < widthOfJunction; index++) {
			sectionsOfJunction.add(lf.produceLaneSection(junctionLaneType));
		}
		lf = null;
	}

	@Override
	public List<LaneSection> produceRoute(Point roadEnteringCoord, Point roadLeavingCoord) {
		List<LaneSection> route = new ArrayList<LaneSection>();
		
		int roadEnteringIndex = 0;
		int roadLeavingIndex = 0;
		
		for (int x = 0; x < roadsEnteringJunction.size(); x++) {
			if (roadsEnteringJunction.get(x).getJuctionEndCoordinates().equals(roadEnteringCoord)) {
				roadEnteringIndex = x;
			}
		}
		for (int x = 0; x < roadsLeavingJunction.size(); x++) {
			if (roadsLeavingJunction.get(x).getJuctionStartCoordinates().equals(roadLeavingCoord)) {
				roadLeavingIndex = x;
			}
		}
		
		if (sectionsOfJunction.size() > 1) {
			if(roadEnteringIndex == 0) {
				if (roadLeavingIndex == 0) {
					getLaneSectionsOfJunction().get(0).setJunctionGridIndex(0);
					route.add(getLaneSectionsOfJunction().get(0));
					
					getLaneSectionsOfJunction().get(0).setJunctionGridIndex(1);
					route.add(getLaneSectionsOfJunction().get(1));
					
					return route;
				}
				if (roadLeavingIndex == 1) {
					route.add(getLaneSectionsOfJunction().get(0));
					return route;
				}
				if (roadLeavingIndex == 2) {
					route.add(getLaneSectionsOfJunction().get(0));
					route.add(getLaneSectionsOfJunction().get(2));
					return route;
				}
				if (roadLeavingIndex == 3) {
					route.add(getLaneSectionsOfJunction().get(0));
					route.add(getLaneSectionsOfJunction().get(3));
					return route;
				}
			}
			if(roadEnteringIndex == 1) {
				if (roadLeavingIndex == 1) {
					route.add(getLaneSectionsOfJunction().get(2));
					route.add(getLaneSectionsOfJunction().get(0));
					return route;
				}
				if (roadLeavingIndex == 2) {
					route.add(getLaneSectionsOfJunction().get(2));
					return route;
				}
				if (roadLeavingIndex == 3) {
					route.add(getLaneSectionsOfJunction().get(2));
					route.add(getLaneSectionsOfJunction().get(3));
					return route;
				}
				if (roadLeavingIndex == 0) {
					route.add(getLaneSectionsOfJunction().get(2));
					route.add(getLaneSectionsOfJunction().get(1));
					return route;
				}
			}
			if(roadEnteringIndex == 2) {
				if (roadLeavingIndex == 2) {
					route.add(getLaneSectionsOfJunction().get(3));
					route.add(getLaneSectionsOfJunction().get(2));
					return route;
				}
				if (roadLeavingIndex == 3) {
					route.add(getLaneSectionsOfJunction().get(3));
					return route;
				}
				if (roadLeavingIndex == 0) {
					route.add(getLaneSectionsOfJunction().get(3));
					route.add(getLaneSectionsOfJunction().get(1));
					return route;
				}
				if (roadLeavingIndex == 1) {
					route.add(getLaneSectionsOfJunction().get(3));
					route.add(getLaneSectionsOfJunction().get(0));
					return route;
				}
			}
			if(roadEnteringIndex == 3) {
				if (roadLeavingIndex == 3) {
					route.add(getLaneSectionsOfJunction().get(1));
					route.add(getLaneSectionsOfJunction().get(3));
					return route;
				}
				if (roadLeavingIndex == 0) {
					route.add(getLaneSectionsOfJunction().get(1));
					return route;
				}
				if (roadLeavingIndex == 1) {
					route.add(getLaneSectionsOfJunction().get(1));
					route.add(getLaneSectionsOfJunction().get(0));
					return route;
				}
				if (roadLeavingIndex == 2) {
					route.add(getLaneSectionsOfJunction().get(1));
					route.add(getLaneSectionsOfJunction().get(2));
					return route;
				}
			}
		}
		else {
			route.add(getLaneSectionsOfJunction().get(0));
			return route;
		}
		
		
		return route;
	}
        /* ROSIE---- generate random junction exit [oint >>>START */
        @Override
        public Point getRandomExitPoint(){
            Random generator = new Random();
            int exitPointIndex;
            exitPointIndex = generator.nextInt(this.roadsLeavingJunction.size());
            return this.roadsLeavingJunction.get(exitPointIndex).getJuctionStartCoordinates();
            
        }
	/* ROSIE---- generate random junction exit [oint <<<<END */
        
	public Map<String, List<LaneSection>> getMappings() {
		return mapOfInputRoadsToOutputRoads;
	}
	
	public List<LaneSection> getLaneSectionsOfJunction() {
		return sectionsOfJunction;
	}

	@Override
	public List<Point> getCoordinates() {
		for (Road road : roadsEnteringJunction) {
			junctionPoints.add(road.getJuctionEndCoordinates());
		}
                for (Road road:roadsLeavingJunction){
                    if (!junctionPoints.contains(road.getJuctionStartCoordinates()))
                        junctionPoints.add(road.getJuctionStartCoordinates());
                }
		return junctionPoints;
	}
}