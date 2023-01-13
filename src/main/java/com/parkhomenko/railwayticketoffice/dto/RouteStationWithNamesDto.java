package com.parkhomenko.railwayticketoffice.dto;

import java.sql.Time;

public class RouteStationWithNamesDto {

    private int id;
    private String routeName;
    private String stationFromName;
    private int stationFromId;
    private String stationFromAddress;
    private String stationWhereName;
    private int stationWhereId;
    private String stationWhereAddress;
    private double price;
    private Time travelTime;
    private int number;

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

    public String getStationFromName() {
        return stationFromName;
    }

    public void setStationFromName(String stationFromName) {
        this.stationFromName = stationFromName;
    }

    public String getStationWhereName() {
        return stationWhereName;
    }

    public void setStationWhereName(String stationWhereName) {
        this.stationWhereName = stationWhereName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Time getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Time travelTime) {
        this.travelTime = travelTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStationFromAddress() {
        return stationFromAddress;
    }

    public void setStationFromAddress(String stationFromAddress) {
        this.stationFromAddress = stationFromAddress;
    }

    public String getStationWhereAddress() {
        return stationWhereAddress;
    }

    public void setStationWhereAddress(String stationWhereAddress) {
        this.stationWhereAddress = stationWhereAddress;
    }

    public int getStationFromId() {
        return stationFromId;
    }

    public void setStationFromId(int stationFromId) {
        this.stationFromId = stationFromId;
    }

    public int getStationWhereId() {
        return stationWhereId;
    }

    public void setStationWhereId(int stationWhereId) {
        this.stationWhereId = stationWhereId;
    }
}
