package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.WagonDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.entitie.Wagon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WagonService {

//    public static long addNewWagon(int number, int id_train) throws SQLException, ClassNotFoundException {
//        Connection con = DBManager.getMySQLConnection();
//        long newWagonId = WagonDao.addNew(con, number, id_train);
//        con.close();
//        return newWagonId;
//    }

    public static List<Wagon> getLooseWagons(String trainId, String travelId) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        List<Wagon> wagons = WagonDao.getLooseWagons(con, trainId, travelId);
        con.close();
        return wagons;
    }

}
