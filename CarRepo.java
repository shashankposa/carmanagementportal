package com.carApp.repository;

import java.util.Date;
import java.util.List;

import com.carApp.pojo.CarUser;
import com.carApp.pojo.Slot;

public interface CarRepo {
	
	int registerUser(CarUser carUserObj);
	
	int logInCarUser(CarUser carUserObj);
	
	int checkAvailability(Date inputDate);
	
	int bookASlot(Date inputDate, String vehicleNo);
	
	List<Slot> displayDashboard(Date inputDate);

}
