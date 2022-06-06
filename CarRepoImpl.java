package com.carApp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carApp.pojo.CarUser;
import com.carApp.pojo.Slot;

@Repository
public class CarRepoImpl implements CarRepo {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int registerUser(CarUser carUserObj) {
		String query = "insert into car_user(userName, password, gender, mobile, vehicleNo)"
				+ " values( ?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(query, new Object[] {carUserObj.getUserName(), 
				carUserObj.getPassword(), carUserObj.getGender(), carUserObj.getMobile(),
				carUserObj.getVehicleNo()});
		return result;
	}

	public int logInCarUser(CarUser carUserObj) {
		String query = "select count(*) from car_user where userName = ? and password = ?";
		int result = jdbcTemplate.queryForObject(query, new Object[] {carUserObj.getUserName(), 
				carUserObj.getPassword()}, Integer.class);
		return result;
	}
	
	public int checkAvailability(Date inputDate) {
		java.sql.Date dateInSql = new java.sql.Date(inputDate.getTime());
	    String query = "select count(*) from slot_info where serviceDate = ?";
	    int result = jdbcTemplate.queryForObject(query, new Object[] {dateInSql}, 
	    		Integer.class);
	    return result;
	}

	public int bookASlot(Date inputDate, String vehicleNo) {
		java.sql.Date dateInSql = new java.sql.Date(inputDate.getTime());
		String query = "insert into slot_info(serviceDate, status, vehicleNo)" + 
				" values( ?, ?, ?)";
	    int result = jdbcTemplate.update(query, new Object[] {dateInSql, "booked", vehicleNo});
	    return result;
	}
	

	public List<Slot> displayDashboard(Date inputDate) {
		java.sql.Date dateInSql = new java.sql.Date(inputDate.getTime());
	    String query = "select * from slot_info where serviceDate = ?";
	    List<Slot> slots = jdbcTemplate.query(query, new Object[] {dateInSql}, 
	    		new SlotRowMapper());
	    return slots;
	}
}
