package com.enfec.spacerequest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.enfec.spacerequest.model.SpaceRequests;
import com.enfec.spacerequest.model.SpaceRowMapper;

@Component
@Transactional
public class SpaceRequestsdaoImpl implements SpaceRequestsdao {
	private static final Logger logger = LoggerFactory.getLogger(SpaceRequestsdaoImpl.class);
	final String INSERT_SPACEREQUEST = "insert into Space_Requests (Room_Id,Event_Id,Booking_Status_Code,Occupancy,Commercial_or_Free,Occupancy_Date_From,Occupancy_Date_To,Other_Details)"
			+ "values(:room_id,:event_id,:booking_status_code,:occupancy,:commercial_or_free,:occupancy_date_from,:occupancy_date_to,:other_details)";
	final String GET_DETAILS = "select * from Space_Requests where Space_Requests_Id=?";
	final String UPDATE_DETAILS = "update Space_Requests set Room_Id=:room_id,Event_Id=:event_id,Booking_Status_Code=:booking_status_code,Occupancy=:occupancy,"
			+ "Commercial_or_Free=:commercial_or_free,Occupancy_Date_From=:occupancy_date_from,Occupancy_Date_To=:occupancy_date_to,Other_Details=:other_details"
			+ " where Space_Requests_Id=:space_request_id";
	final String DELETE_SPACE = "delete from Space_Requests where Space_Requests_Id=?";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	/*
	 * Creating SpaceRequests
	 * 
	 * @param SpaceRequests
	 * 
	 * @return affectedRow.
	 * 
	 */

	@Override
	public int saveSpaceRequests(SpaceRequests s) {
		int affectedRow;
		Map<String, Object> param = SpaceInfoMap(s);

		SqlParameterSource pramSource = new MapSqlParameterSource(param);
		logger.info("Register Space Info : {} ", pramSource);
		affectedRow = namedJdbcTemplate.update(INSERT_SPACEREQUEST, pramSource);
		return affectedRow;
	}

	/*
	 * Updating the SpaceRequests details
	 * 
	 * @param SpaceRequests
	 * 
	 * @return affectedRow
	 */

	@Override
	public int updateSpaceRequests(SpaceRequests s) {
		int affectedRow;
		Map<String, Object> param = SpaceInfoMap(s);

		SqlParameterSource pramSource = new MapSqlParameterSource(param);
		logger.info("Register Space Info : {} ", pramSource);
		affectedRow = namedJdbcTemplate.update(UPDATE_DETAILS, pramSource);
		return affectedRow;
	}
	/*
	 * Deleting existing SpaceRequests Details
	 * 
	 * @param space_requests_id
	 * 
	 * @return
	 */

	@Override
	public int deleteSpaceRequests(int space_requests_id) {
		return jdbcTemplate.update(DELETE_SPACE, space_requests_id);
	}
	/*
	 * Getting the details of SpaceRequests based on Space_Requests_Id
	 * 
	 * @param Space_Requests_Id
	 * 
	 * @return list of SpaceRequests Details.
	 */

	@Override
	public List<SpaceRequests> getSpaceRequestsDetails(Integer Space_Requests_Id) {
		return jdbcTemplate.query(GET_DETAILS, new Object[] { Space_Requests_Id }, new SpaceRowMapper());

	}

	private Map<String, Object> SpaceInfoMap(SpaceRequests space) {
		Map<String, Object> param = new HashMap<>();

		if (space.getRoom_id() != 0) {
			param.put("room_id", space.getRoom_id());
		} else {
			logger.error("Room id missing");
			throw new NullPointerException("room_id cannot be null");
		}
		param.put("space_request_id", space.getSpace_request_id());
		param.put("room_id", space.getRoom_id());
		param.put("event_id", space.getEvent_id());
		param.put("booking_status_code", space.getBooking_status_code());
		param.put("occupancy", space.getOccupancy());
		param.put("commercial_or_free", space.getCommercial_or_free());
		param.put("occupancy_date_from", space.getOccupancy_date_from());
		param.put("occupancy_date_to", space.getOccupancy_date_to());
		param.put("other_details", space.getOther_details());
		return param;

	}

}
