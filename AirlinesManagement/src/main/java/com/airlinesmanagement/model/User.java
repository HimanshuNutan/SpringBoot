package com.airlinesmanagement.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("user")
public class User {
    private int userid;
    private String password;
    private String firstname;
    private String lastname;
    private Date dateofbirth;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateofbirth=" + dateofbirth +
                ", address='" + address + '\'' +
                ", contactnumber=" + contactnumber +
                ", emailid='" + emailid + '\'' +
                '}';
    }

    private long contactnumber;
    private String emailid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(long contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
