package com.enfec.bookstatus.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookingRowMapper implements RowMapper<Booking> {

	@Override
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		Booking b = new Booking();
		b.setBooking_status_code(rs.getInt("Booking_Status_Code"));
		b.setBooking_status_description(rs.getString("Booking_Status_Description"));
		return b;
	}

}
