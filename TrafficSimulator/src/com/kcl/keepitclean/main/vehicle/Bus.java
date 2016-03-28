package com.kcl.keepitclean.main.vehicle;

import java.util.Random;

import javafx.scene.paint.Color;

public class Bus extends Vehicle{
	int size[] = {1,5};
	
	public Bus(){
		super();
		setColor(Color.RED);
		
		int randNum = new Random().nextInt(4);
		switch (randNum) {
		case 0|1:
			setBehaviour(Behaviour.NORMAL);
			break;
		default:
			setBehaviour(Behaviour.CAUTIOUS);
			break;
		}
	}
}