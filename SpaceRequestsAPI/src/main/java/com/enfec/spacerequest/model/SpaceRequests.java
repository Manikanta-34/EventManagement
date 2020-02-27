package com.enfec.spacerequest.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceRequests {
	private int space_request_id;
	private int room_id;
	private int event_id;
	private int booking_status_code;
	private int occupancy;
	private String commercial_or_free;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp occupancy_date_from;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp occupancy_date_to;
	private String other_details;

}
