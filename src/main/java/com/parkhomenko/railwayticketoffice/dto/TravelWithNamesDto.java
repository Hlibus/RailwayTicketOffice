package com.parkhomenko.railwayticketoffice.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TravelWithNamesDto {

    private int id;
    private String routeName;
    private int trainNumber;
    private Timestamp departureDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Timestamp getDepartureDateTime() {
        return departureDateTime;
    }

    public String getStringDepartureDateTime() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(departureDateTime);
    }

    public void setDepartureDateTime(Timestamp departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
}
