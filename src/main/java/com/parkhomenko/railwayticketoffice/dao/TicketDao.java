package com.parkhomenko.railwayticketoffice.dao;

import com.parkhomenko.railwayticketoffice.dto.TicketFullInfoDto;
import com.parkhomenko.railwayticketoffice.entitie.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

    private static final String COUNT_OF_LOOSE_PLACES = "SELECT COUNT(seat.id) AS seatsCount " +
            "FROM train, wagon, seat WHERE train.id = wagon.id_train AND wagon.id = seat.id_wagon AND train.id = ? " +
            "AND seat.id NOT IN (SELECT ticket.id_seat FROM ticket, travel WHERE ticket.id_travel = ?)";
    private static final String GET_TICKET_FULL_INFO_BY_ID = "SELECT * FROM \n" +
            "(SELECT route.name AS routeName, train.number AS trainNumber, train.id AS trainId, travel.departure_dateTime AS departureDateTime \n" +
            "FROM travel, route, train WHERE travel.id_route = route.id AND travel.id_train = train.id AND travel.id = ?)\n" +
            "AS table1,\n" +
            "(SELECT stationFrom.name AS stationFrom, stationWhere.name AS stationWhere FROM station AS stationFrom, " +
            "station AS stationWhere WHERE stationFrom.id = ? AND stationWhere.id = ?) AS table2,\n" +
            "(SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(travel_time))) AS timeInTravel, sum(price) AS price FROM routestation \n" +
            "WHERE number >= (SELECT number FROM routestation WHERE id_route = ? AND id_stationFrom = ?)\n" +
            "AND number <= (SELECT number FROM routestation WHERE id_route = ? AND id_stationWhere = ?) AND id_route = ?)\n" +
            "AS table3;";
    private static final String ADD_NEW_TICKET = "INSERT INTO ticket (id_travel, id_stationFrom, id_stationWhere, " +
            "id_user, id_seat, purchase_dateTime, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_USER_TICKETS = "SELECT * FROM ticket WHERE id_user = ? AND status = '1'";

    private static final String GET_TICKET_INFO_FROM_TRAVEL_ROUTE_TRAIN_TABLES = "SELECT route.name AS routeName, " +
            "train.number AS trainNumber, train.id AS trainId, travel.departure_dateTime AS departureDateTime \n" +
            "FROM travel, route, train WHERE travel.id_route = route.id AND travel.id_train = train.id AND travel.id = ?;";

    private static final String GET_TICKET_INFO_FROM_ROUTESTATION = "SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(travel_time))) " +
            "AS timeInTravel, sum(price) AS price FROM routestation \n" +
            "WHERE number >= (SELECT number FROM routestation WHERE id_route = ? AND id_stationFrom = ?)\n" +
            "AND number <= (SELECT number FROM routestation WHERE id_route = ? AND id_stationWhere = ?) AND id_route = ?;";

    public static int countOfLoosePlaces (Connection con, String trainId, String travelId) throws SQLException {
        int res = 0;

        PreparedStatement pstmt = con.prepareStatement(COUNT_OF_LOOSE_PLACES);
        pstmt.setString(1, trainId);
        pstmt.setString(2, travelId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            res = rs.getInt("seatsCount");
        }

        return res;
    }

    public static boolean addNewTicket (Connection con, Ticket ticket) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(ADD_NEW_TICKET);
        pstmt.setInt(1, ticket.getIdTravel());
        pstmt.setInt(2, ticket.getIdStationFrom());
        pstmt.setInt(3, ticket.getIdStationWhere());
        pstmt.setInt(4, ticket.getIdUser());
        pstmt.setInt(5, ticket.getIdSeat());
        pstmt.setDate(6, ticket.getPurchaseDateTime());
        pstmt.setString(7, ticket.getStatus());
        return pstmt.executeUpdate() > 0;
    }

    public static List<Ticket> getUserActualTickets(Connection con, int userId) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement(GET_ALL_USER_TICKETS);
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        extractTickets(rs, tickets);
        return tickets;
    }

    public static TicketFullInfoDto getTicketFullInfo(Connection con, String travelId, String stationFromId,
                                                      String stationWhereId, long routeId) throws SQLException {
        TicketFullInfoDto ticket = new TicketFullInfoDto();

        PreparedStatement pstmt = con.prepareStatement(GET_TICKET_FULL_INFO_BY_ID);
        pstmt.setString(1, travelId);
        pstmt.setString(2, stationFromId);
        pstmt.setString(3, stationWhereId);
        pstmt.setLong(4, routeId);
        pstmt.setString(5, stationFromId);
        pstmt.setLong(6, routeId);
        pstmt.setString(7, stationWhereId);
        pstmt.setLong(8, routeId);
        ResultSet rs = pstmt.executeQuery();
        extractTicketFullInfo(rs, ticket);
        ticket.setTravelId(Integer.parseInt(travelId));
        ticket.setStationFromId(Integer.parseInt(stationFromId));
        ticket.setStationWhereId(Integer.parseInt(stationWhereId));

        return ticket;
    }

    private static void extractTickets(ResultSet rs, List<Ticket> tickets) throws SQLException {
        while (rs.next()){
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setIdTravel(rs.getInt("id_travel"));
            ticket.setIdStationFrom(rs.getInt("id_stationFrom"));
            ticket.setIdStationWhere(rs.getInt("id_stationWhere"));
            ticket.setIdUser(rs.getInt("id_user"));
            ticket.setIdSeat(rs.getInt("id_seat"));
            ticket.setPurchaseDateTime(rs.getDate("purchase_dateTime"));
            ticket.setStatus(rs.getString("status"));
            tickets.add(ticket);
        }
    }

    private static void extractTicketFullInfo(ResultSet rs, TicketFullInfoDto ticketFullInfo) throws SQLException {
        if(rs.next()){
            ticketFullInfo.setRouteName(rs.getString("routeName"));
            ticketFullInfo.setStationFromName(rs.getString("stationFrom"));
            ticketFullInfo.setStationWhereName(rs.getString("stationWhere"));
            ticketFullInfo.setTrainNumber(rs.getInt("trainNumber"));
            ticketFullInfo.setTrainId(rs.getInt("trainId"));
            ticketFullInfo.setDepartureDateTime(rs.getTimestamp("departureDateTime"));
            ticketFullInfo.setTimeInTravel(rs.getTime("timeInTravel"));
            ticketFullInfo.setPrice(rs.getDouble("price"));
        }

    }

}
