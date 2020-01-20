package com.airlinesmanagement;

import com.airlinesmanagement.service.AirManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan("com.airlinesmanagement")
public class AirlinesManagement {


    public static void main(String[] args) {
        AirManService airManService = new AirManService();
        ApplicationContext context = SpringApplication.run(AirlinesManagement.class,args);
        try{

        airManService.addFlight();
        }catch (Exception e){
            System.out.println("Exception caught in adding flight");
        }
    }
}
