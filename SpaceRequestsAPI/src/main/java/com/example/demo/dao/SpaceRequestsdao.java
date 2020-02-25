package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.SpaceRequests;



public interface SpaceRequestsdao {
	public int saveSpaceRequests(SpaceRequests s);

	public int updateSpaceRequests(SpaceRequests s);

	public int deleteSpaceRequests(int space_requests_id);

	public List<SpaceRequests> getSpaceRequestsDetails(Integer Id);

}
