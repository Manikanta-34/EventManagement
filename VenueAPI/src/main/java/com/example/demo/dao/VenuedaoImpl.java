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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Venue;
import com.example.demo.model.VenueRowMapper;

@Component
@Transactional
public class VenuedaoImpl implements Venuedao {
	private static final Logger logger = LoggerFactory.getLogger(VenuedaoImpl.class);
	final String INSERT_VENUE = "insert into Venues(Venue_Name,Other_Details)values(:venue_name,:other_details)";
	final String INSERT_VENUE_ADDRESS = "insert into Venue_Address_Table(Venue_Id,Street,City,State,Country,Zipcode)"
			+ "values(:venue_id,:street,:city,:state,:country,:zipcode)";

	final String GET_DETAILS = "select * from Venues v  join Venue_Address_Table a on v.Venue_Id=a.Venue_Id where v.Venue_Id=?";
	final String UPDATE_DETAILS = "update Venues v join Venue_Address_Table a on v.Venue_Id=a.Venue_Id set v.Venue_Name=:venue_name,v.Other_Details=:other_details,"
			+ "        a.Street=:street,a.City=:city,a.State=:state,a.Country=:country,a.Zipcode=:zipcode where v.Venue_Id=:venue_id";
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveVenue(Venue v) {
		int affectedRow;
		KeyHolder keyholder = new GeneratedKeyHolder();
		Map<String, Object> param = VenueInfoMap(v);

		SqlParameterSource pramSource = new MapSqlParameterSource(param);
		logger.info("Register Venue Info : {} ", pramSource);
		affectedRow = namedJdbcTemplate.update(INSERT_VENUE, pramSource, keyholder);
		Number Venue_id = keyholder.getKey();
		param.put("venue_id", Venue_id);
		SqlParameterSource pramSource1 = new MapSqlParameterSource(param);
		affectedRow = namedJdbcTemplate.update(INSERT_VENUE_ADDRESS, pramSource1);
		return affectedRow;
	}

	private Map<String, Object> VenueInfoMap(Venue venue) {
		Map<String, Object> param = new HashMap<>();

		if (venue.getVenue_name() != null) {
			param.put("venue_name", venue.getVenue_name());
		} else {
			logger.error("venue name missing");
			throw new NullPointerException("venue_name cannot be null");
		}
		param.put("venue_id", venue.getVenue_id());
		param.put("venue_name", venue.getVenue_name());
		param.put("other_details", venue.getOther_details());
		param.put("street", venue.getStreet());
		param.put("city", venue.getCity());
		param.put("state", venue.getState());
		param.put("country", venue.getCountry());
		param.put("zipcode", venue.getZipcode());

		return param;
	}

	@Override
	public int deleteVenue(int id) {
		int c;
		String sql1 = "delete from Events where Venue_Id=?";
		String sql2 = "delete Venues,Venue_Address_Table from Venue_Address_Table join  Venues on Venues.Venue_Id=Venue_Address_Table.Venue_Id where Venue_Address_Table.Venue_Id=?";
		c = jdbcTemplate.update(sql1, id);
		c = jdbcTemplate.update(sql2, id);

		return c;
	}

	@Override
	public List<Venue> getVenueDetails(Integer venue_id) {
		return jdbcTemplate.query(GET_DETAILS, new Object[] { venue_id }, new VenueRowMapper());

	}

	@Override
	public int updateVenue(Venue venue) {

		int affectedRow;
		Map<String, Object> param = VenueInfoMap(venue);

		SqlParameterSource pramSource = new MapSqlParameterSource(param);
		logger.info("Updating Room Info : {} ", pramSource);
		affectedRow = namedJdbcTemplate.update(UPDATE_DETAILS, pramSource);

		return affectedRow;
	}

}
