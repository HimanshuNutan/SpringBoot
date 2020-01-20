package com.airlinesmanagement.service;



import com.airlinesmanagement.dao.BookingDAO;
import com.airlinesmanagement.dao.UserDAO;
import com.airlinesmanagement.model.Booking;
import com.airlinesmanagement.model.Flight;
import com.airlinesmanagement.model.User;
import com.airlinesmanagement.util.FlightUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
@PropertySource("classpath:application.properties")
@Service("AirManService")
public class AirManService {

    @Autowired
    FlightUtil flightUtil;
    Logger logger = LogManager.getLogger(AirManService.class.getName());
    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public int insertUser(User user){return userDAO.insertUser(user);}

    public boolean  checkCredentials(User user){return userDAO.checkCredentials(user);}

    @Transactional
    public int updateUser(User user){return userDAO.updateUser(user);}

    @Transactional
    public int deleteUser(int userid){return userDAO.deleteUser(userid);}

    public List<User> showAllUsers(){return userDAO.showAllUsers();}

    public User showUserDetails(int userid){return  userDAO.showUserDetails(userid);}

    @Transactional
    public int insertBooking(Booking booking){
        int response = 0;
        String flightUrl = "http://localhost:8082/flight/getFlightDetails?flightid="+booking.getFlightid();
        Flight flight = new Flight();
        try{
            RestTemplate template = new RestTemplate();
            flight =template.getForObject(flightUrl,Flight.class);
        }catch (Exception e){
            logger.error("Error in getting Flight Details",e);
        }
        if(flight.getAvailableseats()>=booking.getSeatsbooked()){
        response = bookingDAO.insertBooking(booking);
if(response == 1){updateAvailableSeats(booking);}
        }
        else{
            response = -1;
        }
        return response;
    }

    @Transactional
    public int deleteBooking(int bookingid){return bookingDAO.deleteBooking(bookingid);}

    public Booking displayBooking(int bookingid){return bookingDAO.displayBooking(bookingid);}

    public Flight getAllFlights(){
        Flight flight = null;
        String flightUrl ="http://localhost:8082/flight/getAllFlights";
        try {
            RestTemplate template = new RestTemplate();
            String result = template.getForObject(flightUrl, String.class);
            flight = flightUtil.convertStringtoObject(result);
        }catch (Exception e){
        logger.error("Error in getting all Flight Details ",e);
        }
        return flight;
    }

    public Flight getFlightById(int flightid){
        Flight flight = null;
        RestTemplate template = new RestTemplate();
        String flightUrl ="http://localhost:8082/flight/getFlightDetails?flightid="+flightid;
        flight = template.getForObject(flightUrl,Flight.class);
        return flight;
    }

    public void addFlight(){
        RestTemplate template = new RestTemplate();
        String flightUrl = "http://localhost:8082/flight/addFlight";
        Flight flight = new Flight();
        flight.setArrivalcity("Pune");
        flight.setArrivaltime("11:00");
        flight.setAvailableseats(110);
        flight.setDeparturecity("Bangalore");
        flight.setDeparturetime("10:00");
        flight.setSeathighprice(2500);
        flight.setSeatlowprice(1200);
        flight.setSeatmediumprice(1900);
        flight.setAvailableseats(110);
        flight.setTotalseats(110);
        try {
            org.springframework.http.HttpHeaders header=new HttpHeaders();
            HttpEntity<Flight> entity=new HttpEntity<Flight>(flight,header);
            URI uri = new URI(flightUrl);
            ResponseEntity<Integer> entity1 =template.postForEntity(uri,entity,Integer.class);
            System.out.println(entity1);
        }catch (Exception e){
            logger.error("Error in adding Flight",e);
        }
    }

    public int updateAvailableSeats(Booking booking){
    int response = 0;
        RestTemplate template = new RestTemplate();
        String flighturl = "http://localhost:8082/flight/updateSeats";
    try {
        HttpHeaders header = new HttpHeaders();
        HttpEntity<Booking> entity = new HttpEntity<>(booking, header);
        URI uri = new URI(flighturl);
        ResponseEntity<Integer> entity1 = template.postForEntity(uri,entity,Integer.class);
        System.out.println(entity1);
    }catch (Exception e){logger.error("Error in sending seat updation details",e);}

    return response;
    }





}
