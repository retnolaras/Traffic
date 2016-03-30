package com.kcl.keepitclean.main.vehicle;

import java.awt.Point;
import java.util.List;

import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.Road;

import javafx.scene.paint.Color;

public class Vehicle {
	static int CarID = 0;
	private int speed;
	private Behaviour behaviour;

	Point axom;
	Point distance;
	Point destination;
	int acceleration;
	int size[];
	Position pos;
	int UCarID;
	boolean onJunction = false;
	List<LaneSection> path;
	private Color color;
	Road roadinfo;
	int pindex;
	int nextRoad;

	// car absolute position
	public Point getAxom() {
		return axom;
	}

	public void setAxom(Point axom) {
		this.axom = axom;
	}

	// car relative position
	public void setPos(Position p) {
		pos = p;
	}

	public Position getPos() {
		return pos;
	}

	public int getID() {
		return UCarID;
	}

	public void setOnJunction(boolean b) {
		if (b)
			onJunction = true;
		else
			onJunction = false;
	}

	public boolean getOnJunction() {
		if (onJunction)
			return true;
		else
			return false;
	}

	public void updatePath(List<LaneSection> p) {
		path = p;
	}

	public void removePath() {
		path.clear();
		onJunction = false;
		pindex = 0;
		nextRoad = -1;
	}

	public int getSpeed() {
		return speed;
	}

	private void setSpeed(int speed) {
		this.speed = speed;
	}

	public void IncrementPIndex() {
		pindex++;
	}

	public void resetPIndex() {
		pindex = 0;
	}

	public int getPIndex() {
		return pindex;
	}

	public List<LaneSection> getPath() {
		return path;
	}

	public void setNextRoadIndex(int nextRoadIndex) {

		nextRoad = nextRoadIndex;
	}

	public int getNextRoadIndex() {
		return nextRoad;

	}

	public Behaviour getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
		switch (this.behaviour) {
		case CAUTIOUS:
			setSpeed(1);
			break;
		case NORMAL:
			setSpeed(2);
			break;
		case RECKLESS:
			setSpeed(3);
			break;
		default:
			setSpeed(1);
			break;
		}
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
