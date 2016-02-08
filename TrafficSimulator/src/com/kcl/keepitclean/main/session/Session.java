package com.kcl.keepitclean.main.session;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

class Session extends Observable implements Runnable {
	
	private final long STEP_SIZE = 125_000_000L;
	
	private final long DEFAULT_STEP_SIZE = STEP_SIZE * 8;
	private final long MAX_STEP_SIZE = STEP_SIZE * 24;
			
	private AtomicBoolean isOn;
	private long stepSize;
	
	public Session() {
		isOn = new AtomicBoolean(true);
		stepSize = DEFAULT_STEP_SIZE;
	}
	
	public void pauseSession(){
		isOn.set(false);
	}
	
	public void resumeSession(){
		isOn.set(true);
	}
	
	public void faster(){
		synchronized(this) {
			if(stepSize > STEP_SIZE){
				stepSize -= STEP_SIZE;
			}
		}
	}
	
	public void slower(){
		synchronized(this) {
			if(stepSize < MAX_STEP_SIZE){
				stepSize += STEP_SIZE;
			}
		}
	}	
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			long startTime = System.nanoTime();
			long stepTime = startTime + stepSize;
			while(isOn.compareAndSet(true, true)){
				/*try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
				startTime = System.nanoTime();
				if(startTime >= stepTime){
					setChanged();
					notifyObservers();
					stepTime = startTime + stepSize;
				}
			}
		}
	}

}
