package com.kcl.keepitclean.main.vehicle;
public class Car extends Vehicle{
	static int CarID = 0;
	String colour = "Green";
	int size[] = {1,3};
	int UCarID;
	Vehicle(){
		UCarID = CarID++;
	}
	void countDistance(float frontX, float frontY, float backX, float backY) {
	}

	public int getID {
		return UCarID;
	}
	public void move() {
		/*super.move();*/
	}
}
