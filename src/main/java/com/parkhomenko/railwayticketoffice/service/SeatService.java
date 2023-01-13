package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.SeatDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.entitie.Seat;

import java.sql.Connection;
import java.sql.SQLException;

public class SeatService {

    public static Seat getFreeSeat(String trainId, String travelId) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        Seat seat = SeatDao.getFreeSeat(con, trainId, travelId);
        con.close();
        return seat;
    }

}
