package com.enfec.room.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enfec.room.dao.Roomdao;
import com.enfec.room.model.Room;
@Service
public class RoomService {
	@Autowired
	private Roomdao dao;

	public int save(Room r) {
		int count = dao.saveRoom(r);
		return count;
	}

	public List<Room> getDetails(Integer id) {

		List<Room> c = dao.getRoomDetails(id);
		return c;
	}

	public int update(Room r) {
		int c = dao.updateRoom(r);
		return c;

	}

	public int delete(int id) {
		int c = dao.deleteRoom(id);
		return c;
	}

}
