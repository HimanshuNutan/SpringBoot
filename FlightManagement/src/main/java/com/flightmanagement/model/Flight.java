package com.flightmanagement.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component("flight")
public class Flight {
    @Getter @Setter private int flightid;
    @Getter @Setter private String departurecity;
    @Getter @Setter private String arrivalcity;
    @Getter @Setter private String departuretime;
    @Getter @Setter private String arrivaltime;
    @Getter @Setter private long seatlowprice;
    @Getter @Setter private long seatmediumprice;
    @Getter @Setter private long seathighprice;
    @Getter @Setter private int totalseats;
    @Getter @Setter private int availableseats;

    public Flight(){}
    public Flight(String departurecity, String arrivalcity, String departuretime, String arrivaltime, long seatlowprice, long seatmediumprice, long seathighprice, int totalseats, int availableseats) {
        super();
        this.departurecity = departurecity;
        this.arrivalcity = arrivalcity;
        this.departuretime = departuretime;
        this.arrivaltime = arrivaltime;
        this.seatlowprice = seatlowprice;
        this.seatmediumprice = seatmediumprice;
        this.seathighprice = seathighprice;
        this.totalseats = totalseats;
        this.availableseats = availableseats;
    }
}
