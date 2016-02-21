package com.kcl.keepitclean.main.vehicle;

public class VehicleFactory {
	public Vehicle getVehicle(String vehicleType){
		 
	      if(vehicleType == null){
	         return null;
	      }		
	      
	      if(vehicleType.equalsIgnoreCase("CAR")){
	         return new Car();
	         
	      }else if(vehicleType.equalsIgnoreCase("BUS")){
	         return new Bus();
	         
	      }else if(vehicleType.equalsIgnoreCase("EMERGENCY")){
	         return new Emergency();
	      }
	      
	      return null;
	   }
}
