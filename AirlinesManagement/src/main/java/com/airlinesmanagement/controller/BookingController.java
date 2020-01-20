package com.airlinesmanagement.controller;


import com.airlinesmanagement.model.Booking;
import com.airlinesmanagement.model.User;
import com.airlinesmanagement.service.AirManService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private AirManService airManService;
    private static final Logger LOGGER =
            LogManager.getLogger(BookingController.class.getName());


    // http://localhost:8080/booking/getDetails
    @RequestMapping(value = "/getDetails",method = RequestMethod.GET)
    public Booking displayBooking(int bookingid){
        Booking booking = new Booking();
        try{
            booking = airManService.displayBooking(bookingid);
            LOGGER.info("Booking Details Fetched");
        }
        catch (Exception e){
            LOGGER.error("Error while getting booking details",e);
        }
        return booking;
        }

    // http://localhost:8080/booking/add
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public int insertBooking(@RequestBody Booking booking){
        int response = 0;
        try{
            response = airManService.insertBooking(booking);
            System.out.println(booking);
        }catch (Exception e){
            LOGGER.error("Error occured while booking",e);
        }
        return response;
    }

    // http://localhost:8080/booking/

    @RequestMapping(value = "/delete/{bookingid}",method = RequestMethod.DELETE)
    public int deleteBooking(@PathVariable("bookingid") int bookingid){
        int response = 0;
        LOGGER.info("Deleting booking with booking id "+bookingid);
        try{
            response = airManService.deleteBooking(bookingid);
        }
        catch (Exception e){
            LOGGER.error("Could not delete booking");
        } return response;
    }

}
