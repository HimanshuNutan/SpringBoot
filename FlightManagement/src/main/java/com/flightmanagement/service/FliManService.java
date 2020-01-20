package com.flightmanagement.service;

import com.flightmanagement.DAO.FlightDAO;
import com.flightmanagement.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("FliManService")
public class FliManService {

    @Autowired
    private FlightDAO flightDAO;
    @Transactional
    public int insertFlight(Flight flight){
     return flightDAO.insertFlight(flight);
    }
    @Transactional
    public int updateFlight(Flight flight,int flightid){
        return flightDAO.updateFlight(flight,flightid);
    }
    @Transactional
    public int deleteFlight(int flightid){
        return flightDAO.deleteFlight(flightid);
    }

    public Flight getFlight(int flightid){
        return flightDAO.getFlight(flightid);
    }
    public List<Flight> getAllFlights(){
        return flightDAO.getAllFlights();
    }
    @Transactional
    public int updateAvailableSeats(int flightid,int seatsbooked){
        return flightDAO.updateAvailableSeats(flightid,seatsbooked);
    }




}
