package com.enfec.room.model;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

public class RoomRowMapper implements RowMapper<Room> {
	@Override
	public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
		Room r=new Room();
		r.setRoom_id(rs.getInt("Room_Id"));
		r.setVenue_id(rs.getInt("Venue_Id"));
		r.setRoom_name(rs.getString("Room_Name"));
		r.setRoom_capability(rs.getInt("Room_Capability"));
		r.setRate_for_day(rs.getDouble("Rate_For_Day"));
		r.setVenue_name(rs.getString("Venue_Name"));
		r.setVenue_address_id(rs.getInt("Venue_Address_ld"));
		r.setState(rs.getString("State"));
		r.setCity(rs.getString("City"));
		r.setCountry(rs.getString("Country"));
		r.setZipcode(rs.getInt("Zipcode"));
		r.setStreet(rs.getString("Street"));
		r.setOther_details(rs.getString("Other_Details"));
		return r;
	}

}
