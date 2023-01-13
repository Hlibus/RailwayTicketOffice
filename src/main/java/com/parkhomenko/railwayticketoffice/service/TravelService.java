package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.TravelDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.dto.TravelWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.Travel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TravelService {

    public static List<Travel> getAllTravels() throws SQLException, ClassNotFoundException{
        Connection con = DBManager.getMySQLConnection();
        List<Travel> travels = TravelDao.findAll(con);
        con.close();
        return travels;
    }

    public static Travel getTravelById (String id) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        Travel travel = TravelDao.findTravelById(con, id);
        con.close();
        return travel;
    }

    public static TravelWithNamesDto getTravelWithNamesById (String id) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        TravelWithNamesDto travel = TravelDao.findTravelsWithNamesById(con, id);
        con.close();
        return travel;
    }

    public static List<TravelWithNamesDto> getAllTravelsWithNames() throws SQLException, ClassNotFoundException{
        Connection con = DBManager.getMySQLConnection();
        List<TravelWithNamesDto> travelsWithNames = TravelDao.findAllTravelsWithNames(con);
        con.close();
        return travelsWithNames;
    }

    public static List<TravelWithNamesDto> getActualTravelsWithNames() throws SQLException, ClassNotFoundException{
        Connection con = DBManager.getMySQLConnection();
        List<TravelWithNamesDto> travelsWithNames = TravelDao.findActualTravelsWithNames(con);
        con.close();
        return travelsWithNames;
    }

    public static void addNewTravel(Travel travel) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        TravelDao.addNewTravel(con, travel);
        con.close();
    }

    public static void deleteTravel(int travelId) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        TravelDao.deleteTravel(con, travelId);
        con.close();
    }

    public static long getRouteId(String travelId) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        long res = TravelDao.getRouteId(con, travelId);
        con.close();
        return res;
    }

}
