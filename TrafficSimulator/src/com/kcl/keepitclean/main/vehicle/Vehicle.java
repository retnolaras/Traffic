package com.kcl.keepitclean.main.vehicle;
import com.kcl.keepitclean.main.roadnetwork.road.Road;

public class Vehicle {

	int speed;
	Point axom;
	Point distance;
	Point destination;
	int acceleration;
	int size[];
	
	String colour;
	Road roadinfo;
	
	public enum Behaviour{
		RECKLESS, CAUTIOUS, NORMAL
	}
	
	public enum Direction{
		LEFT, RIGHT, FORWARD
	}
	
	
//MOVE ACTION
	public Point move(Point posi, int speed, int acceleration, Direction direction){
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
	}
	
}

//END MOVE ACTION

//ACCELERATION

//END ACCELERATION

//DRIVER BEHAVIOUR

//END DRIVER BEHAVIOUR

//METRIC

//END METRIC








