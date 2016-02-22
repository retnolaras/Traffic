package com.kcl.keepitclean.test.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Test;

import com.kcl.keepitclean.main.session.SessionManager;

public class SessionManagerTest {
	private SessionManager testINSTANCE;
	
	public SessionManagerTest() {
	}
	
	@Test
	public void testSingleton() {
		testINSTANCE = SessionManager.getInstance();
		assertEquals(testINSTANCE.hashCode(), SessionManager.getInstance().hashCode());
	}
	
	@Test
	public void testSessionThread(){
		SessionManager.getInstance().startSession();
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		for (Thread thread : threadSet) {
			if(thread.getName().equalsIgnoreCase("SessionThread")) {
				assert(true);
				SessionManager.getInstance().stopSession();
				return;		
			}
		}
		SessionManager.getInstance().stopSession();
		fail();
	}
}

