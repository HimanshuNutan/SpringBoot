package com.airlinesmanagement.controller;

import com.airlinesmanagement.model.User;
import com.airlinesmanagement.service.AirManService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AirManService airManService;
    private static final Logger LOGGER =
            LogManager.getLogger(BookingController.class.getName());

    // http://localhost:8080/user/getAll
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<User> showAllUsers(){
        List<User> userList = new ArrayList<User>();
        try{
            userList = airManService.showAllUsers();
            LOGGER.info("Fetched User Records");}
        catch (Exception e){
            LOGGER.error("Error occured in fetching records",e);
        }
        return userList; }

    // http://localhost:8080/user/getUser
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User showUserDetails(@RequestParam("userid") int userid) {
        User user = new User();
        try{
            user = airManService.showUserDetails(userid);
            LOGGER.info("Fetched Record");
        }catch (Exception e){
            LOGGER.error("Error occured in getting user details",e);
        }
        return user;
    }

    // http://localhost:8080/user/addUser
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public int insertUser(@RequestBody User user){
        int response = 0;
      try{
         response = airManService.insertUser(user);
      }catch (Exception e){
          LOGGER.error("Error Inserting User",e);
      }
      return response;
    }

    // http://localhost:8080/user/deleteUser
    @RequestMapping(value = "/deleteUser/{userid}",method = RequestMethod.DELETE)
    public int deleteUser(@PathVariable("userid") int userid){
        int response = 0;
        LOGGER.info("Deleting user with id "+userid);
        System.out.println(userid);
        try{
          response = airManService.deleteUser(userid);
        }
        catch (Exception e){
            LOGGER.error("Could not delete User!",e);
        }
       return response;
    }

    //http://localhost:8080/user/login
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean checkCredentials(@RequestBody User user){
        LOGGER.info("Trying to log in using id "+user.getUserid()+" and password "+user.getPassword());
        boolean valid = false;
        try{
          valid = airManService.checkCredentials(user);
        }catch (Exception e){
            LOGGER.error("Error occured while logging in",e);
        }
          return valid;
    }

}
