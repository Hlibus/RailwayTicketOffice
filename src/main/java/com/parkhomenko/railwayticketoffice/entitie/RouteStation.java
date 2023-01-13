package com.parkhomenko.railwayticketoffice.entitie;

import java.io.Serializable;
import java.sql.Time;

public class RouteStation implements Serializable {

    private int id;
    private int idRoute;
    private int idStationFrom;
    private int idStationWhere;
    private double price;
    private Time travelTime;
    private int number;

    public RouteStation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public int getIdStationFrom() {
        return idStationFrom;
    }

    public void setIdStationFrom(int idStationFrom) {
        this.idStationFrom = idStationFrom;
    }

    public int getIdStationWhere() {
        return idStationWhere;
    }

    public void setIdStationWhere(int idStationWhere) {
        this.idStationWhere = idStationWhere;
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
}
