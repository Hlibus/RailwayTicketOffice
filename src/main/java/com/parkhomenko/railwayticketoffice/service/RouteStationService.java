package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.RouteStationDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.dto.RouteStationWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.RouteStation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RouteStationService {

    public static List<RouteStationWithNamesDto> getRouteStationWithNamesByRouteId(String routeId) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        List<RouteStationWithNamesDto> routeStationWithNames = RouteStationDao.findAllByRouteId(con, routeId);
        con.close();
        return routeStationWithNames;
    }

    public static  void updateRouteStation(RouteStation routeStation) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        RouteStationDao.update(con, routeStation);
        con.close();
    }

    public static  void updateRouteStationWithNext(RouteStation routeStation, int id) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        con.setAutoCommit(false);

        RouteStationDao.update(con, routeStation);
        RouteStationDao.updateNext(con, routeStation.getIdStationWhere(), id);

        con.commit();
        con.close();
    }

    public static int hasNextStations(String routeId, String number) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        int id = RouteStationDao.hasNextStations(con, routeId, number);
        con.close();
        return id;
    }
}
