package com.airlinesmanagement.model;

import org.springframework.stereotype.Component;

@Component("flight")
public class Flight {
    private int flightid;
    private String departurecity;
    private String arrivalcity;
    private String departuretime;
    private String arrivaltime;
    private long seatlowprice;
    private long seatmediumprice;
    private long seathighprice;
    private int totalseats;

    public int getAvailableseats() {
        return availableseats;
    }

    public void setAvailableseats(int availableseats) {
        this.availableseats = availableseats;
    }

    private int availableseats;

    public int getFlightid() {
        return flightid;
    }

    public void setFlightid(int flightid) {
        this.flightid = flightid;
    }

    public String getDeparturecity() {
        return departurecity;
    }

    public void setDeparturecity(String departurecity) {
        this.departurecity = departurecity;
    }

    public String getArrivalcity() {
        return arrivalcity;
    }

    public void setArrivalcity(String arrivalcity) {
        this.arrivalcity = arrivalcity;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public long getSeatlowprice() {
        return seatlowprice;
    }

    public void setSeatlowprice(long seatlowprice) {
        this.seatlowprice = seatlowprice;
    }

    public long getSeatmediumprice() {
        return seatmediumprice;
    }

    public void setSeatmediumprice(long seatmediumprice) {
        this.seatmediumprice = seatmediumprice;
    }

    public long getSeathighprice() {
        return seathighprice;
    }

    public void setSeathighprice(long seathighprice) {
        this.seathighprice = seathighprice;
    }

    public int getTotalseats() {
        return totalseats;
    }

    public void setTotalseats(int totalseats) {
        this.totalseats = totalseats;
    }
}
