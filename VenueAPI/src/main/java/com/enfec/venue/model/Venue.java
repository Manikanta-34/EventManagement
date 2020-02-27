package com.enfec.venue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venue {
	private int venue_id;
	private String venue_name;
	private int venue_address_id;
	private String street;
	private String city;
	private String state;
	private String country;
	private int zipcode;
	private String other_details;

}
