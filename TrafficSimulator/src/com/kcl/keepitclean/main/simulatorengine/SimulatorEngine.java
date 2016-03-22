
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
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.kcl.keepitclean.main.GUI.IRenderer;
import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight;
import com.kcl.keepitclean.main.roadnetwork.laneSection.LaneSection;
import com.kcl.keepitclean.main.roadnetwork.road.ListOfListsRoadImpl;
import com.kcl.keepitclean.main.roadnetwork.road.Road;
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
	private static final ArrayList<Position> entrancePoints = new ArrayList<>();
	private int vehicleCounter = 0;
	private List<Integer> speedList;
	private static final ArrayList<Position> exitPoints = new ArrayList<>();

	//	private Point startCoord = new Point(35, 0);
	//	private Point endCoord = new Point(0, 100);

	//	private Point vehicleStartCoord = new Point();

	//	private LaneFactory laneFactory;

	private Point vehicleStartCoord = new Point(35 + Constant.VEHICLE_LEFT_MARGIN, 0);

	//	private LaneFactory laneFactory;

	//	private RoadFactory roadFactory;
	private List<Road> roadList;
	private List<Junction> junctionList;
	private List<TrafficLight> trafficLightList;

	private Context context;
	private Road masterRoad;
	private List<Position> startingPositions;
	Map1 map = new Map1();
	private IRenderer renderer;

	public SimulatorEngine(Object simulatorGUI) {

		// this.simulatorGUI = simulatorGUI;
		roadList = new ArrayList<>();
		vehicleList = new ArrayList<>();
		junctionList = new ArrayList<>();
		trafficLightList = new ArrayList<>();
		//	startingPos = new Position();
		startingPositions = new ArrayList<>();

		//listofspeed
		speedList = new ArrayList<>();

		r = new Random();
		context = new Context(roadList, vehicleList);

		vehicleFactory = new VehicleFactory();
		//		laneFactory = new LaneFactory();
		//		roadFactory = new RoadFactory(laneFactory);

	}

	public void init() {

		roadList = map.getRoads();
		for (Road road : roadList) {
			context.addRoad(road);
		}

		entrancePoints.add(new Position(0,0,0));
		entrancePoints.add(new Position(6,0,0));
		entrancePoints.add(new Position(12,0,0));
		entrancePoints.add(new Position(18,0,0));
		entrancePoints.add(new Position(29,0,0));
		entrancePoints.add(new Position(31,0,0));


		exitPoints.add(new Position(1,0,29));
		exitPoints.add(new Position(7,0,29));
		exitPoints.add(new Position(13,0,29));
		exitPoints.add(new Position(19,0,29));
		exitPoints.add(new Position(28,0,29));
		exitPoints.add(new Position(30,0,29));


		junctionList = map.getJunctions();
		for (Junction junction : junctionList) {
			context.addJunction(junction);
		}

		for (int i=0; i<junctionList.size(); i++){
			Junction junction;
			junction= junctionList.get(i);
			junction.setIndex(i);
			context.addJunction(junction);
		}


		trafficLightList = map.getTrafficLights();
		for (TrafficLight trafficLight : trafficLightList) {
			trafficLight.activate();
			context.addTrafficLight(trafficLight);
		}

		System.out.println("<SimulatorEngine> Got Road List"); // test line
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

	//	private void generateRoad() {
	//		masterRoad = roadFactory.produceRoad("listoflistsroadimpl", 50, 1);
	//
	//		((ListOfListsRoadImpl) masterRoad).setStartCoordinate(startCoord);
	//		((ListOfListsRoadImpl) masterRoad).setEndCoordinate(endCoord);
	//
	//		context.addRoad(masterRoad);
	//	}

	public void startSimulation() {

		SessionManager.getInstance().startSession();
		init();
		SessionManager.getInstance().addObserver(this);
		renderer.render();
		System.out.println("<SimulatorEngine>Session Started"); // test line

	}

	public void stopSimulation() {
		SessionManager.getInstance().stopSession();
		clearAll();
		init();
	}

	int iteration = 0;

	@Override
	public void update(Observable o, Object arg) {

		// Generate Car, assign its position to be the starting Position.
		Random generator = new Random();
		int temp= generator.nextInt(6);

		Position startingPos   = new Position ();
		startingPos.update(entrancePoints.get(temp).getMode(),
				entrancePoints.get(temp).getRoad(),
				entrancePoints.get(temp).getLane(),
				entrancePoints.get(temp).getLaneSection());

		if (iteration == 0) {
			Vehicle car;

			car = vehicleFactory.getVehicle(VehicleType.CAR); // generate a car
			vehicleStartCoord.x = roadList.get(startingPos.getRoad()).getStartCoordinates().x;
			vehicleStartCoord.y = roadList.get(startingPos.getRoad()).getStartCoordinates().y
					+ startingPos.getLane()* Constant.LANE_SIZE* Constant.PIXELS + Constant.VEHICLE_LEFT_MARGIN;
			car.setAxom(vehicleStartCoord);
			addToActive(car, startingPos); // add to Active List of cars

			System.out.println("<SimulatorEngine>First Car Generated"); // test
			// line

		}




		else if (isPositionEmpty(startingPos)) {
			generateCar(startingPos); // generates car at starting point with
			// factor 'freq'
			System.out.println("<SimulatorEngine>Car Generated"); // test line
		}
		renderer.render();

		// iterate on all cars, move car only if LookAhead is true
		for (int i = 0; i < vehicleList.size(); i++) {

			System.out.println("<SimulatorEngine>iterating on car number " + i); // test
			// //
			// line


			//if the car is on a junction
			if(vehicleList.get(i).getOnJunction()){

				List <LaneSection>path = new ArrayList<>();
				//Check if next position is empty
				path =vehicleList.get(i).getPath();
				//actually the junctionIndex
				int roadIndex= vehicleList.get(i).getPos().getRoad();

				//get the next index
				int nextPositionIndex= vehicleList.get(i).getPIndex();
				nextPositionIndex++;

				//check if car has reached end of the route
				if(nextPositionIndex==2){
					vehicleList.get(i).setOnJunction(false);


					int nextRoadIndex =vehicleList.get(i).getNextRoadIndex();
					Position newPos = new Position(0,nextRoadIndex, 0,0);
					//if the next 5 positions on the destination road are free
					moveWrapper(vehicleList.get(i).getPos(), newPos, i);
					continue;

					//					if (lookAhead(vehicleList.get(i).getPos(), 5)){
					//						moveWrapper(vehicleList.get(i).getPos(), newPos, i);
					//					}

				}
				//if vehicle still on path
				if (!vehicleList.get(i).getPath().get(nextPositionIndex).hasVehicleOnSeciton())
				{
					Position newPos= new Position(1,roadList.get(roadIndex).getEndJunction().getIndex() ,path.get(0).getJunctionGridIndex(),0);

					//move car to first junction position
					moveWrapper(vehicleList.get(i).getPos(), newPos, i);

					//increaseCarPathIterator
					vehicleList.get(i).IncrementPIndex();

				} else continue;




			}

			// if car is not a junction, move as long as the next position is
			// empty
			if ((!reachedEnd(vehicleList.get(i))) && lookAhead(vehicleList.get(i).getPos(), 5) ) {

				Position newPos = new Position();
				if (vehicleList.get(i).getPos().getLaneSection() <
						roadList.get(vehicleList.get(i).getPos().getRoad()).getLengthOfRoad() - 1)
				{
					System.out.println("car POS:" + vehicleList.get(i).getPos().getLaneSection() );
					System.out.println("Road length: " + roadList.get(vehicleList.get(i).getPos().getRoad()).getLengthOfRoad() );
					newPos.update(
							vehicleList.get(i).getPos().getMode(),
							vehicleList.get(i).getPos().getRoad(),
							vehicleList.get(i).getPos().getLane(),
							vehicleList.get(i).getPos().getLaneSection()+1);


					moveWrapper ( vehicleList.get(i).getPos(), newPos, i);
					System.out.println("<SimulatorEngine>Car " + vehicleList.get(i)+" moved to "+ newPos.getLaneSection()); // test line

				}


			}



			// TODO: support for different maps.





			int roadIndex= vehicleList.get(i).getPos().getRoad();


			//if car is at the end of the road and theres a junction upcoming
			if ( reachedEnd(vehicleList.get(i)) && roadList.get(roadIndex).hasJunction() ){
				Junction junc;
				junc= roadList.get(roadIndex).getEndJunction();

				List <LaneSection> path=new ArrayList<>() ;
				Point randPoint= junc.getRandomExitPoint();

				//get the next road that the junction is going to be on. Car needs this to know 
				//next destination after the junction
				int nextRoadIndex;
				nextRoadIndex= map.getStartPointMap().get(randPoint).getIndex();
				vehicleList.get(i).setNextRoadIndex(nextRoadIndex);

				path= junc.produceRoute(roadList.get(roadIndex).getJuctionEndCoordinates(), randPoint) ;

				//set car on path
				carOnPath(i, path);

				//check if the next position is empty.

				if (!path.get(0).hasVehicleOnSeciton()){

					Position newPos= new Position(1,roadList.get(roadIndex).getEndJunction().getIndex() ,path.get(0).getJunctionGridIndex(),0);

					//move car to first junction position
					moveWrapper(vehicleList.get(i).getPos(), newPos, i);

					//increaseCarPathIterator
					vehicleList.get(i).IncrementPIndex();

				} else continue;


				//  Position newPos= new Position(0, JunctionIndex(junction), nextIndexedPath object );

				//if its not: moveWrapper(vehicleList.get(i), vehicleList.get(i).getPos(),



			}
			else if (reachedEnd(vehicleList.get(i))){
				//if a car reached end of the road , remove it and free its position
				vehicleList.remove(i);
				emptySection(vehicleList.get(i).getPos());
				renderer.render();

			}



		}
		iteration++;
	}

	private void moveWrapper(Position pos, Position newPos, int vehicleIndex) {
		//inform context about the change
		Point debugPoint = context.moveVehicle(vehicleList.get(vehicleIndex), pos, newPos);
		//empty the old position
		emptySection(pos);

		//inform the vehicle list

	
			vehicleList.get(vehicleIndex).getPos().update(newPos.getMode(), newPos.getRoad(), newPos.getLane(), newPos.getLaneSection());

		

		//output to terminal
		//		System.out.println("<SimulatorEngine> Car Moved [" + " ID:" + vehicleList.get(vehicleIndex).getID() + " "
		//				+ debugPoint.getX() + ", " + debugPoint.getY() + "]");

		//fillnew section
		fillSection(newPos);


	}

	private void fillSection(Position newPos) {

		if (newPos.getMode() != 1){
			Road r2 = roadList.get(newPos.getRoad()) ;
			LaneSection ls2 =((ListOfListsRoadImpl)r2).getLaneSectionsOfRoad().get(newPos.getLane()).get(newPos.getLaneSection());
			Vehicle vehicle = vehicleList.get(0);
			ls2.putVehicleOnSection(vehicle);
		}



		if(newPos.getMode()==1){
			//Road is the junction
			Vehicle vehicle = vehicleList.get(0);
			junctionList.get(newPos.getRoad()).getSectionsOfJunction().get(newPos.getLane()).putVehicleOnSection(vehicle);



		}
	}

	private void emptySection(Position pos) {

		if(pos.getMode()!=1){
			Road r1 = roadList.get(pos.getRoad()) ;
			LaneSection ls =((ListOfListsRoadImpl)r1).getLaneSectionsOfRoad().get(pos.getLane()).get(pos.getLaneSection());
			ls.removeVehicleFromSection();
		}

		if(pos.getMode()==1){
			//Road is the junction
			Vehicle vehicle = vehicleList.get(0);
			junctionList.get(pos.getRoad()).getSectionsOfJunction().get(pos.getLane()).putVehicleOnSection(vehicle);



		}
	}

	/*
	 * Check if a car has reached an exit point, if it did return true;
	 */

	private boolean reachedEnd(Vehicle vehicle) {
		Road r = roadList.get(vehicle.getPos().getRoad()) ;
		List<LaneSection> lsList = ((ListOfListsRoadImpl)r).getLaneSectionsOfRoad().get(0);
		//if the car's position is smaller that the road length the car is on, return false
		//if (vehicle.getPos().getLaneSection()< lsList.size())
		if (vehicle.getPos().getLaneSection() < roadList.get(vehicle.getPos().getRoad()).getLengthOfRoad() - 1)

			return false;


		else return true;
	}

	/*
	 * Check if a position is empty
	 */

	private boolean isPositionEmpty(Position position) {

		Road road = roadList.get(position.getRoad()); // returns a road
		List<LaneSection> lanes = ((ListOfListsRoadImpl) road).getLaneSectionsOfRoad().get(position.getLane());

		return !lanes.get(position.getLaneSection()).hasVehicleOnSeciton();
	}

	/*
	 * check the five positions ahead of the car
	 */

	private boolean lookAhead(Position position, int posToLookAhead) {

		int laneIndex = position.getLane();
		int laneSectionIndex = position.getLaneSection();
		int roadIndex = position.getRoad();
		int mode= position.getMode();
		int newLaneSectionIndex = laneSectionIndex;

		Position pos = new Position();
		pos.update(position.getMode(), position.getRoad(), position.getLane(), position.getLaneSection());

		Road road = roadList.get(roadIndex);
		List<LaneSection> Lane = ((ListOfListsRoadImpl) road).getLaneSectionsOfRoad().get(laneIndex);

		for (int x = laneSectionIndex; (x < (laneSectionIndex + posToLookAhead) && x < Lane.size()-2 ); x++) {

			pos.update(mode, roadIndex, laneIndex, ++newLaneSectionIndex);
			if (!isPositionEmpty(pos)) {
				return false;
			}
			// x++;
		}
		return true;
	}

	/*
	 * Adds a car and its position to the Active Cars List
	 */

	private void addToActive(Vehicle car, Position Pos) {
		car.setPos(Pos);
		vehicleList.add(car);
		vehicleCounter++;
		speedList.add(car.getSpeed());
	}

	// System.out.println("Step " + ++iteration);

	// generates a car at a certain lane given certain probability 'freq'

	private void generateCar(Position p) {
		float chance = r.nextFloat();
		Position carPos = new Position();
		carPos.update(p.getMode(), p.getRoad(), p.getLane(), p.getLaneSection());
		vehicleStartCoord.x = roadList.get(p.getRoad()).getStartCoordinates().x;
		vehicleStartCoord.y = roadList.get(p.getRoad()).getStartCoordinates().y
				+ p.getLane()* Constant.LANE_SIZE* Constant.PIXELS + Constant.VEHICLE_LEFT_MARGIN;

		if (chance <= freq) {
			Vehicle Car = vehicleFactory.getVehicle(VehicleType.CAR);
			Car.setPos(p);
			System.out.println(vehicleStartCoord);
			Car.setAxom(vehicleStartCoord);
			addToActive(Car, carPos);
			fillSection(p);
		}

	}

	/*
	 * function that links a car to a certain junction path
	 */
	private void carOnPath(int vehicleIndex , List<LaneSection> path){

		vehicleList.get(vehicleIndex).setOnJunction(true);
		vehicleList.get(vehicleIndex).updatePath(path);


	}

	/*
	 * function to compare if two positions are equal
	 *
	 */
	//}
	private boolean isEqual(Position a, Position b){
		if (  (a.getLaneSection()== b.getLaneSection())
				&&(a.getLane()== b.getLane())
				&&(a.getRoad()== b.getRoad()) )
			return true;

		else
			return false;

	}

	//adding for SimulationData

	public int getVehicleCounter() {
		return vehicleCounter;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public List<Integer> getSpeedList() {
		return speedList;
	}
	public void clearAll(){

		vehicleList.clear();
		iteration=0;
		//TODO: generate report

	}
}
