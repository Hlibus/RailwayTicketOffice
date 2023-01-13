package com.parkhomenko.railwayticketoffice.dao;

import com.parkhomenko.railwayticketoffice.dto.TicketFullInfoDto;
import com.parkhomenko.railwayticketoffice.entitie.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationDao {

    private static final String FIN_ALL_STATIONS = "SELECT * FROM station";
    private static final String FIND_STATION_BY_ID = "SELECT * FROM station WHERE id = ?";
    private static final String FIND_STATION_ID_BY_NAME = "SELECT id FROM station WHERE name = ?";
    private static final String DELETE_STATION = "DELETE FROM station WHERE id = ?";
    private static final String UPDATE_STATION = "UPDATE station SET name = ?, address = ?, coordinates = ? WHERE id = ?";
    private static final String ADD_NEW_STATION = "INSERT INTO station (name, address, coordinates) VALUES (?, ?, ?)";

    private static final String GET_TICKET_INFO_FROM_STATION = "SELECT stationFrom.name AS stationFrom, " +
            "stationWhere.name AS stationWhere FROM station AS stationFrom, \n" +
            "station AS stationWhere WHERE stationFrom.id = ? AND stationWhere.id = ?;";

    public static List<Station> findAll(Connection con) throws SQLException {
        List<Station> stations = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(FIN_ALL_STATIONS);
        extractStations(rs, stations);

        return stations;
    }

    public static Station findById(Connection con, String id) throws SQLException {
        Station station  = new Station();

        PreparedStatement pstmt = con.prepareStatement(FIND_STATION_BY_ID);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
          extractStation(rs, station);
        }

        return station;
    }

    public static int findIdByName(Connection con, String name) throws SQLException {
        int id  = -1;

        PreparedStatement pstmt = con.prepareStatement(FIND_STATION_ID_BY_NAME);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            id = rs.getInt("id");
        }

        return id;
    }

    public static void addNew(Connection con, String name, String address, String coordinates) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(ADD_NEW_STATION);
        pstmt.setString(1, name);
        pstmt.setString(2, address);
        pstmt.setString(3, coordinates);
        pstmt.executeUpdate();
    }

    public static void delete(Connection con, String id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(DELETE_STATION);
        pstmt.setString(1, id);
        pstmt.executeUpdate();
    }

    public static void update(Connection con, String id, String name, String address, String coordinates) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_STATION);
        pstmt.setString(1, name);
        pstmt.setString(2, address);
        pstmt.setString(3, coordinates);
        pstmt.setString(4, id);
        pstmt.executeUpdate();
    }

    private static void extractStations(ResultSet rs, List<Station> stations) throws SQLException {
        Station station;
        while(rs.next()){
            station = new Station();
            extractStation(rs, station);
            stations.add(station);
        }
    }

    private static void extractStation(ResultSet rs, Station station) throws SQLException {
        station.setId(rs.getInt("id"));
        station.setName(rs.getString("name"));
        station.setAddress(rs.getString("address"));
        station.setCoordinates(rs.getString("coordinates"));
    }

    public static void getStationsInfoForTicket(Connection con, TicketFullInfoDto ticketFullInfo, int stationFromId, int stationWhereId) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(GET_TICKET_INFO_FROM_STATION);
        pstmt.setInt(1, stationFromId);
        pstmt.setInt(2, stationWhereId);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            ticketFullInfo.setStationFromName(rs.getString("stationFrom"));
            ticketFullInfo.setStationFromId(stationFromId);
            ticketFullInfo.setStationWhereName((rs.getString("stationWhere")));
            ticketFullInfo.setStationWhereId(stationWhereId);
        }
    }

}
