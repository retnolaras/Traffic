package com.kcl.keepitclean.session;

import java.util.Observable;
import java.util.Observer;

public class SessionManager extends Observable implements Observer{

	private Session sessionRunnable = null;
	public Thread sessionThread = null;
	
	public SessionManager() {
		
		sessionRunnable = new Session();
		sessionRunnable.addObserver(this);
		
		sessionThread = new Thread(sessionRunnable);
	}
	
	public void startSession() throws IllegalStateException{
		System.currentTimeMillis();
		sessionThread.start();
	}
	
	public void pauseSession(){
		sessionRunnable.pauseSession();
	}
	
	public void resumeSession(){
		sessionRunnable.resumeSession();
	}
	
	public void stopSession(){
		sessionRunnable.pauseSession();
		sessionThread.interrupt();
		System.currentTimeMillis();
	}
	
	public void doStepFaster(){
		sessionRunnable.faster();
	}
	
	public void doStepSlower(){
		sessionRunnable.slower();
	}
	
	@Override
	public void notifyObservers() {
		super.notifyObservers();
		System.out.println("DO UI UPDATE");
	}

	@Override
	public void update(Observable o, Object arg) {
		notifyObservers();
	}
	
	public static void main(String[] args) {
		SessionManager sm = new SessionManager();
		sm.startSession();
		
		sm.doStepFaster();
		sm.doStepFaster();
		sm.doStepFaster();
		sm.doStepFaster();
		sm.doStepFaster();
		sm.doStepFaster();
		sm.doStepFaster();
		sm.doStepFaster();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sm.pauseSession();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sm.resumeSession();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		sm.doStepSlower();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sm.stopSession();
		System.exit(0);
	}
}

