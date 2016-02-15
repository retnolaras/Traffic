package com.kcl.keepitclean.main.roadnetwork;

public class LeftLane extends AbstractLaneSection {
	
	@Override
	public boolean isOpenForward() {
		return getOpen();
	}

	@Override
	public boolean isOpenLeft() {
		return getClosed();
	}

	@Override
	public boolean isOpenRight() {
		return getOpen();
	}

	@Override
	public boolean isOpeanBackward() {
		return getClosed();
	}
}
