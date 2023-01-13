package com.parkhomenko.railwayticketoffice.entitie;

import java.io.Serializable;

public class Wagon implements Serializable {

    private int id;
    private int idTrain;
    private int number;

    public Wagon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
