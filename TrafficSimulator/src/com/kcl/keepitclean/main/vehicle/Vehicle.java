package com.kcl.keepitclean.main.vehicle;

import com.kcl.keepitclean.main.roadnetwork.road.Road;
import java.awt.Point;
import java.util.Collections;
import java.util.List;


public class Vehicle {
	static int CarID = 0;
	private int speed;
	Point axom;
	Point distance;
	Point destination;
	int acceleration;
	int size[];
	Position pos;
	int UCarID;
	boolean onJunction = false;
	List<Position> path;
	String colour;
	Road roadinfo;	
	
	//MOVE ACTION --> move this method to engine
	/*public Point move(Point posi, int speed, int acceleration, Direction direction){
		Point newposi;
		newposi = addPoint(posi,speed,acceleration,direction);
		return newposi;
		
	}
	
	private Point addPoint(Point posi, float speed2, float acceleration2, Direction direction) {
		Point added = new Point (posi.getAxisX(), posi.getAxisY()); ;
		if(direction == Direction.FORWARD){
			float addedX = posi.getAxisX();
				addedX = addedX + (speed2/10) + (acceleration2/10);
			    added = new Point (addedX, posi.getAxisY()); 
		 }
		else if (direction == Direction.RIGHT){
			 float addedY = posi.getAxisY();
				addedY = addedY + (speed2/10) + (acceleration2/10);
			    added = new Point (posi.getAxisX(), addedY); 
		 }
		else if (direction == Direction.LEFT){
			float addedY = posi.getAxisY();
				addedY = addedY - (speed2/10) - (acceleration2/10);
			    added = new Point (posi.getAxisX(), addedY); 
		 }
		
		return added;
	}*/

	//car absolute position
	public Point getAxom() {
		return axom;
	}

	public void setAxom(Point axom) {
		this.axom = axom;
	}
	
	//car relative position
	public void setPos(Position p){
		 pos = p;
	}
	public Position getPos(){
		return pos;
	}
	public int getID() {
		return UCarID;
	}
	public void setOnJunction(boolean b){
		if (b) onJunction=true;
		else onJunction=false;
	}
	public boolean getOnJunction(){
		if (onJunction) return true;
		else return false;
	}
	public void updatePath(List<Position> p){
		Collections.copy(path,p);
		
	}
	
	public void removePath(){
		path.clear();
		onJunction= false;
	}

	public int getSpeed() {
		return speed;
	}

	void setSpeed(int speed) {
		this.speed = speed;
	}
}

//END MOVE ACTION

//ACCELERATION

//END ACCELERATION

//DRIVER BEHAVIOUR

//END DRIVER BEHAVIOUR

//METRIC

//END METRIC



