package com.enfec.venue.model;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

public class VenueRowMapper implements RowMapper<Venue> {

	@Override
	public Venue mapRow(ResultSet rs, int rowNum) throws SQLException {
		Venue v=new Venue();
		v.setVenue_id(rs.getInt("Venue_Id"));
		v.setVenue_address_id(rs.getInt("Venue_Address_ld"));
		v.setVenue_name(rs.getString("Venue_Name"));
		v.setCity(rs.getString("City"));
		v.setState(rs.getString("State"));
		v.setStreet(rs.getString("Street"));
		v.setCountry(rs.getString("Country"));
		v.setZipcode(rs.getInt("Zipcode"));
		v.setOther_details(rs.getString("Other_details"));
		return v;
	}

}
