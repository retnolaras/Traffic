package com.kcl.keepitclean.main.roadnetwork.trafficLight;


public class TrafficLightFactory {

	public TrafficLightSection produceTrafficLight(String laneSection) {
		if (laneSection == null) {
			return null;
		}
		else {
			return new TrafficLightSection();
		}
	}
}