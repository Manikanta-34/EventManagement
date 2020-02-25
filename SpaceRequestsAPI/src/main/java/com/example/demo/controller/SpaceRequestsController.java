package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.SpaceRequestsdao;

import com.example.demo.model.SpaceRequests;
import com.google.gson.Gson;

@RestController
public class SpaceRequestsController {
	private static final Logger logger = LoggerFactory.getLogger(SpaceRequestsController.class);
	@Autowired
	private SpaceRequestsdao dao;

	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody(required = true) SpaceRequests s) {
		try {
			int affectedRow = dao.saveSpaceRequests(s);

			if (affectedRow == 0) {
				logger.info("SpaceRequest not available room_id: {} ", s.getRoom_id());
				return new ResponseEntity<>("{\"message\" : \"No Space\"}", HttpStatus.OK);
			} else if (affectedRow == 0) {
				logger.info("SpaceRequest not available room_id: {} ", s.getEvent_id());
				return new ResponseEntity<>("{\"message\" : \"No Space\"}", HttpStatus.OK);
			} else {
				logger.info("SpaceRequest Available room_id: {} ", s.getRoom_id());
				return new ResponseEntity<>("{\"message\" : \"Space Available\"}", HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			logger.error("Invalid room_id: {} ", s.getRoom_id());
			return new ResponseEntity<>("{\"message\" : \"Invalid details\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			logger.error("Exception in Booked room :{}", exception.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in Booking room info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getDetails/{space_Request_id}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> get(@PathVariable("space_Request_id") Integer space_Request_id) {
		try {
			List<SpaceRequests> space = dao.getSpaceRequestsDetails(space_Request_id);
			if (space.isEmpty()) {
				logger.info("No data found for: {} ", space_Request_id);
				return new ResponseEntity<>("{\"message\" : \"No Data found\"}", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new Gson().toJson((dao.getSpaceRequestsDetails(space_Request_id))),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Exception in getting  info: {} ", e.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in getting  info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(value = "/update")
	public ResponseEntity<String> put(@RequestBody(required = true) SpaceRequests space) {
		try {
			int affectedRow = dao.updateSpaceRequests(space);

			if (affectedRow == 0) {
				logger.info("spaceRequests not updated space_Requests_id: {} ", space.getSpace_request_id());
				return new ResponseEntity<>("{\"message\" : \"space_Requests_id not found\"}", HttpStatus.OK);
			} else {
				logger.info("spaceRequests updated space_Requests_id: {} ", space.getSpace_request_id());
				return new ResponseEntity<>("{\"message\" : \"spaceRequests updated\"}", HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			logger.error("Invalid space_Requests_id: {} ", space.getSpace_request_id());
			return new ResponseEntity<>("{\"message\" : \"Invalid details\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			logger.error("Exception in updating space_Requests :{}", exception.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in registering Room info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/deleteSpace/{space_requests_id}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> delete(@PathVariable("space_requests_id") int space_requests_id) {
		int rows = dao.deleteSpaceRequests(space_requests_id);
		if (rows > 0)
			return new ResponseEntity<>("{\"message\" : \"space_requests_id Details deleted\"}", HttpStatus.OK);

		else
			return new ResponseEntity<>("{\"message\" : \"space_requests_id not found\"}", HttpStatus.BAD_REQUEST);

	}

}
