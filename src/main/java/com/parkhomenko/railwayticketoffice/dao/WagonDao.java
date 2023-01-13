package com.parkhomenko.railwayticketoffice.dao;

import com.parkhomenko.railwayticketoffice.entitie.Wagon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WagonDao {

    private static final String ADD_NEW = "INSERT INTO wagon (number, id_train) VALUES (?, ?)";
    private static final String GET_LOOSE_WAGONS = "SELECT wagon.id, wagon.number, COUNT(seat.id) AS seatCount" +
            "FROM wagon, seat where wagon.id_train = ? AND seat.id_wagon = wagon.id " +
            "AND seat.id NOT IN (SELECT id_seat FROM ticket WHERE id_travel = ?) GROUP BY wagon.id";

    public static long addNew(Connection con, int number, long id_train) throws SQLException {
        long id = -1;

        PreparedStatement pstmt = con.prepareStatement(ADD_NEW, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, number);
        pstmt.setLong(2, id_train);
        int affectedRows = pstmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        return id;
    }

    public static List<Wagon> getLooseWagons(Connection con, String trainId, String travelId) throws SQLException {
        List<Wagon> wagons = new ArrayList<>();

        PreparedStatement pstmt = con.prepareStatement(GET_LOOSE_WAGONS);
        pstmt.setString(1, trainId);
        pstmt.setString(2, travelId);
        ResultSet rs =  pstmt.executeQuery();
        extractWagons(rs, wagons, trainId);

        return wagons;
    }

    private static void extractWagons(ResultSet rs, List<Wagon> wagons, String trainId) throws SQLException {
        while(rs.next()){
            if(rs.getInt("seatCount") > 0){
                Wagon wagon = new Wagon();
                wagon.setId(rs.getInt("id"));
                wagon.setNumber(rs.getInt("number"));
                wagon.setIdTrain(Integer.parseInt(trainId));
                wagons.add(wagon);
            }
        }
    }

}
