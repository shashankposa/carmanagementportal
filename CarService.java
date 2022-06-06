package com.carApp.service;

import java.util.Date;
import java.util.List;

import com.carApp.pojo.CarUser;
import com.carApp.pojo.Slot;

public interface CarService {

	boolean registerCar(CarUser user);
	
	String logInCarUser(CarUser carUser);
	
	boolean checkAvailability(Date inputDate);
	
	boolean bookASlot(Date date, String vehicleNo);
	
	List<Slot> displayDashboard(Date inputDate);

}
