package com.parkhomenko.railwayticketoffice.dao;

import com.parkhomenko.railwayticketoffice.dto.RouteStationWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.RouteStation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteStationDao {

    private static final String FIND_ALL_BY_ROUTE_ID = "SELECT table1.id, table1.name, table1.stationFrom, " +
            "table1.stationFromId, table1.stationFromAddress, table2.stationWhere, table2.stationWhereId, " +
            "table2.stationWhereAddress, table1.price, table1.travel_time, table1.number FROM \n" +
            "(SELECT routestation.id, routestation.id_route, route.name, station.name AS stationFrom, " +
            "station.id AS stationFromId, station.address AS stationFromAddress,\n" +
            "routeStation.price, routeStation.travel_time, routeStation.number " +
            "FROM routeStation, station, route " +
            "WHERE routeStation.id_stationFrom = station.id AND routeStation.id_route = route.id AND routeStation.id_route = ?) " +
            "AS table1 LEFT JOIN \n" +
            "(SELECT routestation.id, routestation.id_route, station.name AS stationWhere, " +
            "station.id AS stationWhereId, station.address AS stationWhereAddress \n" +
            "FROM routeStation, station " +
            "WHERE routeStation.id_stationWhere = station.id AND routeStation.id_route = ?) \n" +
            "AS table2 ON table1.id = table2.id";
    private static final String CREATE_NEW_ROUTESTATIONS = "INSERT INTO routestation (id_route, id_stationFrom, " +
            "id_stationWhere, price, travel_time, number) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ROUTESTATIONS = "UPDATE routestation SET id_stationFrom = ?, id_stationWhere = ?, " +
            "price = ?, travel_time = ? WHERE id = ?";
    private static final String UPDATE_ROUTESTATIONS_NEXT = "UPDATE routestation SET id_stationFrom = ? WHERE id = ?";
    private static final String HAS_NEXT_STATIONS = "SELECT id FROM routestation WHERE id_route = ? AND number = ?";

    private static final String GET_NUMBER_OF_STATION = "SELECT number FROM routestation WHERE id_route = ? AND id_stationFrom = ?";

    public static List<RouteStationWithNamesDto> findAllByRouteId(Connection con, String routeId) throws SQLException {
        List<RouteStationWithNamesDto> routeStationsWithNames = new ArrayList<>();

        PreparedStatement pstmt = con.prepareStatement(FIND_ALL_BY_ROUTE_ID);
        pstmt.setString(1, routeId);
        pstmt.setString(2, routeId);
        ResultSet rs = pstmt.executeQuery();
        extractRouteStationsWithNames(rs, routeStationsWithNames);

        return routeStationsWithNames;
    }

    public static void addNew(Connection con, long routeId, RouteStation routeStation) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(CREATE_NEW_ROUTESTATIONS);
        pstmt.setInt(1, (int) routeId);
        pstmt.setInt(2, routeStation.getIdStationFrom());
        pstmt.setInt(3, routeStation.getIdStationWhere());
        pstmt.setDouble(4, routeStation.getPrice());
        pstmt.setTime(5, routeStation.getTravelTime());
        pstmt.setInt(6, routeStation.getNumber());
        pstmt.executeUpdate();
    }

    public static int hasNextStations(Connection con, String routeId, String number) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(HAS_NEXT_STATIONS);
        pstmt.setInt(1, Integer.parseInt(routeId));
        pstmt.setInt(2, Integer.parseInt(number)+1);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        }
        return -1;
    }

    public static void update(Connection con, RouteStation routeStation) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_ROUTESTATIONS);
        pstmt.setInt(1, routeStation.getIdStationFrom());
        pstmt.setInt(2, routeStation.getIdStationWhere());
        pstmt.setDouble(3, routeStation.getPrice());
        pstmt.setTime(4, routeStation.getTravelTime());
        pstmt.setInt(5, routeStation.getId());
        pstmt.executeUpdate();
    }

    public static void updateNext(Connection con, int newStationFromId, int id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_ROUTESTATIONS_NEXT);
        pstmt.setInt(1, newStationFromId);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }

    private static void extractRouteStationsWithNames(ResultSet rs, List<RouteStationWithNamesDto> routeStationsWithNames) throws SQLException {
        RouteStationWithNamesDto routeStationWithNames;
        while (rs.next()) {
            routeStationWithNames = new RouteStationWithNamesDto();
            extractRouteStationWithNames(rs, routeStationWithNames);
            routeStationsWithNames.add(routeStationWithNames);
        }

    }

    private static void extractRouteStationWithNames(ResultSet rs, RouteStationWithNamesDto routeStationWithNames) throws SQLException {
        routeStationWithNames.setId(rs.getInt("id"));
        routeStationWithNames.setRouteName(rs.getString("name"));
        routeStationWithNames.setStationFromName(rs.getString("stationFrom"));
        routeStationWithNames.setStationFromId(rs.getInt("stationFromId"));
        routeStationWithNames.setStationFromAddress(rs.getString("stationFromAddress"));
        routeStationWithNames.setStationWhereName(rs.getString("stationWhere"));
        routeStationWithNames.setStationWhereId(rs.getInt("stationWhereId"));
        routeStationWithNames.setStationWhereAddress(rs.getString("stationWhereAddress"));
        routeStationWithNames.setPrice(rs.getDouble("price"));
        routeStationWithNames.setTravelTime(rs.getTime("travel_time"));
        routeStationWithNames.setNumber(rs.getInt("number"));
    }

    public static

}
