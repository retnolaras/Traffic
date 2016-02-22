package com.kcl.keepitclean.test.vehicle;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		vehicletype = car.getClass().getName();
		assertEquals(vehicletype, VehicleType.CAR.toString());
		}
	
	@Test
	public void testCreateBus() {
		vehicletype = bus.getClass().getName();
		assertEquals(vehicletype, VehicleType.BUS.toString());
		}
	
	@Test
	public void testCreateEmergency() {
		vehicletype = emergency.getClass().getName();
		assertEquals(vehicletype, VehicleType.EMERGENCY.toString());
		}



}
