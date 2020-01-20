package com.flightmanagement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.flightmanagement")
public class FlightManagement {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FlightManagement.class,args);
    }

}
