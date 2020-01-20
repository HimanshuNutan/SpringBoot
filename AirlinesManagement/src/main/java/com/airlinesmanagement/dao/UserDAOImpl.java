package com.airlinesmanagement.dao;

import com.airlinesmanagement.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;


@Repository("userdao")
public class UserDAOImpl implements UserDAO {

    // DATA MEMBERS

    @Autowired
     private JdbcTemplate jdbcTemplate;
     private static final Logger LOGGER =
        LogManager.getLogger(UserDAOImpl.class.getName());

    //MEMBER FUNCTIONS

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Function to insert a new User  / Sign Up Function
    @Override
    public int insertUser(User user) {
        LOGGER.info("Inserting user with details"+user.toString());
        int response=0;

     try{
      String query = "INSERT into user(password,firstname,lastname,dateofbirth,address,contactnumber,emailid) values(?,?,?,?,?,?,?)";
         System.out.println(query+user.getPassword()+user.getFirstname()+user.getLastname()+
                 user.getDateofbirth()+user.getAddress()+user.getContactnumber()+user.getEmailid());
       response = jdbcTemplate.update(query,user.getPassword(),user.getFirstname(),user.getLastname(),
        user.getDateofbirth(),user.getAddress(),user.getContactnumber(),user.getEmailid());
        LOGGER.info("Record inserted successfully");

     }catch(Exception e){
         LOGGER.error("Error occured while inserting user record",e);
     }
     return response;
    }


    //Function to check if user credentials are correct   / Login Function
    @Override
    public boolean checkCredentials(User user) {
        boolean valid = false;
        LOGGER.info("Checking credentials for User "+user.getUserid()+"and Password "+user.getPassword());
        try {
            String query = "select userid from user where userid ='" + user.getUserid() + "' and password = '" + user.getPassword()+"'";

            List<User> users = jdbcTemplate.query(query, new RowMapper<User>() {

                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    //System.out.println("INSIDE mapROW");
                    user.setUserid(rs.getInt("userid"));
                    return user;
                }
            });
            if (users.isEmpty()) {
            } else {
                valid = true;
                LOGGER.info("Credentials Matched");
            //    System.out.println("Credentials matched");
            }
        }catch (Exception e){
            LOGGER.error("Error occured while trying to login",e);
          //  System.out.println("Error"+e);
        }
        System.out.println(valid);
        return valid;
    }


    //Function to update User Details
    @Override
    public int updateUser(User user) {
        int response = 0;
        LOGGER.info("Updating details for user "+user.getUserid()+" with data "+user.toString());
        try{
            String query = "UPDATE TABLE user set firstname = ?, lastname = ?, dateofbirth = ?,address = ?,contactnumber = ?,emailid = ?" +
                    "where userid = ?";
             response = jdbcTemplate.update(query,user.getFirstname(),
                    user.getLastname(),user.getDateofbirth(),user.getAddress(),user.getContactnumber(),user.getEmailid());
            LOGGER.info("User Details Updated");
            }
        catch (Exception e){
            LOGGER.error("Error occured while updating user details",e);
        }
        return response;
    }



    //Function to Delete a User Account
    @Override
    public int deleteUser(int userid) {int response = 0;
    LOGGER.info("Deleting user "+userid);
        try{
          String query = "DELETE FROM user where userid = ?";
          response = jdbcTemplate.update(query,userid);
          LOGGER.info("User Deleted");
        }
        catch (Exception e){
            LOGGER.error("Error occured while deleting user",e);
        }
          return response;
    }


    //Function to get all User Details
    @Override
    public List<User> showAllUsers() {
        String query ="SELECT * from user";
        LOGGER.info("Getting List of all Users");
        return jdbcTemplate.query(query, new RowMapper<User>() {
            public User mapRow(ResultSet rs,int rowNum){
                User user = new User();
                try {
                    user.setUserid(rs.getInt("userid"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setDateofbirth(rs.getDate("dateofbirth"));
                    user.setAddress(rs.getString("address"));
                    user.setContactnumber(rs.getLong("contactnumber"));
                    user.setEmailid(rs.getString("emailid"));


                }catch (Exception e){
                    LOGGER.error("Error getting the list of all users",e);
                }
               return user;            }
        });

    }


    //Function to get details of a User
    @Override
    public User showUserDetails(int userid) {
        LOGGER.info("Getting details for user "+userid);
        String query = "SELECT * from user where userid = ?";
       return jdbcTemplate.queryForObject(query, new Object[]{userid}, new RowMapper<User>() {
           @Override
           public User mapRow(ResultSet rs, int rowNum) throws SQLException {
               User user = new User();
               try{
                   user.setUserid(rs.getInt("userid"));
                   user.setFirstname(rs.getString("firstname"));
                   user.setLastname(rs.getString("lastname"));
                   user.setDateofbirth(rs.getDate("dateofbirth"));
                   user.setAddress(rs.getString("address"));
                   user.setContactnumber(rs.getLong("contactnumber"));
                   user.setEmailid(rs.getString("emailid"));
               }
               catch (Exception e){
                   LOGGER.error("Error occured while getting details",e);
               }
               return user;
           }
       });

    }

}
