package com.carApp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carApp.pojo.CarUser;
import com.carApp.pojo.ResponseEntity;
import com.carApp.pojo.Slot;
import com.carApp.service.CarService;

@Controller
@RequestMapping(value="/car")
public class CarController {

	@Autowired
	private CarService service;

	public CarService getService() {
		return service;
	}

	public void setService(CarService service) {
		this.service = service;
	}
	
	@CrossOrigin(value="http://localhost:4200")
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity registerCarUser(@RequestBody CarUser carUser) {
		boolean result = service.registerCar(carUser);
		ResponseEntity resp = new ResponseEntity(200, result, "");
		return resp;
	}
	
	@CrossOrigin(value="http://localhost:4200")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity logInCarUser(@RequestBody CarUser carUser) {
		String result = service.logInCarUser(carUser);
		ResponseEntity resp = new ResponseEntity(200, result, "");
		return resp;
	}
	
	@CrossOrigin(value="http://localhost:4200")
	@RequestMapping(value="/check", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity checkAvailibility(@RequestBody String date) throws ParseException {
		System.out.println("date received is "+date);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date dateInUtil = sdf.parse(date);
		boolean result = service.checkAvailability(dateInUtil);
		ResponseEntity resp = new ResponseEntity(200, result, "");
		return resp;
	}
	
	@CrossOrigin(value="http://localhost:4200")
	@RequestMapping(value="/book", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity bookASlot(@RequestBody String date) 
			throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date dateInUtil = sdf.parse(date);
		boolean result = service.bookASlot(dateInUtil, "abc");
		ResponseEntity resp = new ResponseEntity(200, result, "");
		return resp;
	}
	
	@CrossOrigin(value="http://localhost:4200")
	@RequestMapping(value="/display", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity displayDashboard(@RequestBody String date) throws ParseException {
		System.out.println("date received is "+ date);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date dateInUtil = sdf.parse(date);
		List<Slot> slots = service.displayDashboard(dateInUtil);
		ResponseEntity resp = new ResponseEntity(200, slots, "");
		return resp;
	}
	
}
	