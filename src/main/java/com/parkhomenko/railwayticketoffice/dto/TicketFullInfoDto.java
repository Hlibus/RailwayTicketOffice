package com.parkhomenko.railwayticketoffice.dto;

import com.parkhomenko.railwayticketoffice.entitie.Ticket;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TicketFullInfoDto {

//    private int id;
//    private int idTravel;
//    private int idStationFrom;
//    private int idStationWhere;
//    private int userId;
//    private int seatId;
//    private Date purchaseDateTime;
//    private String status;

    private int travelId;
    private String routeName;
    private String stationFromName;
    private int stationFromId;
    private String stationWhereName;
    private int stationWhereId;
    private int trainNumber;
    private int trainId;
    private Timestamp departureDateTime;
    private Time timeInTravel;
    private double price;

    private Date purchaseDateTime;
    private Timestamp arrivalDateTime;

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
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

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Timestamp getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = Timestamp.valueOf(arrivalDateTime);
    }

    public Time getTimeInTravel() {
        return timeInTravel;
    }

    public void setTimeInTravel(Time timeInTravel) {
        this.timeInTravel = timeInTravel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Timestamp departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }


}
