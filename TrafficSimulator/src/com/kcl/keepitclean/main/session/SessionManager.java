package com.kcl.keepitclean.main.session;
/**
 * @author dmendoza
 */
import java.util.Observable;
import java.util.Observer;

public class SessionManager extends Observable implements Observer{
	
	// Handling singleton pattern with Java class loader
	private static class SessionManagerHolder {
	    static SessionManager instance = new SessionManager();
	}
	
	private Session session = null;
	
	public Thread sessionThread = null;
	
	/**
	 * Method to obtain the <code>SessionManager</code> instance.
	 * @return The <code>SessionManager</code> instance.
	 */
	public static SessionManager getInstance() { 
		// Synchronization not needed. A single instance is assured by
		// using the Java class loader.
	    return SessionManagerHolder.instance;
	}
	
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

