package com.parkhomenko.railwayticketoffice.dao;

import com.parkhomenko.railwayticketoffice.dto.TravelWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.Travel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelDao {

    private static final String FIND_ALL_TRAVELS = "SELECT * FROM travel";
    private static final String FIND_ALL_TRAVEL_BY_ID = "SELECT * FROM travel WHERE id = ?";
    private static final String FIND_ALL_TRAVELS_WITH_NAMES = "SELECT travel.id, route.name AS routeName, train.number " +
            "AS trainNumber, travel.departure_dateTime FROM travel, route, train WHERE travel.id_route = route.id " +
            "AND travel.id_train = train.id";
    private static final String FIND_ACTUAL_TRAVELS_WITH_NAMES = "SELECT travel.id, route.name AS routeName, train.number " +
            "AS trainNumber, travel.departure_dateTime FROM travel, route, train WHERE travel.id_route = route.id " +
            "AND travel.id_train = train.id AND travel.departure_dateTime > CURRENT_TIMESTAMP()";
    private static final String FIND_TRAVELS_WITH_NAMES_BY_ID = "SELECT travel.id, route.name AS routeName, train.number" +
            " AS trainNumber, travel.departure_dateTime FROM travel, route, train WHERE travel.id_route = route.id " +
            "AND travel.id_train = train.id AND travel.id = ?";
    private static final String ADD_NEW_TRAVEL = "INSERT INTO travel (id_route, id_train, departure_dateTime) VALUES (?, ?, ?)";
    private static final String DELETE_TRAVEL = "DELETE FROM travel WHERE id = ?";
    private static final String FIND_ALL_TRAVELS_WITH_NAMES_BY_STATIONS = "SELECT * FROM travel";
    private static final String GET_ROUTE_ID = "SELECT id_route FROM travel WHERE id = ?";

    public static TravelWithNamesDto findTravelsWithNamesById(Connection con, String id) throws SQLException {
        TravelWithNamesDto travelWithName = new TravelWithNamesDto();

        PreparedStatement pstmt = con.prepareStatement(FIND_TRAVELS_WITH_NAMES_BY_ID);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            extractTravelWithName(rs, travelWithName);
        }

        return travelWithName;
    }

    public static List<Travel> findAll(Connection con) throws SQLException {
        List<Travel> travels = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(FIND_ALL_TRAVELS);
        extractTravels(rs, travels);

        return travels;
    }

    public static long getRouteId(Connection con, String travelId) throws SQLException {
        long res = -1;

        PreparedStatement pstmt = con.prepareStatement(GET_ROUTE_ID);
        pstmt.setString(1, travelId);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            res = rs.getLong("id_route");
        }

        return res;
    }

    public static void addNewTravel(Connection con, Travel travel) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(ADD_NEW_TRAVEL);
        pstmt.setInt(1, travel.getIdRoute());
        pstmt.setInt(2, travel.getIdTrain());
        pstmt.setTimestamp(3, travel.getDepartureDateTime());
        pstmt.executeUpdate();
    }

    public static void deleteTravel(Connection con, int travelId) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(DELETE_TRAVEL);
        pstmt.setInt(1, travelId);
        pstmt.executeUpdate();
    }

    public static Travel findTravelById(Connection con, String id) throws SQLException {
        Travel travel = new Travel();

        PreparedStatement pstmt = con.prepareStatement(FIND_ALL_TRAVEL_BY_ID);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            extractTravel(rs, travel);
        }

        return travel;
    }

    public static List<TravelWithNamesDto> findAllTravelsWithNames(Connection con) throws SQLException {
        List<TravelWithNamesDto> travelsWithNames = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(FIND_ALL_TRAVELS_WITH_NAMES);
        extractTravelsWithNames(rs, travelsWithNames);

        return travelsWithNames;
    }

    public static List<TravelWithNamesDto> findTravelsBySelectedStations(Connection con, int fromId, int whereId) throws SQLException {
        List<TravelWithNamesDto> travels = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(FIND_ALL_TRAVELS_WITH_NAMES_BY_STATIONS);
        extractTravelsWithNames(rs, travels);

        return travels;
    }

    public static List<TravelWithNamesDto> findActualTravelsWithNames(Connection con) throws SQLException {
        List<TravelWithNamesDto> travelsWithNames = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(FIND_ACTUAL_TRAVELS_WITH_NAMES);
        extractTravelsWithNames(rs, travelsWithNames);

        return travelsWithNames;
    }

    private static void extractTravels(ResultSet rs, List<Travel> travels) throws SQLException {
        Travel travel;
        while (rs.next()) {
            travel = new Travel();
            extractTravel(rs, travel);
            travels.add(travel);
        }
    }

    private static void extractTravel(ResultSet rs, Travel travel) throws SQLException {
        travel.setId(rs.getInt("id"));
        travel.setIdRoute(rs.getInt("id_route"));
        travel.setIdTrain(rs.getInt("id_train"));
        travel.setDepartureDateTime(rs.getTimestamp("departure_dateTime"));
    }

    private static void extractTravelsWithNames(ResultSet rs, List<TravelWithNamesDto> travelsWithNames) throws SQLException {
        TravelWithNamesDto travelsWithName = null;
        while (rs.next()) {
            travelsWithName = new TravelWithNamesDto();
            extractTravelWithName(rs, travelsWithName);
            travelsWithNames.add(travelsWithName);
        }
    }

    private static void extractTravelWithName(ResultSet rs, TravelWithNamesDto travelWithName) throws SQLException {
        travelWithName.setId(rs.getInt("id"));
        travelWithName.setRouteName(rs.getString("routeName"));
        travelWithName.setTrainNumber(rs.getInt("trainNumber"));
        travelWithName.setDepartureDateTime(rs.getTimestamp("departure_dateTime"));
    }

}
