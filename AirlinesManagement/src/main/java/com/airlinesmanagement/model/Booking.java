package com.airlinesmanagement.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("booking")
public class Booking {

    private int bookingid;
    private String userid;
    private String dateofbooking;
    private String dateofflight;
    private String flightdetails;
    private int seatsbooked;
    private double amountpaid;
    private String paymentmethod;
    private int flightid;

    public int getFlightid() {
        return flightid;
    }

    public void setFlightid(int flightid) {
        this.flightid = flightid;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingid=" + bookingid +
                ", userid='" + userid + '\'' +
                ", dateofbooking=" + dateofbooking +
                ", dateofflight=" + dateofflight +
                ", flightdetails='" + flightdetails + '\'' +
                ", seatsbooked=" + seatsbooked +
                ", amountpaid=" + amountpaid +
                ", paymentmethod='" + paymentmethod + '\'' +
                '}';
    }



    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String useid) {
        this.userid = useid;
    }

    public String getDateofbooking() {
        return dateofbooking;
    }

    public void setDateofbooking(String dateofbooking) {
        this.dateofbooking = dateofbooking;
    }

    public String getDateofflight() {
        return dateofflight;
    }

    public void setDateofflight(String dateofflight) {
        this.dateofflight = dateofflight;
    }

    public String getFlightdetails() {
        return flightdetails;
    }

    public void setFlightdetails(String flightdetails) {
        this.flightdetails = flightdetails;
    }

    public int getSeatsbooked() {
        return seatsbooked;
    }

    public void setSeatsbooked(int seatsbooked) {
        this.seatsbooked = seatsbooked;
    }

    public double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}
