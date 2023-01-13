package com.parkhomenko.railwayticketoffice.dao;

import com.parkhomenko.railwayticketoffice.dto.TrainWithInfoDto;
import com.parkhomenko.railwayticketoffice.entitie.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDao {

    private static final String FIND_ALL_TRAINS = "SELECT * FROM train";
    private static final String FIND_ALL_TRAINS_WITH_INFO = "SELECT table1.id, table1.number, table1.wagonsCount, " +
            "table2.seatsCount FROM (SELECT train.id, train.number, COUNT(wagon.id) AS wagonsCount FROM train, wagon " +
            "WHERE train.id = wagon.id_train GROUP BY train.id) AS table1 JOIN (SELECT train.id, COUNT(seat.id) AS seatsCount " +
            "FROM train, wagon, seat WHERE train.id = wagon.id_train AND wagon.id = seat.id_wagon GROUP BY train.id) AS table2 " +
            "ON table1.id = table2.id";
    private static final String FIND_TRAINS_WITH_INFO_BY_ID = "SELECT table1.id, table1.number, table1.wagonsCount, " +
            "table2.seatsCount FROM (SELECT train.id, train.number, COUNT(wagon.id) AS wagonsCount FROM train, wagon " +
            "WHERE train.id = wagon.id_train AND train.id = ?) AS table1 JOIN (SELECT DISTINCT train.id, COUNT(seat.id) AS seatsCount " +
            "FROM train, wagon, seat WHERE train.id = wagon.id_train AND wagon.id = seat.id_wagon AND train.id = ?) AS table2 " +
            "ON table1.id = table2.id";
    private static final String ADD_NEW = "INSERT INTO train (number) VALUES (?)";
    private static final String DELETE_TRAIN = "DELETE FROM train WHERE id = ?";
    private static final String FIND_FREE_TRAINS = "SELECT * FROM train WHERE id NOT IN (SELECT id_train FROM travel " +
            "WHERE departure_dateTime > CURRENT_TIMESTAMP())";

    public static List<Train> findAll(Connection con) throws SQLException {
        List<Train> trains = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(FIND_ALL_TRAINS);
        extractTrains(rs, trains);

        return trains;
    }

    public static List<Train> findFreeTrains(Connection con) throws SQLException {
        List<Train> trains = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(FIND_FREE_TRAINS);
        extractTrains(rs, trains);

        return trains;
    }

    public static List<TrainWithInfoDto> findAllTrainsWithInfo(Connection con) throws SQLException {
        List<TrainWithInfoDto> trains = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(FIND_ALL_TRAINS_WITH_INFO);
        extractTrainsWithInfo(rs, trains);

        return trains;
    }

    public static TrainWithInfoDto findTrainWithInfoById(Connection con, String id) throws SQLException {
        TrainWithInfoDto trainWithInfoDto = new TrainWithInfoDto();

        PreparedStatement pstmt = con.prepareStatement(FIND_TRAINS_WITH_INFO_BY_ID);
        pstmt.setString(1, id);
        pstmt.setString(2, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            extractTrainWithInfo(rs, trainWithInfoDto);
        }

        return trainWithInfoDto;
    }

    public static long addNew(Connection con, String number) throws SQLException {
        long id= -1;

        PreparedStatement pstmt = con.prepareStatement(ADD_NEW, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, number);
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

    public static void delete(Connection con, String id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(DELETE_TRAIN);
        pstmt.setString(1, id);
        pstmt.executeUpdate();
    }

    private static void extractTrains(ResultSet rs, List<Train> trains) throws SQLException{
        Train train;
        while (rs.next()){
            train = new Train();
            train.setId(rs.getInt("id"));
            train.setNumber(rs.getInt("number"));
            trains.add(train);
        }
    }

    private static void extractTrainsWithInfo(ResultSet rs, List<TrainWithInfoDto> trains) throws SQLException{
        TrainWithInfoDto trainWithInfoDto;
        while (rs.next()){
            trainWithInfoDto = new TrainWithInfoDto();
            extractTrainWithInfo(rs, trainWithInfoDto);
            trains.add(trainWithInfoDto);
        }
    }

    private static void extractTrainWithInfo(ResultSet rs, TrainWithInfoDto trainWithInfoDto) throws SQLException{
        trainWithInfoDto.setId(rs.getInt("id"));
        trainWithInfoDto.setNumber(rs.getInt("number"));
        trainWithInfoDto.setWagonsCount(rs.getInt("wagonsCount"));
        trainWithInfoDto.setSeatsCount(rs.getInt("seatsCount"));
    }

}
