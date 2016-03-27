package com.kcl.keepitclean.main.vehicle;

public class Bus extends Vehicle{
	String colour = "Blue";
	int size[] = {1,5};
	
	public Bus(){
		super();
		setBehaviour(Behaviour.NORMAL);
	}
}