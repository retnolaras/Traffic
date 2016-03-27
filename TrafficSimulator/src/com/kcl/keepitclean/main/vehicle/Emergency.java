package com.kcl.keepitclean.main.vehicle;

import javafx.scene.paint.Color;

public class Emergency extends Vehicle{
	int size[] = {1,3};
	
	public Emergency(){
		super();
		
		setColor(Color.WHITE);
		
		setBehaviour(Behaviour.RECKLESS);
	}
	
}