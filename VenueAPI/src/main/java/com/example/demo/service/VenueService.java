package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Venuedao;

import com.example.demo.model.Venue;
@Service
public class VenueService {
	@Autowired
	private Venuedao dao;
	public int save(Venue v) {
		int count = dao.saveVenue(v);
		return count;
	}

	public List<Venue> getDetails(Integer venue_id) {

		List<Venue> c = dao.getVenueDetails(venue_id);
		return c;
	}
	public int delete(int id) {
		int c = dao.deleteVenue(id);
		return c;
	}
	public int update(Venue v) {
		int c = dao.updateVenue(v);
		return c;

	}

}
