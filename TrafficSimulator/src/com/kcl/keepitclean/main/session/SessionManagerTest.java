package com.kcl.keepitclean.main.session;
/**
 * @author dmendoza
 */
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class SessionManagerTest implements Observer, ActionListener{
	JFrame frame;
	JButton buttonPlus, buttonMinus, buttonPause, buttonResume, buttonPlay, buttonStop;
	JTextArea textArea;
	
	public static void main(String[] args) {
		/*SessionManager sm = SessionManager.getInstance();
		
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
		System.exit(0);*/
		SessionManagerTest smt = new SessionManagerTest();
		smt.initUI();
		smt.setup(SessionManager.getInstance());
	}
	
	private void setup(SessionManager sm) {
		sm.addObserver(this);
	}
	
	private void blink(){
		if(textArea.getBackground().equals(Color.BLACK)){
			textArea.setBackground(Color.WHITE);
			
		} else {
			textArea.setBackground(Color.BLACK);	
		}
	}
	
	
	private void initUI(){
		buttonPlay = new JButton("Play");
		buttonPlay.setName("py");
		buttonPlay.addActionListener(this);
		
		buttonPause = new JButton("Pause");
		buttonPause.setName("ps");
		buttonPause.addActionListener(this);
		
		buttonResume = new JButton("Resume");
		buttonResume.setName("rs");
		buttonResume.addActionListener(this);
		
		buttonMinus = new JButton("Minus");
		buttonMinus.setName("m");
		buttonMinus.addActionListener(this);
		
		buttonPlus = new JButton("Plus");
		buttonPlus.setName("pl");
		buttonPlus.addActionListener(this);
		
		buttonStop = new JButton("Stop");
		buttonStop.setName("s");
		buttonStop.addActionListener(this);
		
		frame = new JFrame("Test");
		frame.setLayout(new GridBagLayout());
		frame.add(buttonPlay);
		frame.add(buttonPause);
		frame.add(buttonResume);
		frame.add(buttonMinus);
		frame.add(buttonPlus);
		frame.add(buttonStop);
		
		textArea = new JTextArea();
		textArea.setText("Blink");
		
		frame.add(textArea);
		
		frame.setSize(600, 80);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		blink();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			if(((JButton)e.getSource()).getName().equals("py")){
				SessionManager.getInstance().startSession();
			}
			if(((JButton)e.getSource()).getName().equals("ps")){
				SessionManager.getInstance().pauseSession();
			}
			if(((JButton)e.getSource()).getName().equals("rs")){
				SessionManager.getInstance().resumeSession();
			}
			if(((JButton)e.getSource()).getName().equals("m")){
				SessionManager.getInstance().doStepSlower();
			}
			if(((JButton)e.getSource()).getName().equals("pl")){
				SessionManager.getInstance().doStepFaster();	
			}
			if(((JButton)e.getSource()).getName().equals("s")){
				SessionManager.getInstance().stopSession();
			}
		}

	}
}
