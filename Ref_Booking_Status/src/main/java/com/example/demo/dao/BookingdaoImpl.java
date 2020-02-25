package com.example.demo.dao;

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

import com.example.demo.model.Booking;
import com.example.demo.model.BookingRowMapper;


@Component
@Transactional
public class BookingdaoImpl implements Bookingdao {
	private static final Logger logger = LoggerFactory.getLogger(BookingdaoImpl.class);
	final String GET_DETAILS = "select * from Ref_Booking_Status where Booking_Status_Code=?";
	final String INSERT_DETAILS = "insert into Ref_Booking_Status(Booking_Status_Code,Booking_Status_Description)values(:booking_status_code,:booking_status_description)";
	final String UPDATE_DETAILS="update Ref_Booking_Status set Booking_Status_Description=:booking_status_description where Booking_Status_Code=:booking_status_code";
	final String DELETE_DETAILS="delete from Ref_Booking_Status where Booking_Status_Code=?";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public int saveBooking(Booking b) {
		int affectedRow;
		Map<String, Object> param = BookingInfoMap(b);

		SqlParameterSource pramSource = new MapSqlParameterSource(param);
		logger.info("Register  Info : {} ", pramSource);
		affectedRow = namedJdbcTemplate.update(INSERT_DETAILS, pramSource);
		return affectedRow;

	}

	@Override
	public int updateBooking(Booking b) {
		int affectedRow;
		Map<String, Object> param = BookingInfoMap(b);

		SqlParameterSource pramSource = new MapSqlParameterSource(param);
		logger.info("Register  Info : {} ", pramSource);
		affectedRow = namedJdbcTemplate.update(UPDATE_DETAILS, pramSource);
		return affectedRow;

	}

	@Override
	public int deleteBooking(int Booking_status_code) {
		return jdbcTemplate.update(DELETE_DETAILS,Booking_status_code);
		
	}

	@Override
	public List<Booking> getBookingDetails(Integer Booking_status_code) {
		return jdbcTemplate.query(GET_DETAILS, new Object[] { Booking_status_code }, new BookingRowMapper());

	}

	private Map<String, Object> BookingInfoMap(Booking b) {
		Map<String, Object> param = new HashMap<>();

		if (b.getBooking_status_code() != 0) {
			param.put("booking_status_code", b.getBooking_status_code());
		} else {
			logger.error("booking_status_code missing");
			throw new NullPointerException("booking_status_code cannot be null");
		}

		param.put("booking_status_code", b.getBooking_status_code());
		param.put("booking_status_description", b.getBooking_status_description());

		return param;

	}

}
