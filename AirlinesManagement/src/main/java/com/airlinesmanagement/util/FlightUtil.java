package com.airlinesmanagement.util;

import com.airlinesmanagement.model.Flight;
import com.airlinesmanagement.service.AirManService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightUtil {

    Logger logger = LogManager.getLogger(AirManService.class.getName());
    public List<Flight>  convertStringtoList(String res){
        List<Flight> flight = null;
        try {

            final ObjectMapper mapper = new ObjectMapper();
            flight = mapper.readValue(res,new TypeReference<List<Flight>>(){});
        }catch (Exception e){
            logger.error("Error in converting Flight Details from String to List",e);
        }
        return flight;
    }

     public Flight convertStringtoObject(String res){
        Flight flight = null;
        try{
        final ObjectMapper mapper = new ObjectMapper();
        flight = mapper.readValue(res,Flight.class);
        }catch (Exception e){
            logger.error("Error in converting Flight Details from String to Object");
        }
        return flight;
     }

}
