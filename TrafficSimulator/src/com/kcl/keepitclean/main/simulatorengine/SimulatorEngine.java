
//Checklist:
/*Check when car has reached the end of the road
 * 
 */

// Load Map 
// Update UI 
// Generate Cars
// Maintain AcvtiveCarList 
		//create ActiveCar Class
// Move Car

package com.kcl.keepitclean.main.simulatorengine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.kcl.keepitclean.main.GUI.IRenderer;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneFactory;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
import com.kcl.keepitclean.main.roadnetwork.road.RoadFactory;
import com.kcl.keepitclean.main.session.SessionManager;
import com.kcl.keepitclean.main.utils.Constant;
import com.kcl.keepitclean.main.vehicle.Position;
import com.kcl.keepitclean.main.vehicle.Vehicle;
import com.kcl.keepitclean.main.vehicle.VehicleFactory;
import com.kcl.keepitclean.main.vehicle.VehicleType;

public class SimulatorEngine implements Observer {

	// private Object simulatorGUI; // instance of the GUI
	static float freq = 0.5f;
	private Random r;
	private Position startingPos;
	private VehicleFactory vehicleFactory;
	private List<Vehicle> vehicleList;

	private Point startCoord = new Point(35, 0);
	private Point endCoord = new Point(0, 100);
	
	private Point vehicleStartCoord = new Point(35 + Constant.VEHICLE_LEFT_MARGIN, 0);
	
	private LaneFactory laneFactory;

	private RoadFactory roadFactory;
	private List<Road> roadList;
	private Context context;
	private Road masterRoad;
	private List<Position> startingPositions;

	private IRenderer renderer;

	public SimulatorEngine(Object simulatorGUI) {

		// this.simulatorGUI = simulatorGUI;
		roadList = new ArrayList<>();
		vehicleList = new ArrayList<>();
		startingPos = new Position();
    	startingPositions = new ArrayList<>();

		
		r = new Random();
		context = new Context(roadList, vehicleList);

		vehicleFactory = new VehicleFactory();
		laneFactory = new LaneFactory();
		roadFactory = new RoadFactory(laneFactory);
		
	}

	public void init() {
		// roadList.add(roadFactory.produceRoad("", 5, 5));
		// Policy.getPolicyInstance();
		// vehicleList.add(vehicleFactory.getVehicle(VehicleType.CAR));

		startingPos.update(0, 0, 0);
		// load policy variables: add them into RoadList
		//generateRoad();
                Map1 map = new Map1();
                roadList = map.getRoads();
                for (Road road:roadList)
                {
                    context.addRoad(road);
                }
                
		//roadList = context.getRoadList();
	
		roadList = context.getRoadList();
		System.out.println("<SimulatorEngine>Got Road List"); // test line
	}

	public IContext getContext() {
		return this.context;
	}

	public void setRenderer(IRenderer renderer) {
		this.renderer = renderer;
	}

	public IRenderer getRenderer() {
		return this.renderer;
	}

	private void generateRoad() {
		masterRoad = roadFactory.produceRoad("listoflistsroadimpl", 50, 1);

		((ListOfListsRoadImpl) masterRoad).setStartCoordinate(startCoord);
		((ListOfListsRoadImpl) masterRoad).setEndCoordinate(endCoord);

		context.addRoad(masterRoad);

	}

	public void startSimulation() {

		SessionManager.getInstance().startSession();
		init();
		SessionManager.getInstance().addObserver(this);
		renderer.render();
		System.out.println("<SimulatorEngine>Session Started"); // test line

	}
	
		public void stopSimulation(){
 		SessionManager.getInstance().stopSession();
 	}
 	

	// private updateSimulationStatus(){
	//
	// }

	int iteration = 0;

	@Override
	public void update(Observable o, Object arg) {

		// Generate Car, assign its position to be the starting Position.
		if (iteration == 0) {
			Vehicle Car;
			Car = vehicleFactory.getVehicle(VehicleType.CAR); // generate a car
			startingPos.update(0, 0, 0);
			Position carPos = new Position();
			carPos.update(startingPos.getRoad(),startingPos.getLane(),startingPos.getLaneSection());
			AddToActive(Car, carPos); // add to Active List of cars

			System.out.println("<SimulatorEngine>First Car Generated"); // test line
	
	/* //instead of the esle if
			else {
			for (int i=0; i<startingPositions.size(); i++){
					 if (!NotEmpty(startingPos))
					generateCar(startingPositions.get(i)); // generates car at starting point with
										// factor 'freq'
			System.out.println("<SimulatorEngine>Car Generated at starting point" + i+ " "); // test line
			}
			}
			
			*/
		} else if (!NotEmpty(startingPos)) {
			generateCar(startingPos); // generates car at starting point with
										// factor 'freq'
			System.out.println("<SimulatorEngine>Car Generated"); // test line
		}
		renderer.render();
		
		// iterate on all cars, move car only if LookAhead is true
		for (int i = 0; i < vehicleList.size(); i++) {

			System.out.println("<SimulatorEngine>iterating on car number " + i); // test // line
			
			//if car is in a junction, move as long as the next position is empty
			if (lookAhead(vehicleList.get(i).getPos(), 5) && (!reachedEnd(vehicleList.get(i)))) {

				Position newPos = new Position();
				newPos.update(0, vehicleList.get(i).getPos().getLane(),
						vehicleList.get(i).getPos().getLaneSection()+1); 
				
			
				moveWrapper ( vehicleList.get(i).getPos(), newPos, i);
			}
			//TODO: junctionNext and getNextJunction and carOnPath
			//TODO: in vehicle, add attricute, onJunction, a setter a getter.
			//TODO: in vehicle, add a list of positions for current route, also a function to clear positions. DONE
			//TODO: support for different maps.
			
			//if the car reached the end,and a junction is upcoming, check if theres a redlight. if there isnt, acquire path.
			/*
			else if ( reachedEnd(vehicleList.get(i)) && junctionNext() ){
			
			
			Junction junc = new Junction();
			junc=getNextJunction();  //TODO: translate path into a list of pos objects
			list<LaneSection> path=junc.getPath(); //returns a list  of lanesections relevant to the junction
			//TODO: pass to the Context positions, have it translate positions to cooridantes, and retrieve a list of positions.
			//save the path in the car
			//check if the  path is enabled,
			if it is, set the car on that path.
			carOnPath(vehicleList.get(i), path);
			//TODO: define movefunction for path.
			
			
			
			}
			
			
			*/
			
			//if car is at at the end of the road and no upcoming junction
			/*
			else if (reachedEnd(vehicleList.get(i)) && !junctionNext()){
			for (i=0; i<endPositions.size();i++){
			if (isEqual (vehicleList.get(i).getPos() , endPositions(i)) {
				vehicleList.remove(i);
				System.out.println("<SimulatorEngine>Car "+vehicleList.get(i).getID()+ " removed" );
				continue; 
			}
			}
			}
			
			*/
			else if((reachedEnd(vehicleList.get(i))) ){
				vehicleList.remove(i);
				System.out.println("<SimulatorEngine>Car "+vehicleList.get(i).getID()+ " removed" );
				continue; 
			}
		}
		iteration++;
	}
	
	
	private void moveWrapper(Position pos, Position newPos, int i) {
		//inform context about the change
		Point debugPoint=context.moveVehicle(vehicleList.get(i), vehicleList.get(i).getPos(), newPos);
		//inform the vehicle list
		vehicleList.get(i).getPos().update(newPos.getRoad(), newPos.getLane(), newPos.getLaneSection());;
		//output to terminal
		System.out.println("<SimulatorEngine> Car Moved [" + " ID:" + vehicleList.get(i).getID() + " "
				+ debugPoint.getX() + ", " + debugPoint.getY() + "]");
		//empty old section
		Road r1 = roadList.get(vehicleList.get(i).getPos().getRoad()) ;
		 LaneSection ls =((ListOfListsRoadImpl)r1).getLaneSectionsOfRoad().get(pos.getLane()).get(pos.getLaneSection());	
		 ls.removeVehicleFromSection();
		 
		 //fill new section
		 Road r2 = roadList.get(vehicleList.get(i).getPos().getRoad()) ;
		 LaneSection ls2 =((ListOfListsRoadImpl)r2).getLaneSectionsOfRoad().get(newPos.getLane()).get(newPos.getLaneSection());	
		 ls2.removeVehicleFromSection();
		 
	}		
	/*
	 * Check if a car has reached an exit point, in this case the end of the road
	 * TODO: define exit points  
	 */

private boolean reachedEnd(Vehicle vehicle) {
	Road r = roadList.get(vehicle.getPos().getRoad()) ;
	List<LaneSection> lsList = ((ListOfListsRoadImpl)r).getLaneSectionsOfRoad().get(0);
	//if the car's position is smaller that the road length the car is on, return false
	if (vehicle.getPos().getLaneSection()< lsList.size())
	return false;
	else return true;
	}

	/*
	 * Check if a position is empty
	 */

	private boolean NotEmpty(Position startingPos2) {
		Road r = roadList.get(startingPos2.getRoad()); // returns a road
		// (()r).
		List<LaneSection> lanes = ((ListOfListsRoadImpl) r).getLaneSectionsOfRoad().get(startingPos2.getLane());
		if (lanes.get(startingPos2.getLaneSection()).hasVehicleOnSeciton())
			return true;

		return false;
	}

	/*
	 * check the five positions ahead of the car
	 */

	private boolean lookAhead(Position p, int a) {
		int LaneIndex = p.getLane();
		int LaneSection = p.getLaneSection();
		int Road = p.getRoad();
		Road R;
		R = roadList.get(Road);
		int newLane = LaneSection;
		Position Pos = new Position();
		Pos.update(p.getRoad(), p.getLane(), p.getLaneSection());
		List<LaneSection> Lane = ((ListOfListsRoadImpl) R).getLaneSectionsOfRoad().get(LaneIndex);

		for (int x = LaneSection; x < LaneSection + a && x < Lane.size(); x++) {

			Pos.update(Road, LaneIndex, newLane++);
			if (NotEmpty(Pos))
				return false;
			//x++;
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

	// System.out.println("Step " + ++iteration);

	// generates a car at a certain lane given certain probability 'freq'

	private void generateCar(Position p) {
		float chance = r.nextFloat();
		Position carPos = new Position();
		carPos.update(p.getRoad(), p.getLane(), p.getLaneSection());
		if (chance <= freq) {
			Vehicle Car = vehicleFactory.getVehicle(VehicleType.CAR);
			Car.setPos(p);
			System.out.println(vehicleStartCoord);
			Car.setAxom(vehicleStartCoord);
			AddToActive(Car, carPos);
		}

	}

	/*
	* function that links a car to a certain junction path
	*/
	private void carOnPath(int vehicleIndex , List<Position> path){
	vehicleList.get(vehicleIndex).setOnJunction=true;
	vehicleList.get(vehicleIndex).setPath(path);
	
	
	}
	
	/*
	* function to compare if two positions are equal
	*
	*/
	}
	private bool isEqual(Position a, Position b){
		if (  (a.getLaneSection== b.getLaneSection)
			&&(a.getLane== b.getLane)
			&&(a.getRoad== b.getRoad) )
			return true;
			
		else 
			return false;
		
	}
}
