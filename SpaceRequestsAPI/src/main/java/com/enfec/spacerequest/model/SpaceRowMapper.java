package com.enfec.spacerequest.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SpaceRowMapper implements RowMapper<SpaceRequests> {

	@Override
	public SpaceRequests mapRow(ResultSet rs, int rowNum) throws SQLException {
		SpaceRequests s = new SpaceRequests();
		s.setSpace_request_id(rs.getInt("Space_Requests_Id"));
		s.setRoom_id(rs.getInt("Room_Id"));
		s.setEvent_id(rs.getInt("Event_Id"));
		s.setBooking_status_code(rs.getInt("Booking_Status_Code"));
		s.setCommercial_or_free(rs.getString("Commercial_or_Free"));
		s.setOccupancy(rs.getInt("Occupancy"));
		s.setOccupancy_date_from(rs.getTimestamp("Occupancy_Date_From"));
		s.setOccupancy_date_to(rs.getTimestamp("Occupancy_Date_To"));
		s.setOther_details(rs.getString("Other_Details"));
		return s;
	}

}
