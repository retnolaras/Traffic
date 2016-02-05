package com.kcl.keepitclean.main.vehicle;

public class Point {
	private float axisX;
	private float axisY;
	
	public Point(float x, float y){
		this.axisX =x;
		this.axisY =y;
	}

	float getAxisX() {
		return axisX;
	}

	void setAxisX(float axisX) {
		this.axisX = axisX;
	}

	float getAxisY() {
		return axisY;
	}

	void setAxisY(float axisY) {
		this.axisY = axisY;
	}
	
}