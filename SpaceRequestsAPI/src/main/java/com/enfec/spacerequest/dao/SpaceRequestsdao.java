package com.enfec.spacerequest.dao;

import java.util.List;

import com.enfec.spacerequest.model.SpaceRequests;



public interface SpaceRequestsdao {
	public int saveSpaceRequests(SpaceRequests s);

	public int updateSpaceRequests(SpaceRequests s);

	public int deleteSpaceRequests(int space_requests_id);

	public List<SpaceRequests> getSpaceRequestsDetails(Integer Id);

}
