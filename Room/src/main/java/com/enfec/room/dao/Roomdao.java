package com.enfec.room.dao;

import java.util.List;

import com.enfec.room.model.Room;

public interface Roomdao {
	public int saveRoom(Room r);

	public int updateRoom(Room r);

	public int deleteRoom(int id);

	public List<Room> getRoomDetails(Integer Id);

}
