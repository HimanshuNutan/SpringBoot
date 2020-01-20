package com.airlinesmanagement.dao;

import com.airlinesmanagement.model.Booking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository("bookingdao")
public class BookingDAOImpl implements BookingDAO{
    // DATA MEMBERS
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER =
             LogManager.getLogger(BookingDAO.class.getName());



            public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

           //Function to add a new Booking
            public int insertBooking(Booking booking){
                LOGGER.info("Adding a new Booking"+booking.toString());
                int response = 0;
                try {
                    String query = "INSERT INTO booking(userid,dateofbooking,dateofflight,flightdetails,seatsbooked,amountpaid,paymentmethod,flightid) values(?,?,?,?,?,?,?,?)";
                     response = jdbcTemplate.update(query,booking.getUserid(),booking.getDateofbooking(),booking.getDateofflight(),booking.getFlightdetails(),booking.getSeatsbooked(),booking.getAmountpaid(),booking.getPaymentmethod(),booking.getFlightid());
                     LOGGER.info("Booking added");
                }catch (Exception e){
                    LOGGER.error("Error occured while creating the booking",e);
                }
            return  response;
            }


            //Function to delete a Booking
            public int deleteBooking(int bookingid){
                int response = 0;
                LOGGER.info("Deleting the booking with id "+bookingid);
                try {
                    String query = "DELETE from booking where bookingid = ?";
                    response = jdbcTemplate.update(query, bookingid);
                    LOGGER.info("Booking deleted");
                }catch (Exception e){
                    LOGGER.error("Error occured while deleting the booking",e);
                }
               return response;
            }


            //Function to display a Booking's Details
            public Booking displayBooking(int bookingid){
               String query = "select * from booking where bookingid = ?";
               LOGGER.info("Getting details for booking id "+bookingid);
               return jdbcTemplate.queryForObject(query, new Object[]{bookingid}, new RowMapper<Booking>() {
                   @Override
                   public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
                       Booking booking = new Booking();
                       try{
                           booking.setBookingid(rs.getInt("bookingid"));
                           booking.setAmountpaid(rs.getDouble("amountpaid"));
                           booking.setDateofbooking(rs.getString("dateofbooking"));
                           booking.setDateofflight(rs.getString("dateofflight"));
                           booking.setFlightdetails(rs.getString("flightdetails"));
                           booking.setPaymentmethod(rs.getString("paymentmethod"));
                           booking.setSeatsbooked(rs.getInt("seatsbooked"));
                       }
                       catch (Exception e){
                           LOGGER.error("Error occured while getting booking details",e);
                       }
                       return booking;
                   }
               });


            }



}
