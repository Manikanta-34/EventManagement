package com.enfec.venue.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.enfec.venue.dao.VenuedaoImpl;
import com.enfec.venue.model.Venue;
import com.enfec.venue.service.VenueService;
import com.google.gson.Gson;

@RestController

public class VenueController {
	private static final Logger logger = LoggerFactory.getLogger(VenueController.class);
	@Autowired
	private VenueService service;
	@Autowired
	private VenuedaoImpl venuedaoImpl;

	/*
	 * Creating a New Venue
	 * 
	 * @param Venue
	 * 
	 * @return Message wrote in ResponseEntity.
	 * 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody(required = true) Venue v) {
		try {
			int affectedRow = service.save(v);

			if (affectedRow == 0) {
				logger.info("Venue not created venue_id: {} ", v.getVenue_id());
				return new ResponseEntity<>("{\"message\" : \"Venue not created\"}", HttpStatus.OK);
			} else {
				logger.info("Venue  created venue_id: {} ", v.getVenue_id());
				return new ResponseEntity<>("{\"message\" : \"Venue created\"}", HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			logger.error("Invalid venue_id: {} ", v.getVenue_id());
			return new ResponseEntity<>("{\"message\" : \"venue_Id is already created\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			logger.error("Exception in Booked room :{}", exception.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in Booking room info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*
	 * Getting the details of venue based on Venue_id
	 * 
	 * @param venue_id
	 * 
	 * @return ResponseEntity message .
	 */

	@RequestMapping(value = "/getDetails/{venue_id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> get(@PathVariable("venue_id") Integer venue_id) {

		try {

			List<Venue> r = venuedaoImpl.getVenueDetails(venue_id);

			if (r.isEmpty()) {
				logger.info("No data found for: {} ", venue_id);
				return new ResponseEntity<>("{\"message\" : \"No Venue found\"}", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new Gson().toJson((service.getDetails(venue_id))), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Exception in getting Venue info: {} ", e.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in getting Venue info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*
	 * Updating the Venue details
	 * 
	 * @param venue
	 * 
	 * @return ResponseEntity message.
	 */

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> put(@RequestBody(required = true) Venue venue) {
		try {
			int affectedRow = service.update(venue);

			if (affectedRow == 0) {
				logger.info("Venue not updated venue_id: {} ", venue.getVenue_id());
				return new ResponseEntity<>("{\"message\" : \"Venue not found\"}", HttpStatus.OK);
			} else {
				logger.info("Venue updated venue_id: {} ", venue.getVenue_id());
				return new ResponseEntity<>("{\"message\" : \"Venue updated\"}", HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			logger.error("Invalid venue_id: {} ", venue.getVenue_id());
			return new ResponseEntity<>("{\"message\" : \"Invalid venue_id\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			logger.error("Exception in updating Venue :{}", exception.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in registering Venue info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*
	 * Deleting existing venue Details
	 * 
	 * @param venue_id
	 * 
	 * @return ResponseEntity message
	 */

	@RequestMapping(value = "/delete/{venue_id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteVenue(@PathVariable("venue_id") int venue_id) {
		int count = service.delete(venue_id);
		if (count > 0) {
			return new ResponseEntity<>("{\"message\" : \"Venue Details deleted\"}", HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>("{\"message\" : \"No Venue details found\"}", HttpStatus.BAD_REQUEST);
		}

	}

}
