package com.kcl.keepitclean.main.simulatorengine;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.kcl.keepitclean.main.policy.Policy;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;
import com.kcl.keepitclean.main.session.SessionManager;
import com.kcl.keepitclean.main.vehicle.Vehicle;
import com.kcl.keepitclean.main.vehicle.VehicleFactory;
import com.kcl.keepitclean.main.vehicle.VehicleType;

public class SimulatorEngine implements Observer{
	
	private Object simulatorGUI;
	
	private VehicleFactory vehicleFactory;
	private List<Vehicle> vehicleList;
	
	private LaneFactory laneFactory;
	
	private RoadFactory roadFactory;
	private List<Road> roadList;
	
	public SimulatorEngine(Object simulatorGUI) {
		
		this.simulatorGUI = simulatorGUI;
		
		vehicleFactory = new VehicleFactory();
		laneFactory =  new LaneFactory();
		roadFactory =  new RoadFactory(laneFactory);
	}
	
	public void init(){
		
		SessionManager.getInstance().addObserver(this);
		
		roadList.add(roadFactory.produceRoad("", 5, 5));
		Policy.getPolicyInstance();
		vehicleList.add(vehicleFactory.getVehicle(VehicleType.CAR));
	}
	
	public void startSimulation(){
		SessionManager.getInstance().addObserver(this);//REMOVE ME. Duplicated line. Added just for testing.
		SessionManager.getInstance().startSession();
	}

//	private updateSimulationStatus(){
//		
//	}
	
	int i = 0;
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Step " + ++i);
	}
	
	
}
