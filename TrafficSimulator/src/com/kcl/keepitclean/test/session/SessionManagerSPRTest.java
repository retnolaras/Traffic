package com.kcl.keepitclean.test.session;

import static org.junit.Assert.assertTrue;

import java.util.Observable;
import java.util.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kcl.keepitclean.main.session.SessionManager;

public class SessionManagerSPRTest implements Observer{

	private int steps = 0;
	
	@Before
	public void setUp() {
		SessionManager.getInstance().addObserver(this);
		SessionManager.getInstance().startSession();
		for (int i = 0; i < 10; i++) {
			SessionManager.getInstance().doStepFaster();
		}
	}
	
	@After
	public void tearDown() {
		SessionManager.getInstance().stopSession();
		SessionManager.getInstance().deleteObservers();
		steps = 0;
	}
	
	@Test
	public void testStart(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("testStart " + steps);
		assertTrue(steps > 0);
	}

	@Test
	public void testPause(){
		int pstp;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SessionManager.getInstance().pauseSession();
		pstp = steps;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("testPause " + steps);
		assertTrue(pstp == steps);
	}
	
	@Test
	public void testResume(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SessionManager.getInstance().pauseSession();
		steps = 0;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SessionManager.getInstance().resumeSession();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("testResume " + steps);
		assertTrue(steps > 0);	
	}
	
	@Override
	public void update(Observable o, Object arg) {
		steps++;
	}
}
