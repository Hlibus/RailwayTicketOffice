package com.parkhomenko.railwayticketoffice.dao;

import com.parkhomenko.railwayticketoffice.entitie.Seat;
import com.parkhomenko.railwayticketoffice.entitie.Wagon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatDao {

    private static final String ADD_NEW = "INSERT INTO seat (id_wagon, number) VALUES (?, ?)";
    private static final String GET_FREE_SEAT_BY_TRAIN_AND_TRAVEL_ID = "SELECT DISTINCT seat.id, seat.id_wagon, seat.number \n" +
            "FROM seat, wagon \n" +
            "WHERE seat.id_wagon = wagon.id AND wagon.id_train = ? \n" +
            "AND seat.id NOT IN (SELECT id_seat FROM ticket WHERE id_travel = ?)";

    public static void addNew(Connection con, long wagonId, int number) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(ADD_NEW);
        pstmt.setLong(1, wagonId);
        pstmt.setInt(2, number);
        pstmt.executeUpdate();
    }

    public static Seat getFreeSeat(Connection con, String trainId, String travelId) throws SQLException {
        Seat seat = null;

        PreparedStatement pstmt = con.prepareStatement(GET_FREE_SEAT_BY_TRAIN_AND_TRAVEL_ID);
        pstmt.setString(1, trainId);
        pstmt.setString(2, travelId);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            seat = new Seat();
            seat.setId(rs.getInt("id"));
            seat.setIdWagon(rs.getInt("id_wagon"));
            seat.setNumber(rs.getInt("number"));
        }

        return seat;
    }

}
