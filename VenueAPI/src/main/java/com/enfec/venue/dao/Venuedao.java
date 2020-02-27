package com.enfec.venue.dao;

import java.util.List;

import com.enfec.venue.model.Venue;


public interface  Venuedao {
	public int saveVenue(Venue v);

	public int updateVenue(Venue v);

	public int deleteVenue(int id);

	public List<Venue> getVenueDetails(Integer venue_id);

}
