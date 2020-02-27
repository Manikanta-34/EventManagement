package com.enfec.bookstatus.dao;
import java.util.List;

import com.enfec.bookstatus.model.Booking;

public interface Bookingdao {
	public int saveBooking(Booking b);

	public int updateBooking(Booking b);

	public int deleteBooking(int booking_status_code);

	public List<Booking> getBookingDetails(Integer booking_status_code);

}
