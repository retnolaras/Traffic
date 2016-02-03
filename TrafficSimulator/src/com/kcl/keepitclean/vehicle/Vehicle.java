package com.kcl.keepitclean.vehicle;

public abstract class Vehicle {

	int speed;
	Coordinate axom;
	Coordinate distance;
	Coordinate destination;
	int acceleration;
	int size[];
	int behaviour;
	String colour;
	
	public Coordinate moveForward(Coordinate posi, int speed, int acceleration){
		Coordinate newposi;
		newposi = addCoordinate(posi,speed,acceleration,1);
		return newposi;
		
	}
	
	public Coordinate moveRight(Coordinate posi, float speed, float acceleration){
		Coordinate newposi;
		newposi = addCoordinate(posi,speed,acceleration,2);
		return newposi;
		
	}
	
	public Coordinate moveLeft(Coordinate posi, int speed, int acceleration){
		Coordinate newposi;
		newposi = addCoordinate(posi,speed,acceleration,3);
		return newposi;
		
	}

	private Coordinate addCoordinate(Coordinate posi, float speed2, float acceleration2, int type) {
		Coordinate added = new Coordinate (posi.getAxisX(), posi.getAxisY()); ;
		if(type==1){
			float addedX = posi.getAxisX();
				addedX = addedX + (speed2/10) + (acceleration2/10);
			    added = new Coordinate (addedX, posi.getAxisY()); 
		 }
		else if (type==2){
			 float addedY = posi.getAxisY();
				addedY = addedY + (speed2/10) + (acceleration2/10);
			    added = new Coordinate (posi.getAxisX(), addedY); 
		 }
		else if (type==3){
			float addedY = posi.getAxisY();
				addedY = addedY - (speed2/10) - (acceleration2/10);
			    added = new Coordinate (posi.getAxisX(), addedY); 
		 }
		
		return added;
	}
	
}


class Car extends Vehicle{
	String colour = "Green";
	int size[] = {1,5};
	
	
	void countDistance(float frontX, float frontY, float backX, float backY) {
	}


	public void move() {
		/*super.move();*/
	}
}

class Emergency extends Vehicle{
	String colour = "Red";
	int size[] = {1,5};
	
	
	
}

