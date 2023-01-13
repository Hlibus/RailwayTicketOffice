package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.SeatDao;
import com.parkhomenko.railwayticketoffice.dao.TrainDao;
import com.parkhomenko.railwayticketoffice.dao.WagonDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.dto.TrainWithInfoDto;
import com.parkhomenko.railwayticketoffice.entitie.Train;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TrainService {

    public static List<Train> getAllTrains() throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        List<Train> trains = TrainDao.findAll(con);
        con.close();
        return trains;
    }

    public static List<Train> getFreeTrains() throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        List<Train> trains = TrainDao.findFreeTrains(con);
        con.close();
        return trains;
    }

    public static List<TrainWithInfoDto> getAllTrainsWithInfo() throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        List<TrainWithInfoDto> trainWithInfoDtos = TrainDao.findAllTrainsWithInfo(con);
        con.close();
        return trainWithInfoDtos;
    }

    public static TrainWithInfoDto getTrainWithInfoById(String id) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        TrainWithInfoDto trainWithInfoDto = TrainDao.findTrainWithInfoById(con, id);
        con.close();
        return trainWithInfoDto;
    }

    //Operations with 3 tables: train, wagon, seat
    public static long addNewTrainWithWagonsAndSeats(String trainNumber, int[][] countWagonsAndSeats) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        con.setAutoCommit(false);

        long newTrainId;
        try{
            //new train
            newTrainId = TrainDao.addNew(con, trainNumber);
            for(int[] countWagonAndSeats : countWagonsAndSeats){
                for (int i = 1; i <= countWagonAndSeats[0]; i++) {
                    //new wagon
                    long newWagonId = WagonDao.addNew(con, i, newTrainId);
                    for (int j = 1; j <= countWagonAndSeats[1]; j++) {
                        //new seat
                        SeatDao.addNew(con, newWagonId, j);
                    }
                }
            }
            con.commit();
        }catch (SQLException e){
            con.rollback();
            e.printStackTrace();
            throw new SQLException();
        }finally {
            con.close();
        }

        //long newWagonId = WagonDao.addNew(con, number, newTrainId);

        return newTrainId;
    }

    public static void deleteTrain(String id) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        TrainDao.delete(con, id);
        con.close();
    }

}
