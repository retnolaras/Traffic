package com.kcl.keepitclean.main.vehicle;

import java.util.Random;

import javafx.scene.paint.Color;

public class Car extends Vehicle{

	int size[] = {1,3};
	
	public Car() {
		UCarID = CarID++;
		
		setColor(Color.BLUE);
		
		int randNum = new Random().nextInt(3);
		switch (randNum) {
		case 0:
			setBehaviour(Behaviour.CAUTIOUS);
			break;
		case 1:
			setBehaviour(Behaviour.NORMAL);
			break;
		case 2:
			setBehaviour(Behaviour.RECKLESS);
			break;
		default:
			setBehaviour(Behaviour.NORMAL);
			break;
		}
	}
	
}
