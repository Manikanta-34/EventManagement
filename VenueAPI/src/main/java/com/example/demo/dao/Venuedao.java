package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Venue;


public interface  Venuedao {
	public int saveVenue(Venue v);

	public int updateVenue(Venue v);

	public int deleteVenue(int id);

	public List<Venue> getVenueDetails(Integer venue_id);

}
