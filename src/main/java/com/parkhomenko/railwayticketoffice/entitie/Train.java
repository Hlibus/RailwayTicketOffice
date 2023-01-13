package com.parkhomenko.railwayticketoffice.entitie;

import java.io.Serializable;

public class Train implements Serializable {

    private int id;
    private int number;

    public Train() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
