package com.kcl.keepitclean.main.session;

import java.util.Observable;
import java.util.Observer;

public class SessionManager extends Observable implements Observer{
	
	private static class SessionManagerHolder {
	    static SessionManager instance = new SessionManager();
	}

	public static SessionManager getInstance() { // Note: "synchronized" not needed
	    return SessionManagerHolder.instance;
	}

	private Session session = null;
	public Thread sessionThread = null;
	
	private SessionManager() {
		//Do nothing
	}
	
	public void startSession() throws IllegalStateException{
		System.currentTimeMillis();
		if(session == null){
			session = new Session();
			session.addObserver(this);
			sessionThread = new Thread(session);
			sessionThread.start();
		} else {
			System.out.println("Session already running!");
		}
	}
	
	public void pauseSession(){
		if(session != null){
			session.pauseSession();
		}
	}
	
	public void resumeSession(){
		if(session != null){
			session.resumeSession();
		}
	}
	
	public void stopSession(){
		if(session != null){
			session.pauseSession();
			session = null;
		}
		sessionThread.interrupt();
		System.currentTimeMillis();
	}
	
	public void doStepFaster(){
		if(session != null){
			session.faster();
		}
	}
	
	public void doStepSlower(){
		if(session != null){
			session.slower();
		}
	}
	
	@Override
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
		//System.out.println("DO UI UPDATE");
	}

	@Override
	public void update(Observable o, Object arg) {
		notifyObservers();
	}
	
}

