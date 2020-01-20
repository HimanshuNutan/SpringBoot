package com.flightmanagement.DAO;

import com.flightmanagement.model.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightDAOImpl implements FlightDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger = LogManager.getLogger(FlightDAOImpl.class.getName());



    public int insertFlight(Flight flight){
        int response = 0;
        try{
            logger.info("Insert Flight details"+flight.getArrivalcity()+flight.getDeparturecity()+flight.getArrivaltime()+flight.getAvailableseats()
            +flight.getDeparturetime()+flight.getSeathighprice()+flight.getSeatmediumprice()+flight.getSeatlowprice());
        String query = "INSERT INTO FLIGHT (departurecity,arrivalcity,departuretime,arrivaltime,seatlowprice,seatmediumprice,seathighprice," +
                "totalseats,availableseats) VALUES(?,?,?,?,?,?,?,?,?)";
        response = jdbcTemplate.update(query,flight.getDeparturecity(),flight.getArrivalcity(),flight.getDeparturetime(),flight.getArrivaltime(),
                flight.getSeatlowprice(),flight.getSeatmediumprice(),flight.getSeathighprice(),flight.getTotalseats(),flight.getTotalseats());}
        catch (Exception e){
            logger.error("Error in Inserting Flight Details",e);
        }
        return response;
    }
    public int updateFlight(Flight flight,int flightid){
        int response = 0;

        try {
            String query = "UPDATE FLIGHT SET departurecity = ?,arrivalcity =?,departuretime = ?,arrivaltime = ?,seatlowprice = ?," +
                    "seatmediumprice =?,seathighprice =?,totalseats =?,availableseats =? where flightid = ?";
            response = jdbcTemplate.update(query, flight.getDeparturecity(), flight.getArrivalcity(), flight.getDeparturetime(), flight.getArrivaltime(),
                    flight.getSeatlowprice(), flight.getSeatmediumprice(), flight.getSeathighprice(), flight.getTotalseats(), flight.getTotalseats(),flight.getFlightid());
        }catch (Exception e){
            logger.error("Error in updating Flight",e);
        }
        return response;
    }

    public int deleteFlight(int flightid){
        int response = 0;
        try {
            String query = "DELETE FROM FLIGHT where flightid = ?";
            response = jdbcTemplate.update(query, flightid);
        }catch (Exception e){
            logger.error("Error in deleting Flight",e);
        }
        return response;
    }
    public List<Flight> getAllFlights(){
        List<Flight> flightList = new ArrayList<>();
        String query = "SELECT * FROM FLIGHT";

        return jdbcTemplate.query(query, new RowMapper<Flight>() {
            @Override
            public Flight mapRow(ResultSet rs, int rowNum) {
                Flight flight = null;
                try{
                       flight.setFlightid(rs.getInt("flightid"));
                       flight.setDeparturecity(rs.getString("departurecity"));
                       flight.setArrivalcity(rs.getString("arrivalcity"));
                       flight.setDeparturetime(rs.getString("departuretime"));
                       flight.setArrivaltime(rs.getString("arrivaltime"));
                       flight.setSeatlowprice(rs.getInt("seatlowprice"));
                       flight.setSeatmediumprice(rs.getInt("seatmediumprice"));
                       flight.setSeathighprice(rs.getInt("seathighprice"));
                    flight.setTotalseats(rs.getInt("totalseats"));
                    flight.setAvailableseats(rs.getInt("availableseats"));
                }catch (Exception e){
                    logger.error("Error in getting list of Flights",e);
                }
return flight;


            }
        });
    }

    @Override
    public int updateAvailableSeats(int flightid, int seatsbooked) {
       int response = 0;int availableseats;
       try {
           Flight flight = null;
           flight = getFlight(flightid);
           availableseats = flight.getAvailableseats();
           availableseats = availableseats - seatsbooked;
           String query = "UPDATE FLIGHT set availableseats = "+availableseats+" where flightid ="+flightid;
          // System.out.println(query);
           response = jdbcTemplate.update(query);
       }catch (Exception e){
           logger.error("Error in updating seats");
       }
        return response;
            }

    public Flight getFlight(int flightid) {


            String query = "SELECT * FROM FLIGHT WHERE FLIGHTID=?";
            return jdbcTemplate.queryForObject(query, new Object[]{ flightid }, new RowMapper<Flight>() {

                @Override
                public Flight mapRow(ResultSet rs, int rowNum) {
                    Flight flight = new Flight();
                    try {
                        flight.setFlightid(rs.getInt("flightid"));
                        flight.setDeparturecity(rs.getString("departurecity"));
                        flight.setArrivalcity(rs.getString("arrivalcity"));
                        flight.setDeparturetime(rs.getString("departuretime"));
                        flight.setArrivaltime(rs.getString("arrivaltime"));
                        flight.setSeatlowprice(rs.getInt("seatlowprice"));
                        flight.setSeatmediumprice(rs.getInt("seatmediumprice"));
                        flight.setSeathighprice(rs.getInt("seathighprice"));
                        flight.setTotalseats(rs.getInt("totalseats"));
                        flight.setAvailableseats(rs.getInt("availableseats"));
                    } catch (Exception e) {
                        logger.error("Error in getting Flight details", e);
                    }
                    return flight;
                }
            });
    }
}