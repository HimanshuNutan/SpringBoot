package com.flightmanagement.DAO;

import com.flightmanagement.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("FlightDAO")
public interface FlightDAO {

public int insertFlight(Flight flight);
public int updateFlight(Flight flight,int flightid);
public int deleteFlight(int flightid);
public Flight getFlight(int flightid);
public List<Flight> getAllFlights();
public int updateAvailableSeats(int flightid,int seatsbooked);
}
