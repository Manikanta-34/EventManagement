package com.enfec.room.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enfec.room.model.Room;
import com.enfec.room.service.RoomService;
import com.google.gson.Gson;

@RestController
public class RoomController {
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
	@Autowired
	private RoomService service;

	/*
	 * Creating a New Room
	 * 
	 * @param Room
	 * 
	 * @return ResponseEntity Message.
	 * 
	 */

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody(required = true) Room r) {
		try {
			int affectedRow = service.save(r);

			if (affectedRow == 0) {
				logger.info("Room not Booked room_id: {} ", r.getRoom_id());
				return new ResponseEntity<>("{\"message\" : \"Room not Booked\"}", HttpStatus.OK);
			} else {
				logger.info("Room Booked room_id: {} ", r.getRoom_id());
				return new ResponseEntity<>("{\"message\" : \"Room Booked\"}", HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			logger.error("Invalid room_id: {} ", r.getRoom_id());
			return new ResponseEntity<>("{\"message\" : \"Invalid venue_id\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			logger.error("Exception in Booked room :{}", exception.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in Booking room info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Getting the details of Room based on room_id
	 * 
	 * @param room_id
	 * 
	 * @return ResponseEntity message.
	 */

	@GetMapping(value = "/getRoomDetails/{room_id}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> get(@PathVariable("room_id") Integer room_id) {
		try {
			List<Room> room = service.getDetails(room_id);
			if (room.isEmpty()) {
				logger.info("No data found for: {} ", room_id);
				return new ResponseEntity<>("{\"message\" : \"No Room found\"}", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new Gson().toJson((service.getDetails(room_id))), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Exception in getting Room info: {} ", e.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in getting Room info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	/*
	 * Updating the Room details
	 * 
	 * @param Room
	 * 
	 * @return ResponseEntity message.
	 */

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> put(@RequestBody(required = true) Room room) {
		try {
			int affectedRow = service.update(room);

			if (affectedRow == 0) {
				logger.info("Room not updated room_id: {} ", room.getRoom_id());
				return new ResponseEntity<>("{\"message\" : \"Room not found\"}", HttpStatus.OK);
			} else {
				logger.info("Room updated room_id: {} ", room.getRoom_id());
				return new ResponseEntity<>("{\"message\" : \"Room updated\"}", HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			logger.error("Invalid venue_id: {} ", room.getVenue_id());
			return new ResponseEntity<>("{\"message\" : \"Invalid venue_id\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			logger.error("Exception in updating Room :{}", exception.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in registering Room info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Deleting existing Room Details
	 * 
	 * @param room_id
	 * 
	 * @return ResponseEntity message
	 */
	@DeleteMapping(value = "/deleteRoom/{room_id}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> delete(@PathVariable("room_id") int room_id) {
		int rows = service.delete(room_id);
		if (rows > 0)
			return new ResponseEntity<>("{\"message\" : \"Room Details deleted\"}", HttpStatus.OK);

		else
			return new ResponseEntity<>("{\"message\" : \"No Room details found\"}", HttpStatus.BAD_REQUEST);

	}

}
