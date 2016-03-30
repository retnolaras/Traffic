package com.kcl.keepitclean.main.session;

/**
 * @author dmendoza
 */
import java.util.Observable;
import java.util.Observer;

public class SessionManager extends Observable implements Observer {

	// Handling singleton pattern with Java class loader
	private static class SessionManagerHolder {
		static SessionManager instance = new SessionManager();
	}

	private Session session = null;

	public Thread sessionThread = null;

	/**
	 * Method to obtain the <code>SessionManager</code> instance.
	 * 
	 * @return The <code>SessionManager</code> instance.
	 */
	public static SessionManager getInstance() {
		// Synchronization not needed. A single instance is assured by
		// using the Java class loader.
		return SessionManagerHolder.instance;
	}

	private SessionManager() {
		// Do nothing
	}

	private long startTime;
	private long pauseTime;
	private long resumeTime;
	private long stopTime;

	public long startSession() throws IllegalStateException {
		synchronized (this) {
			if (session == null) {
				session = new Session();
				session.addObserver(this);
				sessionThread = new Thread(session, "SessionThread");
				sessionThread.start();
			} else {
				System.out.println("Session already running!");
			}
			startTime = System.nanoTime();
		}
		return startTime;
	}

	public long pauseSession() {
		synchronized (this) {
			if (session != null) {
				session.pauseSession();
			}
			pauseTime = System.nanoTime();
		}
		return pauseTime;
	}

	public long resumeSession() {
		synchronized (this) {
			if (session != null) {
				session.resumeSession();
			}
			resumeTime = System.nanoTime();
		}
		return resumeTime;
	}

	public long stopSession() {
		deleteObservers();
		synchronized (this) {
			if (session != null) {
				session.pauseSession();
				session = null;
			}
			sessionThread.interrupt();
			stopTime = System.nanoTime();
		}
		return stopTime;
	}

	public void doStepFaster() {
		if (session != null) {
			session.faster();
		}
	}

	public void doStepSlower() {
		if (session != null) {
			session.slower();
		}
	}

	@Override
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}

	@Override
	public void update(Observable o, Object arg) {
		notifyObservers();
	}

}
