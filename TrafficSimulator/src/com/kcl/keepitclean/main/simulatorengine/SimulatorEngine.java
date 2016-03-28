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
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.kcl.keepitclean.main.GUI.IRenderer;
import com.kcl.keepitclean.main.GUI.MapType;
import com.kcl.keepitclean.main.GUI.SimulationSettings;
import com.kcl.keepitclean.main.GUI.TrafficDensity;
import com.kcl.keepitclean.main.policy.Policy;
import com.kcl.keepitclean.main.roadnetwork.junction.Junction;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight;
import com.kcl.keepitclean.main.roadnetwork.junction.TrafficLight.State;
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
	static float freq = 0.1f;
	private Random r;
	private Position startingPos;
	private VehicleFactory vehicleFactory;
	private List<Vehicle> vehicleList;
	private static final ArrayList<Position> entrancePoints = new ArrayList<>();
	private int vehicleCounter = 0;
	// private List<Integer> speedList;
	private static final ArrayList<Position> exitPoints = new ArrayList<>();

	// private Point startCoord = new Point(35, 0);
	// private Point endCoord = new Point(0, 100);

	// private Point vehicleStartCoord = new Point();

	// private LaneFactory laneFactory;

	private Point vehicleStartCoord = new Point(35 + Constant.VEHICLE_LEFT_MARGIN, 0);

	// private LaneFactory laneFactory;

	// private RoadFactory roadFactory;
	private List<Road> roadList;
	private List<Junction> junctionList;
	private List<TrafficLight> trafficLightList;

	private Context context;
	private Road masterRoad;
	private List<Position> startingPositions;
	private SimulationMap map;
	private IRenderer renderer;
	private SimulationSettings userSettings;
	private Policy policy;
	private int sessionDuration;
	private TrafficDensity trafficDensity = TrafficDensity.NORMAL;
	private ArrayList<Boolean> userSelectionEntryPoints ;

	public SimulatorEngine(Object simulatorGUI) {

		userSettings = (SimulationSettings) simulatorGUI;
		policy = Policy.getPolicyInstance();
		sessionDuration = userSettings.getSessionDuration();
		trafficDensity = userSettings.getTrafficDensity();

		if (userSettings.getMapType() == MapType.JUNCTION)
			map = (SimulationMap) new SimpleMap();
		else
			map = (SimulationMap) new TownMap();

		// this.simulatorGUI = simulatorGUI;
		roadList = new ArrayList<>();
		vehicleList = Collections.synchronizedList(new ArrayList<>());
		junctionList = new ArrayList<>();
		trafficLightList = new ArrayList<>();
		// startingPos = new Position();
		startingPositions = new ArrayList<>();
		userSelectionEntryPoints= new ArrayList<>();

		// listofspeed
		// speedList = new ArrayList<>();

		r = new Random();
		context = new Context(roadList, vehicleList);

		vehicleFactory = new VehicleFactory();
		// laneFactory = new LaneFactory();
		// roadFactory = new RoadFactory(laneFactory);

	}

	public void init() {

		roadList = map.getRoads();
		for (Road road : roadList) {
			context.addRoad(road);
		}

		junctionList = map.getJunctions();
		for (Junction junction : junctionList) {
			context.addJunction(junction);
		}


		for (int i = 0; i < junctionList.size(); i++) {
			Junction junction;
			junction = junctionList.get(i);
			junction.setIndex(i);
			context.addJunction(junction);
		}

		trafficLightList = map.getTrafficLights();
		for (TrafficLight trafficLight : trafficLightList) {
			trafficLight.activate();
			context.addTrafficLight(trafficLight);
		}
		userSelectionEntryPoints= userSettings.getEntryPoints();
		if (userSettings.getMapType() == MapType.TOWN) {

			if(userSelectionEntryPoints.get(0))
				entrancePoints.add(new Position(0, 0, 0));

			if(userSelectionEntryPoints.get(1))
				entrancePoints.add(new Position(6, 0, 0));

			if(userSelectionEntryPoints.get(2))
				entrancePoints.add(new Position(12, 0, 0));

			if(userSelectionEntryPoints.get(3))
				entrancePoints.add(new Position(18, 0, 0));

			if(userSelectionEntryPoints.get(4))
				entrancePoints.add(new Position(29, 0, 0));

			if(userSelectionEntryPoints.get(5))
				entrancePoints.add(new Position(31, 0, 0));

			exitPoints.add(new Position(1, 0, 29));
			exitPoints.add(new Position(7, 0, 29));
			exitPoints.add(new Position(13, 0, 29));
			exitPoints.add(new Position(19, 0, 29));
			exitPoints.add(new Position(28, 0, 29));
			exitPoints.add(new Position(30, 0, 29));
		} else {
			if(userSelectionEntryPoints.get(0))
				entrancePoints.add(new Position(0, 0, 0));

			if(userSelectionEntryPoints.get(1))
				entrancePoints.add(new Position(7,0,0));

			if(userSelectionEntryPoints.get(2))
				entrancePoints.add(new Position(5,0,0));

			if(userSelectionEntryPoints.get(3))
				entrancePoints.add(new Position(2,0,0));

			exitPoints.add(new Position(1, 0, 79));
			exitPoints.add(new Position(3, 0, 59));
			exitPoints.add(new Position(6, 0, 79));
			exitPoints.add(new Position(4, 0, 59));

		}

		System.out.println("<SimulatorEngine> Got Road List"); // test line

		if (userSettings.getTrafficDensity() == TrafficDensity.HIGH) {
			freq = 0.3f;
		}
		if (userSettings.getTrafficDensity() == TrafficDensity.NORMAL) {
			freq = 0.1f;
		}
		if (userSettings.getTrafficDensity() == TrafficDensity.LOW) {
			freq = 0.05f;
		}
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

	// private void generateRoad() {
	// masterRoad = roadFactory.produceRoad("listoflistsroadimpl", 50, 1);
	//
	// ((ListOfListsRoadImpl) masterRoad).setStartCoordinate(startCoord);
	// ((ListOfListsRoadImpl) masterRoad).setEndCoordinate(endCoord);
	//
	// context.addRoad(masterRoad);
	// }

	public void startSimulation() {

		init();
		SessionManager.getInstance().addObserver(this);
		SessionManager.getInstance().startSession();
		renderer.render();
		System.out.println("<SimulatorEngine>Session Started"); // test line

	}

	public void stopSimulation() {
		SessionManager.getInstance().stopSession();
		clearAll();
	}

	int iteration = 0;

	@Override
	public void update(Observable o, Object arg) {

		// Generate Car, assign its position to be the starting Position.
		Random generator = new Random();
		int temp = generator.nextInt(entrancePoints.size());

		Position startingPos = new Position();
		startingPos.update(entrancePoints.get(temp).getMode(), entrancePoints.get(temp).getRoad(),
				entrancePoints.get(temp).getLane(), entrancePoints.get(temp).getLaneSection());

		if (iteration == 0) {
			Vehicle car;

			car = vehicleFactory.getVehicle(VehicleType.CAR); // generate a car

			vehicleStartCoord.x = roadList.get(startingPos.getRoad()).getStartCoordinates().x
					+ startingPos.getLaneSection() * Constant.PIXELS;
			vehicleStartCoord.y = roadList.get(startingPos.getRoad()).getStartCoordinates().y
					+ startingPos.getLane() * Constant.LANE_SIZE * Constant.PIXELS
					+ Constant.VEHICLE_LEFT_MARGIN * Constant.PIXELS;
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

			// if the car is on a junction
			if (vehicleList.get(i).getOnJunction() &&  vehicleList.get(i).getPos().getMode()==1) {

				List<LaneSection> path = new ArrayList<>();
				// Check if next position is empty
				path = vehicleList.get(i).getPath();
				// actually the junctionIndex
				int roadIndex = vehicleList.get(i).getPos().getRoad();

				// get the next index
				int nextPositionIndex = vehicleList.get(i).getPIndex();
				nextPositionIndex++;
				// check if car has reached end of the route

				if (nextPositionIndex == 2 ) {
					vehicleList.get(i).setOnJunction(false);

					int nextRoadIndex = vehicleList.get(i).getNextRoadIndex();
					Position newPos = new Position(0, nextRoadIndex, 0, 0);
					// if the next 5 positions on the destination road are free
					moveWrapper(vehicleList.get(i).getPos(), newPos, i);
					vehicleList.get(i).resetPIndex();
					continue;


				}

				if(vehicleList.get(i).getPath().get(nextPositionIndex).hasVehicleOnSeciton()){
					continue;
				}

				// if vehicle still on path
				if (i< vehicleList.size()  && nextPositionIndex< vehicleList.get(i).getPath().size()
						&& !vehicleList.get(i).getPath().get(nextPositionIndex).hasVehicleOnSeciton()) {
					Position newPos = new Position(1, roadList.get(roadIndex).getEndJunction().getIndex(),
							path.get(0).getJunctionGridIndex(), 0);

					// move car to first junction position
					moveWrapper(vehicleList.get(i).getPos(), newPos, i);

					// increaseCarPathIterator
					vehicleList.get(i).IncrementPIndex();

				} else {

					continue;
				}

			}

			// if car is not a junction, move as long as the next position is
			// empty
			//			if(vehicleList.get(i).getPos().getMode()==1){
			//				//.get(i).setOnJunction(true);
			//				continue;
			//			}
			if ((!reachedEnd(vehicleList.get(i))) && lookAhead(vehicleList.get(i).getPos(), 5)) {

				// first check if there is a traffic light

				Position newPos = new Position();
				if (vehicleList.get(i).getPos()
						.getLaneSection() < roadList.get(vehicleList.get(i).getPos().getRoad()).getLengthOfRoad() - 1) {
					System.out.println("car POS:" + vehicleList.get(i).getPos().getLaneSection());
					System.out.println(
							"Road length: " + roadList.get(vehicleList.get(i).getPos().getRoad()).getLengthOfRoad());

					//					newPos.update(vehicleList.get(i).getPos().getMode(), vehicleList.get(i).getPos().getRoad(),
					//							vehicleList.get(i).getPos().getLane(), vehicleList.get(i).getPos().getLaneSection() + 1);

					int vehicleSpeed = vehicleList.get(i).getSpeed();
					int speedModifier = 0;

					if(vehicleSpeed > 1){
						Road r = roadList.get(vehicleList.get(i).getPos().getRoad());
						List<LaneSection> lsList = ((ListOfListsRoadImpl) r).getLaneSectionsOfRoad().get(0);

						if(vehicleSpeed == 2 && 
								(vehicleList.get(i).getPos().getLaneSection() == lsList.size() - 2 )){

							speedModifier = 1;

						} else if(vehicleSpeed == 3 &&
								(vehicleList.get(i).getPos().getLaneSection() == lsList.size() - 3 )) {

							speedModifier = 1;

						} else if(vehicleSpeed == 3 &&
								(vehicleList.get(i).getPos().getLaneSection() == lsList.size() - 2 )){

							speedModifier = 2;

						}
					}

					newPos.update(vehicleList.get(i).getPos().getMode(), 
							vehicleList.get(i).getPos().getRoad(),
							vehicleList.get(i).getPos().getLane(), 
							vehicleList.get(i).getPos().getLaneSection() + vehicleList.get(i).getSpeed() - speedModifier);


					moveWrapper(vehicleList.get(i).getPos(), newPos, i);
					System.out.println(
							"<SimulatorEngine>Car " + vehicleList.get(i) + " moved to " + newPos.getLaneSection()); // test
					// line

				}

			}


			int roadIndex = vehicleList.get(i).getPos().getRoad();

			// if car is at the end of the road and theres a junction upcoming
			if (reachedEnd(vehicleList.get(i)) && roadList.get(roadIndex).hasJunction()) {
				Junction junc;
				junc = roadList.get(roadIndex).getEndJunction();

				// check if trafficLight is red, if it is, mark car position as
				// filled and constinue
				TrafficLight trafficlight;
				Road road = roadList.get(roadIndex);
				trafficlight = context.getTrafficLight(road, junc);
				if (trafficlight != null && trafficlight.getState() == State.RED) {
					fillSection(vehicleList.get(i).getPos());
					continue;
				}

				List<LaneSection> path = new ArrayList<>();
				Point randPoint = junc.getRandomExitPoint();

				// get the next road that the junction is going to be on. Car
				// needs this to know
				// next destination after the junction
				int nextRoadIndex;
				nextRoadIndex = map.getStartPointMap().get(randPoint).getIndex();
				vehicleList.get(i).setNextRoadIndex(nextRoadIndex);

				path = junc.produceRoute(roadList.get(roadIndex).getJuctionEndCoordinates(), randPoint);

				// set car on path
				carOnPath(i, path);

				// check if the next position is empty.

				if (!path.get(0).hasVehicleOnSeciton()) {

					Position newPos = new Position(1, roadList.get(roadIndex).getEndJunction().getIndex(),
							path.get(0).getJunctionGridIndex(), 0);

					// move car to first junction position
					moveWrapper(vehicleList.get(i).getPos(), newPos, i);

					// increaseCarPathIterator
					vehicleList.get(i).IncrementPIndex();

				} else
					continue;

				// Position newPos= new Position(0, JunctionIndex(junction),
				// nextIndexedPath object );

				// if its not: moveWrapper(vehicleList.get(i),
				// vehicleList.get(i).getPos(),

			} else if (reachedEnd(vehicleList.get(i))) {
				// if a car reached end of the road , remove it and free its
				// position
				emptySection(vehicleList.get(i).getPos());
				vehicleList.remove(i);
				renderer.render();

			}

		}
		iteration++;
		if(sessionDuration > 0 &&
				iteration == sessionDuration){

			SessionManager.getInstance().pauseSession();
		}
	}

	private void moveWrapper(Position pos, Position newPos, int vehicleIndex) {
		// inform context about the change
		Point debugPoint = context.moveVehicle(vehicleList.get(vehicleIndex), pos, newPos);
		// empty the old position
		emptySection(pos);

		// inform the vehicle list

		vehicleList.get(vehicleIndex).getPos().update(newPos.getMode(), newPos.getRoad(), newPos.getLane(),
				newPos.getLaneSection());

		// output to terminal
		// System.out.println("<SimulatorEngine> Car Moved [" + " ID:" +
		// vehicleList.get(vehicleIndex).getID() + " "
		// + debugPoint.getX() + ", " + debugPoint.getY() + "]");

		// fillnew section
		fillSection(newPos);

	}

	private void fillSection(Position newPos) {

		if (newPos.getMode() != 1) {
			Road r2 = roadList.get(newPos.getRoad());
			LaneSection ls2 = ((ListOfListsRoadImpl) r2).getLaneSectionsOfRoad().get(newPos.getLane())
					.get(newPos.getLaneSection());
			Vehicle vehicle = vehicleList.get(0);
			ls2.putVehicleOnSection(vehicle);
		}

		if (newPos.getMode() == 1) {
			// Road is the junction
			Vehicle vehicle = vehicleList.get(0);
			junctionList.get(newPos.getRoad()).getSectionsOfJunction().get(newPos.getLane())
			.putVehicleOnSection(vehicle);

		}
	}

	private void emptySection(Position pos) {

		if (pos.getMode() != 1) {
			Road r1 = roadList.get(pos.getRoad());
			LaneSection ls = ((ListOfListsRoadImpl) r1).getLaneSectionsOfRoad().get(pos.getLane())
					.get(pos.getLaneSection());
			ls.removeVehicleFromSection();
		}

		if (pos.getMode() == 1) {
			// Road is the junction
			Vehicle vehicle = vehicleList.get(0);
			junctionList.get(pos.getRoad()).getSectionsOfJunction().get(pos.getLane()).removeVehicleFromSection();

		}
	}

	/*
	 * Check if a car has reached an exit point, if it did return true;
	 */

	private boolean reachedEnd(Vehicle vehicle) {
		Road r = roadList.get(vehicle.getPos().getRoad());
		List<LaneSection> lsList = ((ListOfListsRoadImpl) r).getLaneSectionsOfRoad().get(0);
		// if the car's position is smaller that the road length the car is on,
		// return false
		// if (vehicle.getPos().getLaneSection()< lsList.size())
		if (vehicle.getPos().getLaneSection() < roadList.get(vehicle.getPos().getRoad()).getLengthOfRoad() - 5)

			return false;

		else
			return true;
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
		int mode = position.getMode();
		int newLaneSectionIndex = laneSectionIndex;

		Position pos = new Position();
		pos.update(position.getMode(), position.getRoad(), position.getLane(), position.getLaneSection());

		Road road = roadList.get(roadIndex);
		if(laneIndex>=1){
			laneIndex=0;

		}
		List<LaneSection> Lane = ((ListOfListsRoadImpl) road).getLaneSectionsOfRoad().get(laneIndex);

		for (int x = laneSectionIndex; (x < (laneSectionIndex + posToLookAhead) && x < Lane.size() - 1); x++) {

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
		// speedList.add(car.getSpeed());
	}

	// System.out.println("Step " + ++iteration);

	// generates a car at a certain lane given certain probability 'freq'

	private void generateCar(Position p) {
		float chance = r.nextFloat();
		Position carPos = new Position();
		carPos.update(p.getMode(), p.getRoad(), p.getLane(), p.getLaneSection());
		vehicleStartCoord.x = roadList.get(p.getRoad()).getStartCoordinates().x + p.getLaneSection() * Constant.PIXELS;
		vehicleStartCoord.y = roadList.get(p.getRoad()).getStartCoordinates().y
				+ p.getLane() * Constant.LANE_SIZE * Constant.PIXELS + Constant.VEHICLE_LEFT_MARGIN * Constant.PIXELS;

		if (chance <= freq) {

			Vehicle car = null;


			int vehicleTypeChance = r.nextInt(10);
			switch (vehicleTypeChance) {
			case 0:
				car = vehicleFactory.getVehicle(VehicleType.EMERGENCY);
				break;
			case 1 | 2:
				car = vehicleFactory.getVehicle(VehicleType.BUS);
				break;
			default:
				car = vehicleFactory.getVehicle(VehicleType.CAR);
				break;
			}

			car.setPos(p);
			System.out.println(vehicleStartCoord);
			car.setAxom(vehicleStartCoord);

			addToActive(car, carPos);
			fillSection(p);
		}

	}

	/*
	 * function that links a car to a certain junction path
	 */
	private void carOnPath(int vehicleIndex, List<LaneSection> path) {

		vehicleList.get(vehicleIndex).setOnJunction(true);
		vehicleList.get(vehicleIndex).updatePath(path);

	}

	/*
	 * function to compare if two positions are equal
	 *
	 */
	// }
	private boolean isEqual(Position a, Position b) {
		if ((a.getLaneSection() == b.getLaneSection()) && (a.getLane() == b.getLane()) && (a.getRoad() == b.getRoad()))
			return true;

		else
			return false;

	}

	// adding for SimulationData

	public int getVehicleCounter() {
		return vehicleCounter;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	// public List<Integer> getSpeedList() {
	// return speedList;
	// }

	public int getIteration() {
		return iteration;
	}

	public void clearAll() {

		vehicleList.clear();

		entrancePoints.clear();
		exitPoints.clear();

		iteration = 0;

		// TODO: generate report

	}
}
