package com.example.demo.service.dao;

import java.util.List;
import com.example.demo.model.Room;

public interface Roomdao {
	public int saveRoom(Room r);

	public int updateRoom(Room r);

	public int deleteRoom(int id);

	public List<Room> getRoomDetails(Integer Id);

}
