package com.example.demo.dao;
import java.util.List;
import com.example.demo.model.Booking;

public interface Bookingdao {
	public int saveBooking(Booking b);

	public int updateBooking(Booking b);

	public int deleteBooking(int Booking_status_code);

	public List<Booking> getBookingDetails(Integer Booking_status_code);

}
