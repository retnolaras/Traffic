

// Load Map 
// Update UI 
// Generate Cars
// Maintain AcvtiveCarList 
		//create ActiveCar Class
// Move Car

package com.kcl.keepitclean.main.simulatorengine;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.kcl.keepitclean.main.policy.Policy;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;
import com.kcl.keepitclean.main.session.SessionManager;
import com.kcl.keepitclean.main.vehicle.Position;
import com.kcl.keepitclean.main.vehicle.Vehicle;
import com.kcl.keepitclean.main.vehicle.VehicleFactory;
import com.kcl.keepitclean.main.vehicle.VehicleType;

public class SimulatorEngine implements Observer{
	
	private Object simulatorGUI; // instance of the GUY
	static float freq = 0.10f ;
	private Random r;
	Position StartingPos;
	private VehicleFactory vehicleFactory;
	private List<Vehicle> vehicleList;
	
	private LaneFactory laneFactory;
	
	private RoadFactory roadFactory;
	private List<Road> roadList;
	private Context context;
	
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
		
		//StartingPos.update(0,0,0);
		//lood policy variables: add them into RoadList 
		roadList = context.getRoadList();
		
		
		
	}
	
	public void startSimulation(){
		SessionManager.getInstance().startSession();
	}

//	private updateSimulationStatus(){
//		
//	}
	
	int iteration = 0;
	
	public void update(Observable o, Object arg) {
	
		
		// Generate Car 
		if (iteration==0){
			Vehicle Car;
			Car = vehicleFactory.getVehicle(VehicleType.CAR);
			AddToActive( Car, StartingPos);
		}
			else if (NotEmpty(StartingPos)){
				generateCar(StartingPos); //generates car at starting point with factor  'freq'
			}
		
		
		for ( int i ; i< vehicleList.size(); i++){
			if (vehicleList.ge lookAhead(vehicleList.get(i).getPos(), 5));
			else {
				
				Position  newPos;
				newPos.update(i, vehicleList.get(i).getPos().getLane(), vehicleList.get(i).getPos().getLaneSection()+1) ;
				context.moveVehicle(vehicleList.get(i), vehicleList.get(i).getPos(), newPos);
			}
		}
		
		

}



	/*
	 * Check if a position is empty  
	 */

private boolean NotEmpty(Position startingPos2) {
	Road r= roadList.get(startingPos2.getRoad()); //returns a road 
	// (()r).
	List<LaneSection> lanes= ((ListOfListsRoadImpl)r).getLaneSectionsOfRoad().get(startingPos2.getLane());
	if (lanes.get(startingPos2.getLaneSection()).hasVehicleOnSeciton())
		return false;
	
	return true;
}
	}

/*
* check the five positions ahead of the car
*/

 private bool LookAhead (Position p , int a){
   int lane= p.getLane();
   int LaneSection= P.getLaneSection();
   int Road = p.getRoad();
   Road R;
   R=RoadList.get(Road);
   Position Pos=p;
   
    for ( int x= LaneSection; x< LaneSection+5 || x<R.size(); x++){
         
      Pos.update(Road, Lane, LaneSection+1);
      if (NotEmpty(Pos)) return false;

      x++;


      }
   

   


Return true;
 
}

/*
 * Adds a car and its position to the Active Cars List 
 */

	private void AddToActive(Vehicle car, Position Pos) {
		car.setPos(Pos);
		vehicleList.add(car);
		
	}
		
	

	//	System.out.println("Step " + ++iteration);

	

//generates a car at  a certain lane given certain probability 'freq'

private void generateCar(Position p){
float chance = r.nextFloat();
 if (chance <= freq) {
	 Vehicle Car = vehicleFactory.getVehicle(VehicleType.CAR);
	AddToActive( Car, StartingPos);
	}
	
}

