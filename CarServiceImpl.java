package com.carApp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carApp.pojo.CarUser;
import com.carApp.pojo.Slot;
import com.carApp.repository.CarRepo;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepo carRepo;
	public void setCarRepo(CarRepo carRepo) {
		this.carRepo = carRepo;
	}	

	public boolean registerCar(CarUser user) {
		
		int result = carRepo.registerUser(user);
		if(result != 0) {
			return true;
		}else {
			return false;
		}
	}

	public String logInCarUser(CarUser carUser) {
		int result = carRepo.logInCarUser(carUser);
		if (result != 0) {
			return "valid";
		} else {
			return "invalid";
		}
	}
	
	public boolean checkAvailability(Date inputDate) {
		int count = carRepo.checkAvailability(inputDate);
		if (count >= 5) {
			return false;
		} else {
			return true;
		}
	}	

	
	public boolean bookASlot(Date date, String vehicleNo) {
		
		int result = carRepo.bookASlot(date,vehicleNo);
		if(result > 0) {
			return true;
		}else {
			return false;
		}

	}

	public List<Slot> displayDashboard(Date inputDate) {
		// TODO Auto-generated method stub
		return carRepo.displayDashboard(inputDate);
	}
}
	
