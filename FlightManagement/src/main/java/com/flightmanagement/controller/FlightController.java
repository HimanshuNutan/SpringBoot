package com.flightmanagement.controller;


import com.flightmanagement.model.Booking;
import com.flightmanagement.model.Flight;
import com.flightmanagement.service.FliManService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FliManService fliManService;
    private static final Logger logger =
            LogManager.getLogger(FlightController.class.getName());

    //http://localhost:8082/flight/getAllFlights
    @RequestMapping(value = "/getAllFlights",method = RequestMethod.GET)
   public List<Flight> getAllFlights(){
     List<Flight> flightList = new ArrayList<>();

     try{
          flightList = fliManService.getAllFlights();
     }catch (Exception e){
         logger.error("Error showing Flights",e);
     }
     return flightList;
   }
    //http://localhost:8082/flight/getFlightDetails
   @RequestMapping(value = "/getFlightDetails",method = RequestMethod.GET)
   public Flight getFlightDetails(@RequestParam("flightid") int flightid){
        Flight flight = new Flight();
        try{
            flight = fliManService.getFlight(flightid);
            System.out.println(flight);
        }catch (Exception e){
            logger.error("Error getting Flight Details",e);
        }
        return flight;
   }
     //http://localhost:8082/flight/addFlight
     /*{
         "departurecity":"Bengaluru",
             "arrivalcity":"New Delhi",
             "departuretime":"10:00",
             "arrivaltime":"12:45",
             "seatlowprice":"3300",
             "seatmediumprice":"3900",
             "seathighprice":"4800",
             "totalseats":"120",
             "availableseats":"120"
     };*/
   @RequestMapping(value = "/addFlight",method = RequestMethod.POST)
   public int addFlight(@RequestBody Flight flight){
        int response = 0;
        try{
            response=fliManService.insertFlight(flight);
        }catch (Exception e){
            logger.error("Error adding Flight",e);
        }
return response;
   }
    //http://localhost:8082/flight/updateFlight
   @RequestMapping(value = "/updateFlight",method = RequestMethod.PUT)
    public int updateFlight(@RequestBody Flight flight){
        int response = 0;
        try{
            response = fliManService.updateFlight(flight,flight.getFlightid());
        }catch (Exception e){
            logger.error("Error updating Flight",e);
        }
        return response;
   }

    //http://localhost:8082/flight/deleteFlight/id
   @RequestMapping(value = "/deleteFlight/{flightid}",method = RequestMethod.DELETE)
    public int deleteFlight(@PathVariable("flightid") int flightid){
        int response= 0;
        try{
            response = fliManService.deleteFlight(flightid);
        }catch (Exception e){
            logger.error("Error deleting flight",e);
        }
return response;
   }
    //http://localhost:8082/flight/updateSeats
    @RequestMapping(value = "/updateSeats",method = RequestMethod.PUT)
    public int updateAvailableSeats(@RequestBody Booking booking){
  //  public int updateAvailableSeats(@RequestParam("flightid") int flightid,@RequestParam("numberofseats") int numberofseats){
        int response = 0;
        try{

      response = fliManService.updateAvailableSeats(booking.getFlightid(),booking.getSeatsbooked());
        }catch (Exception e){
        }
      return response;
    }
}
