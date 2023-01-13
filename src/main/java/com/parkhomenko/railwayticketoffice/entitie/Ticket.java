package com.parkhomenko.railwayticketoffice.entitie;

import java.io.Serializable;
import java.sql.Date;

public class Ticket implements Serializable {

    private int id;
    private int idTravel;
    private int idStationFrom;
    private int idStationWhere;
    private int idUser;
    private int idSeat;
    private Date purchaseDateTime;
    private String status;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(int idTravel) {
        this.idTravel = idTravel;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
    }

    public Date getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(Date purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
