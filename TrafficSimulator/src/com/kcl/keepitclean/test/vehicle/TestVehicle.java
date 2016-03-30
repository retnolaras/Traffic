package com.kcl.keepitclean.test.vehicle;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kcl.keepitclean.main.vehicle.Bus;
import com.kcl.keepitclean.main.vehicle.Car;
import com.kcl.keepitclean.main.vehicle.Emergency;
import com.kcl.keepitclean.main.vehicle.Vehicle;
import com.kcl.keepitclean.main.vehicle.VehicleFactory;
import com.kcl.keepitclean.main.vehicle.VehicleType;

public class TestVehicle {

	private Vehicle car;
	private Vehicle bus;
	private Vehicle emergency;
	
	//
	private String vehicletype;

	private VehicleFactory v = new VehicleFactory();

	@Before
	public void buildBeforeEach() {
		car = v.getVehicle(VehicleType.CAR);
		bus = v.getVehicle(VehicleType.BUS);
		emergency = v.getVehicle(VehicleType.EMERGENCY);
	}
	
	@After
	public void destroyAfterEach() {
		car = null;
		bus = null;
		emergency = null;
	}
	
	@Test
	public void testCreateCar() {
		assertTrue(car instanceof Car);
		}
	
	@Test
	public void testCreateBus() {
		assertTrue(bus instanceof Bus);
		}
	
	@Test
	public void testCreateEmergency() {
		assertTrue(emergency instanceof Emergency);
		}



}
