package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.TicketDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.dto.TicketFullInfoDto;
import com.parkhomenko.railwayticketoffice.entitie.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TicketService {

    public static int getCountOfLoosePlaces(String trainId, String travelId) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        int res = TicketDao.countOfLoosePlaces(con, trainId, travelId);
        con.close();
        return res;
    }

    public static TicketFullInfoDto getTicketFullInfo(String travelId, String stationFromId, String stationWhereId, long routeId)
            throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        TicketFullInfoDto ticket = TicketDao.getTicketFullInfo(con, travelId, stationFromId, stationWhereId, routeId);
        con.close();
        return ticket;
    }



    public static boolean addNewTicket(Ticket ticket) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        boolean res =  TicketDao.addNewTicket(con, ticket);
        con.close();
        return res;
    }

    public static List<Ticket> getUserActualTickets(int userId) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        List<Ticket> tickets =  TicketDao.getUserActualTickets(con, userId);
        con.close();
        return tickets;
    }

}
