package com.enfec.room.dao;

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

import com.enfec.room.model.Room;
import com.enfec.room.model.RoomRowMapper;

@Component
@Transactional

public class RoomdaoImpl implements Roomdao {
	private static final Logger logger = LoggerFactory.getLogger(RoomdaoImpl.class);
	final String INSERT_ROOM = "insert into Rooms (Venue_Id,Room_Name,Room_Capability,Rate_For_Day) values(:venue_id,:room_name,:room_capability,:rate_for_day)";
	final String GetDetails = "select * from Rooms r join Venues v on r.Venue_Id=v.Venue_Id join Venue_Address_Table a on v.Venue_Id=a.Venue_Id where Room_Id=?";
	final String DELETE_ROOM = "delete from Rooms where Room_Id=?";
	final String UPDATE_ROOM = "update Rooms set Room_Name=:room_name,Room_Capability=:room_capability,Rate_For_Day=:rate_for_day,Venue_id=:venue_id where Room_Id=:room_id";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	/*
	 * Creating a New Room
	 * 
	 * @param Room
	 * 
	 * @return affectedRow.
	 * 
	 */

	@Override
	public int saveRoom(Room r) {

		int affectedRow;
		Map<String, Object> param = RoomInfoMap(r);

		SqlParameterSource pramSource = new MapSqlParameterSource(param);
		logger.info("Register Room Info : {} ", pramSource);
		affectedRow = namedJdbcTemplate.update(INSERT_ROOM, pramSource);
		return affectedRow;
	}

	/*
	 * Updating the Room details
	 * 
	 * @param room
	 * 
	 * @return affectedRow
	 */

	@Override
	public int updateRoom(Room room) {
		int affectedRow;
		Map<String, Object> param = RoomInfoMap(room);

		SqlParameterSource pramSource = new MapSqlParameterSource(param);
		logger.info("Updating Room Info : {} ", pramSource);
		affectedRow = namedJdbcTemplate.update(UPDATE_ROOM, pramSource);

		return affectedRow;
	}

	/*
	 * Deleting existing Room Details
	 * 
	 * @param room_id
	 * 
	 * @return
	 */

	@Override
	public int deleteRoom(int room_id) {
		return jdbcTemplate.update(DELETE_ROOM, room_id);

	}

	/*
	 * Getting the details of Room based on room_id
	 * 
	 * @param room_id
	 * 
	 * @return list of Room Details.
	 */

	@Override
	public List<Room> getRoomDetails(Integer room_id) {

		return jdbcTemplate.query(GetDetails, new Object[] { room_id }, new RoomRowMapper());

	}

	private Map<String, Object> RoomInfoMap(Room room) {
		Map<String, Object> param = new HashMap<>();

		if (room.getRoom_name() != null) {
			param.put("room_name", room.getRoom_id());
		} else {
			logger.error("Room name missing");
			throw new NullPointerException("room_name cannot be null");
		}

		param.put("room_id", room.getRoom_id());
		param.put("venue_id", room.getVenue_id());
		param.put("room_name", room.getRoom_name());
		param.put("room_capability", room.getRoom_capability());
		param.put("rate_for_day", room.getRate_for_day());
		return param;

	}

}
