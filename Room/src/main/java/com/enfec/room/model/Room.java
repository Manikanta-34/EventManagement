package com.enfec.room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
	private int room_id;
	private int venue_id;
	private String room_name;
	private int room_capability;
	private double rate_for_day;
	private String venue_name;
	private int venue_address_id;
	private String city;
	private String state;
	private String country;
	private int zipcode;
	private String street;
	private String other_details;
	

}
