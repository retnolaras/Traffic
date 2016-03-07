

// Load Map 
// Update UI 
// Generate Cars
// Maintain AcvtiveCarList 
		//create ActiveCar Class
// Move Car

package com.kcl.keepitclean.main.simulatorengine;

import com.kcl.keepitclean.main.GUI.IRenderer;
import com.kcl.keepitclean.main.GUI.SimulationSettings;
import java.util.ArrayList;
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
	
	//private Object simulatorGUI; // instance of the GUY
	static float freq = 0.10f ;
	private Random r;
	Position startingPos;
	private VehicleFactory vehicleFactory;
	private List<Vehicle> vehicleList;
	
	
	private LaneFactory laneFactory;
	
	private RoadFactory roadFactory;
	private List<Road> roadList;
	private Context context;
	private Road masterRoad;
        private IRenderer renderer;
        private SimulationSettings settings;
	
	public SimulatorEngine(SimulationSettings settings) {
		
	//	this.simulatorGUI = simulatorGUI;
                this.settings = settings;
		roadList = new ArrayList<>();
		vehicleList = new ArrayList<>();
		startingPos= new Position();
		r= new Random();
		context = new Context(roadList); 

		vehicleFactory = new VehicleFactory();
		laneFactory =  new LaneFactory();
		roadFactory =  new RoadFactory(laneFactory);
	}
	
	public void init(){
		
		SessionManager.getInstance().addObserver(this);
		
		
		
	//	roadList.add(roadFactory.produceRoad("", 5, 5));
	//	Policy.getPolicyInstance();
	//	vehicleList.add(vehicleFactory.getVehicle(VehicleType.CAR));
		
		startingPos.update(0,0,0);
		//lood policy variables: add them into RoadList 
		generateRoad();
		roadList = context.getRoadList();
		System.out.println("Got Road List"); //test line

		
		
	}
	
	private void generateRoad() {
		masterRoad =roadFactory.produceRoad("listoflistsroadimpl", 50, 1);
		context.addRoad(masterRoad);
		
	}

	public void startSimulation(){
		
		SessionManager.getInstance().startSession();
		
		init();
		if (renderer != null) {
                    renderer.render();
                }
		System.out.println("Session Started"); //test line

	}

//	private updateSimulationStatus(){
//		
//	}
	
	int iteration = 0;
	
	@SuppressWarnings("null")
	@Override
	public void update(Observable o, Object arg) {
	
		
		// Generate Car 
		if (iteration==0){
			Vehicle Car;
			Car = vehicleFactory.getVehicle(VehicleType.CAR); //generate a car 
			AddToActive( Car, startingPos); //add to Active List of cars
			
			System.out.println("First Car Generated"); //test line

		}	
		else if (NotEmpty(startingPos)){
			generateCar(startingPos); //generates car at starting point with factor  'freq'
			System.out.println("Car Generated"); //test line
		}
		
		// iterate on all cars, move car only if LookAhead is true
		for ( int i = 0 ; i< vehicleList.size(); i++){
			if (lookAhead(vehicleList.get(i).getPos(), 5)){
				
				Position  newPos = new Position();
				newPos.update(i, vehicleList.get(i).getPos().getLane(), vehicleList.get(i).getPos().getLaneSection()+1) ;
				context.moveVehicle(vehicleList.get(i), vehicleList.get(i).getPos(), newPos);
				System.out.println("Car Moved"); //test line

				}
			}
		iteration++;
		}



	/*
	 * Check if a position is empty  
	 */

private boolean NotEmpty(Position startingPos2) {
	Road r= roadList.get(startingPos2.getRoad()); //returns a road 
	// (()r).
	List<LaneSection> lanes= ((ListOfListsRoadImpl)r).getLaneSectionsOfRoad().get(startingPos2.getLane());
	if (lanes.get(startingPos2.getLaneSection()).hasVehicleOnSeciton())
		return true;
	
	return false;
}
	

/*
* check the five positions ahead of the car
*/

		 private boolean lookAhead (Position p , int a){
		   int LaneIndex= p.getLane();
		   int LaneSection= p.getLaneSection();
		   int Road = p.getRoad();
		   Road R;
		   R = roadList.get(Road);
		   Position Pos=p;
                   List<LaneSection> Lane =((ListOfListsRoadImpl)R).getLaneSectionsOfRoad().get(LaneIndex);
		   
		    for ( int x= LaneSection; x< LaneSection+a && x<Lane.size(); x++){
		         
		      Pos.update(Road, LaneIndex, LaneSection+1);
		      if (NotEmpty(Pos)) return false;
		      x++;
		      }		   
		return true;		 
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
	 Car.setPos(p);
	AddToActive(Car, p);
	}

}

public IRenderer getRenderer() {
  return renderer;
}
public void setRenderer(IRenderer renderer) {
 this.renderer = renderer;
}


}

