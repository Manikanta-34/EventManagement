package com.enfec.bookstatus.controller;

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

import com.enfec.bookstatus.dao.Bookingdao;
import com.enfec.bookstatus.model.Booking;
import com.google.gson.Gson;

@RestController
public class BookingController {
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	@Autowired
	private Bookingdao dao;

	/*
	 * Getting the details of BookingStatusAPI based on booking_status_code
	 * 
	 * @param booking_status_code
	 * 
	 * @return ResponseEntity message.
	 */

	@GetMapping(value = "/getDetails/{booking_status_code}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> get(@PathVariable("booking_status_code") Integer booking_status_code) {
		try {
			List<Booking> book = dao.getBookingDetails(booking_status_code);
			if (book.isEmpty()) {
				logger.info("No data found for: {} ", booking_status_code);
				return new ResponseEntity<>("{\"message\" : \"No Details found\"}", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new Gson().toJson((dao.getBookingDetails(booking_status_code))),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Exception in getting  info: {} ", e.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in getting  info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Creating BookingStatusAPI
	 * 
	 * @param Booking
	 * 
	 * @return ResponseEntity Message.
	 * 
	 */

	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody(required = true) Booking b) {
		try {
			int affectedRow = dao.saveBooking(b);

			if (affectedRow == 0) {
				logger.info("getBooking_status_code: {} ", b.getBooking_status_code());
				return new ResponseEntity<>("{\"message\" : \"Invalid Deatils\"}", HttpStatus.OK);
			} else {
				logger.info("Details Entered getBooking_status_code: {} ", b.getBooking_status_code());
				return new ResponseEntity<>("{\"message\" : \"Details Successfully Entered \"}", HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			logger.error("Invalid getBooking_status_code: {} ", b.getBooking_status_code());
			return new ResponseEntity<>("{\"message\" : \"Invalid getBooking_status_code\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			logger.error("Exception in Booked  :{}", exception.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in Booking  info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Updating the BookingStatusAPI details
	 * 
	 * @param Booking
	 * 
	 * @return ResponseEntity message.
	 */

	@PutMapping(value = "/update")
	public ResponseEntity<String> put(@RequestBody(required = true) Booking b) {
		try {
			int affectedRow = dao.updateBooking(b);

			if (affectedRow == 0) {
				logger.info("getBooking_status_code: {} ", b.getBooking_status_code());
				return new ResponseEntity<>("{\"message\" : \"Invalid Deatils\"}", HttpStatus.OK);
			} else {
				logger.info("Details Entered getBooking_status_code: {} ", b.getBooking_status_code());
				return new ResponseEntity<>("{\"message\" : \"Details Updated Successfully Entered \"}", HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			logger.error("Invalid getBooking_status_code: {} ", b.getBooking_status_code());
			return new ResponseEntity<>("{\"message\" : \"Invalid Booking_status_code\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			logger.error("Exception in Booked  :{}", exception.getMessage());
			return new ResponseEntity<>("{\"message\" : \"Exception in Booking  info\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Deleting existing BookingStatusAPI Details
	 * 
	 * @param booking_staus_code
	 * 
	 * @return ResponseEntity message
	 */

	@DeleteMapping(value = "/deleteBooking/{booking_staus_code}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> delete(@PathVariable("booking_staus_code") int booking_staus_code) {
		int rows = dao.deleteBooking(booking_staus_code);
		if (rows > 0)
			return new ResponseEntity<>("{\"message\" : \"booking_staus_code Details deleted\"}", HttpStatus.OK);

		else
			return new ResponseEntity<>("{\"message\" : \"booking_staus_code not found\"}", HttpStatus.BAD_REQUEST);

	}

}
