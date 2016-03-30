/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import java.awt.Point;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight.State;
import com.kcl.keepitclean.main.roadnetwork.road.Orientation;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.simulatorengine.SimulatorEngine;
import com.kcl.keepitclean.main.utils.Constant;
import com.kcl.keepitclean.main.vehicle.Bus;
import com.kcl.keepitclean.main.vehicle.Car;
import com.kcl.keepitclean.main.vehicle.Emergency;
import com.kcl.keepitclean.main.vehicle.Vehicle;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * draw simulation objects on GUI
 * 
 * @author rosiengo
 */
public class SimulationRender implements IRenderer {
	private GraphicsContext gc;
	private SimulatorEngine simulation;
	private List<Road> roads;
	private List<Vehicle> vehicles;
	private List<TrafficLight> trafficLights;
	private List<Junction> junctions;
	
	public SimulationRender(GraphicsContext gc, SimulatorEngine simulation) {
		this.gc = gc;
		this.simulation = simulation;
	}

	public void render() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				clear();
				roads = simulation.getContext().getRoadList();
				vehicles = simulation.getContext().getVehicleList();
				junctions = simulation.getContext().getJunctionList();
				trafficLights = simulation.getContext().getTrafficLightList();
				//

				drawRoads();
				drawJunctions();
				drawTrafficLights();

				drawVehicles();

			}
		});

	}

	/* Clear canvas before painting updated components */
	public void clear() {
		gc.clearRect(0, 0, 800, 600);
	}

	// DRAW ROADS
	public void drawRoads() {

		/*
		 * draw list of roads generated from simulation engine parameters: List
		 * of Roads, that is provided by simulation engine
		 */
		//		Commented code. Debug code.
		//		int j = 0;
		for (Road road : roads) {
			//			j++;
			//			System.out.println("Road " + j + " start: (" + road.getStartCoordinates().x + ","
			//					+ road.getStartCoordinates().y + ")");
			//			System.out.println(
			//					"Road " + j + " end: (" + road.getEndCoordinates().x + "," + road.getEndCoordinates().y + ")");

			// draw road
			gc.setFill(Color.DARKGRAY);

			gc.fillPolygon(
					new double[] { road.getStartCoordinates().x, road.getEndCoordinates().x, road.getEndCoordinates().x,
							road.getStartCoordinates().x },
					new double[] { road.getStartCoordinates().y, road.getStartCoordinates().y,
							road.getEndCoordinates().y, road.getEndCoordinates().y },
					4);
			if (road.getOrientation() == Orientation.HORIZONTAL || road.getOrientation() == Orientation.LEFT_HORIZONTAL
					|| road.getOrientation() == Orientation.RIGHT_HORIZONTAL) {
				gc.setStroke(Color.WHITE);
				gc.setLineWidth(1);
				gc.setLineDashes(new double[] { 10d, 0d });
				gc.strokeLine(road.getStartCoordinates().x, road.getEndCoordinates().y, road.getEndCoordinates().x,
						road.getEndCoordinates().y);

				gc.setStroke(Color.WHITE);
				gc.setLineWidth(1);
				gc.setLineDashes(new double[] { 10d, 0d });
				gc.strokeLine(road.getStartCoordinates().x, road.getStartCoordinates().y, road.getEndCoordinates().x,
						road.getStartCoordinates().y);
			}

			else {
				gc.setStroke(Color.WHITE);
				gc.setLineWidth(1);
				gc.setLineDashes(new double[] { 10d, 0d });
				gc.strokeLine(road.getStartCoordinates().x, road.getStartCoordinates().y, road.getStartCoordinates().x,
						road.getEndCoordinates().y);

				gc.setStroke(Color.WHITE);
				gc.setLineWidth(1);
				gc.setLineDashes(new double[] { 10d, 0d });
				gc.strokeLine(road.getEndCoordinates().x, road.getStartCoordinates().y, road.getEndCoordinates().x,
						road.getEndCoordinates().y);

			}

			// draw stroke lane line
			for (int i = 1; i <= road.getNumberOfLanes() - 1; i++) {
				gc.setStroke(Color.WHITE);
				gc.setLineWidth(1);
				gc.setLineDashes(new double[] { 25d, 10d });
				if (road.getOrientation() == Orientation.HORIZONTAL
						|| road.getOrientation() == Orientation.LEFT_HORIZONTAL
						|| road.getOrientation() == Orientation.RIGHT_HORIZONTAL) {
					gc.strokeLine(road.getStartCoordinates().x,
							road.getStartCoordinates().y + i * Constant.LANE_SIZE * Constant.PIXELS,
							road.getEndCoordinates().x,
							road.getStartCoordinates().y + i * Constant.LANE_SIZE * Constant.PIXELS);
				} else if (road.getOrientation() == Orientation.VERTICAL
						|| road.getOrientation() == Orientation.DOWN_VERTICAL
						|| road.getOrientation() == Orientation.UP_VERTICAL) {
					gc.strokeLine(road.getStartCoordinates().x + i * Constant.LANE_SIZE * Constant.PIXELS,
							road.getStartCoordinates().y,
							road.getStartCoordinates().x + i * Constant.LANE_SIZE * Constant.PIXELS,
							road.getEndCoordinates().y);
				}

			}

		}

	}

	// DRAW VEHICLES
	public void drawVehicles() {
		Orientation orientation;
		synchronized (vehicles) {

			for (Vehicle vehicle : vehicles) {

				if (Car.class.isInstance(vehicle))
					// Double angle =
					// vehicle.getDirectionVector().angleVectorDegree();
					// gc.getFill(BLUE);
					gc.setFill(Color.BLUE);
				if (Emergency.class.isInstance(vehicle))
					gc.setFill(Color.WHITE);
				if (Bus.class.isInstance(vehicle))
					gc.setFill(Color.RED);

				orientation = roads.get(vehicle.getPos().getRoad()).getOrientation();
				if (orientation == Orientation.HORIZONTAL || orientation == Orientation.LEFT_HORIZONTAL
						|| orientation == Orientation.RIGHT_HORIZONTAL)
					gc.fillRect(vehicle.getAxom().x, vehicle.getAxom().y,

							Constant.VEHICLE_LENGTH * Constant.PIXELS, Constant.VEHICLE_WIDTH * Constant.PIXELS);

				else
					gc.fillRect(vehicle.getAxom().x, vehicle.getAxom().y,

							Constant.VEHICLE_WIDTH * Constant.PIXELS, Constant.VEHICLE_LENGTH * Constant.PIXELS);

			}
		}

	}

	// DRAW JUNCTION
	public void drawJunctions() {
		Point center;

		for (Junction junction : junctions) {

			gc.setStroke(Color.YELLOW);
			gc.setFill(Color.DARKGREY);
			
			gc.fillPolygon(
					new double[] { junction.getCoordinates().get(0).x, junction.getCoordinates().get(1).x,
							junction.getCoordinates().get(2).x, junction.getCoordinates().get(3).x },
					new double[] { junction.getCoordinates().get(0).y, junction.getCoordinates().get(1).y,
							junction.getCoordinates().get(2).y, junction.getCoordinates().get(3).y },
					4);

			gc.setFill(Color.BLANCHEDALMOND);
			center = getCenterPoint(junction.getCoordinates());
			gc.fillOval(center.x - 6, center.y - 6, 12, 12);

		}
	}

	// DRAW TRAFFIC LIGHT
	public void drawTrafficLights() {

		for (TrafficLight trafficLight : trafficLights) {
			if (trafficLight.getState() == State.RED)
				gc.setFill(Color.RED);

			if (trafficLight.getState() == State.GREEN)
				gc.setFill(Color.GREEN);

			if (trafficLight.getState() == State.AMBER)
				gc.setFill(Color.YELLOW);

			gc.fillOval(trafficLight.getTrafficLightCoordinate().x - 4, trafficLight.getTrafficLightCoordinate().y - 4,
					8, 8);

		}

	}

	public Point getCenterPoint(List<Point> points) {
		Point center = new Point();
		int minx = points.get(0).x;
		int miny = points.get(0).y;

		for (int i = 0; i < 4; i++) {
			if (points.get(i).x < minx)
				minx = points.get(i).x;
			if (points.get(i).y < miny)
				miny = points.get(i).y;

		}

		center.x = minx + Constant.LANE_SIZE * Constant.PIXELS;
		center.y = miny + Constant.LANE_SIZE * Constant.PIXELS;

		return center;
	}

}
