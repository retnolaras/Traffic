package com.kcl.keepitclean.main.vehicle;

public class VehicleFactory {
	public Vehicle getVehicle(VehicleType vehicleType) {

		if (vehicleType == null) {
			return null;
		}

		if (vehicleType == VehicleType.CAR) {
			return new Car();

		} else if (vehicleType == VehicleType.BUS) {
			return new Bus();

		} else if (vehicleType == VehicleType.EMERGENCY) {
			return new Emergency();
		}

		return null;
	}
}
