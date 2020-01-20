package com.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightmanagement.FlightManagement;
import com.flightmanagement.model.Flight;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FlightManagement.class)
public class FlightControllerTest
{
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

        @Test
        @Ignore
        public void getAllFlightsTest() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/flight/getAllFlights")
        .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(1))).andDo(print());
        }

        @Test
        @Ignore
    public void getFlightbyId() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/flight/getFlightDetails?flightid=2")
        .accept(MediaType.APPLICATION_JSON))
         .andExpect(MockMvcResultMatchers.jsonPath("flightid").exists())
         .andExpect(MockMvcResultMatchers.jsonPath("departurecity").exists())
         .andExpect(MockMvcResultMatchers.jsonPath("arrivalcity").exists())
         .andExpect(MockMvcResultMatchers.jsonPath("departuretime").exists())
         .andExpect(MockMvcResultMatchers.jsonPath("arrivaltime").exists())
         .andExpect(MockMvcResultMatchers.jsonPath("seatlowprice").exists())
         .andExpect(MockMvcResultMatchers.jsonPath("seatmediumprice").exists())
         .andExpect(MockMvcResultMatchers.jsonPath("seathighprice").exists())
         .andExpect(MockMvcResultMatchers.jsonPath("totalseats").value("120"))
         .andExpect(MockMvcResultMatchers.jsonPath("availableseats").exists())
         .andDo(print());
        }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
        @Test
        public void addFlightTest() throws Exception{

        Flight flight = new Flight("New Delhi","Mumbai","11:00"
        ,"13:00",3900,4400,5000,120,120);
//        flight.setDeparturecity("New Delhi);
//        flight.setArrivalcity("Pune");
//        flight.setDeparturetime();
//        flight.setArrivaltime("15:00");
//        flight.setSeatlowprice(4000);
//        flight.setSeathighprice(4500);
//        flight.setSeathighprice(5100);
//        flight.setTotalseats(120);
//        flight.setAvailableseats(120);
        mockMvc.perform(MockMvcRequestBuilders.post("/flight/addFlight")
        .content(asJsonString(flight))
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
   /*     @Test
        public void updateFlightTest() throws Exception{



        }*/



}
