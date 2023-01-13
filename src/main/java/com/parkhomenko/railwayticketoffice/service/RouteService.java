package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.RouteDao;
import com.parkhomenko.railwayticketoffice.dao.TravelDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.dto.TravelWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RouteService {

    public static List<Route> getAllRoutes() throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        List<Route> routes = RouteDao.findAll(con);
        con.close();
        return routes;
    }

    public static void editRoute(String name, String id) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        RouteDao.edit(con, name, id);
        con.close();
    }

    public static void deleteRoute(String id) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        RouteDao.delete(con, id);
        con.close();
    }

    public static int getRouteIdByName(String name) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        int routeId = RouteDao.findIdByName(con, name);
        con.close();
        return routeId;
    }

}
