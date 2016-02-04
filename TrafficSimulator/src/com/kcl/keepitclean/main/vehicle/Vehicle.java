package com.kcl.keepitclean.main.vehicle;
import com.kcl.keepitclean.main.roadnetwork.Road;

public abstract class Vehicle {

	int speed;
	Point axom;
	Point distance;
	Point destination;
	int acceleration;
	int size[];
	int behaviour;
	String colour;
	Road roadinfo;
	
	
	
//MOVE ACTION
	public Point moveForward(Point posi, int speed, int acceleration){
		Point newposi;
		newposi = addPoint(posi,speed,acceleration,1);
		return newposi;
		
	}
	
	public Point moveRight(Point posi, float speed, float acceleration){
		Point newposi;
		newposi = addPoint(posi,speed,acceleration,2);
		return newposi;
		
	}
	
	public Point moveLeft(Point posi, int speed, int acceleration){
		Point newposi;
		newposi = addPoint(posi,speed,acceleration,3);
		return newposi;
		
	}

	private Point addPoint(Point posi, float speed2, float acceleration2, int type) {
		Point added = new Point (posi.getAxisX(), posi.getAxisY()); ;
		if(type==1){
			float addedX = posi.getAxisX();
				addedX = addedX + (speed2/10) + (acceleration2/10);
			    added = new Point (addedX, posi.getAxisY()); 
		 }
		else if (type==2){
			 float addedY = posi.getAxisY();
				addedY = addedY + (speed2/10) + (acceleration2/10);
			    added = new Point (posi.getAxisX(), addedY); 
		 }
		else if (type==3){
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




class Car extends Vehicle{
	String colour = "Green";
	int size[] = {1,3};
	
	
	void countDistance(float frontX, float frontY, float backX, float backY) {
	}


	public void move() {
		/*super.move();*/
	}
}

class Emergency extends Vehicle{
	String colour = "Red";
	int size[] = {1,3};
	
}

class Bus extends Vehicle{
	String colour = "Blue";
	int size[] = {1,5};
	
}

