package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.StationDao;
import com.parkhomenko.railwayticketoffice.dao.TravelDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.dto.TravelWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.Station;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StationService {

    public static List<Station> getAllStations() throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        List<Station> stations = StationDao.findAll(con);
        con.close();
        return stations;
    }

    public static Station getStationById(String id) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        Station station = StationDao.findById(con, id);
        con.close();
        return station;
    }

    public static int findStationIdByName(String name) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        int id = StationDao.findIdByName(con, name);
        con.close();
        return id;
    }

    public static void addNewStation(String name, String address, String coordinates) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        StationDao.addNew(con, name, address, coordinates);
        con.close();
    }

    public static void deleteStation(String id) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        StationDao.delete(con, id);
        con.close();
    }

    public static void update(String id, String name, String address, String coordinates) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        StationDao.update(con, id, name, address, coordinates);
        con.close();
    }

}
