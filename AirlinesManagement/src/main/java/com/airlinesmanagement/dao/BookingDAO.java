package com.airlinesmanagement.dao;

import com.airlinesmanagement.model.Booking;

public interface BookingDAO {
    public int insertBooking(Booking booking);
    public int deleteBooking(int bookingid);
    public Booking displayBooking(int bookingid);
}
