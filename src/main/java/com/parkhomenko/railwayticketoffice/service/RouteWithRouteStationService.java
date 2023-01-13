package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.RouteDao;
import com.parkhomenko.railwayticketoffice.dao.RouteStationDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.entitie.RouteStation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RouteWithRouteStationService {

    public static boolean addNewRouteAndRouteStations(String routeName, List<RouteStation> routeStations) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        con.setAutoCommit(false);
        boolean res = false;
        try {
            long routeId = RouteDao.addNew(con, routeName);
            for (RouteStation routeStation : routeStations) {
                RouteStationDao.addNew(con, routeId, routeStation);
            }
            con.commit();
            res = true;
        } catch (SQLException ex) {
            con.rollback();
            ex.printStackTrace();
        } finally {
            con.close();
        }
        return res;
    }

}
