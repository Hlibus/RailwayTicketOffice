package com.parkhomenko.railwayticketoffice.entitie;

import java.io.Serializable;

public class Seat implements Serializable {

    private int id;
    private int idWagon;
    private int number;

    public Seat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(int idWagon) {
        this.idWagon = idWagon;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
