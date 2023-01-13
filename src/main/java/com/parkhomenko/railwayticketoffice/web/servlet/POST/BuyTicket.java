package com.parkhomenko.railwayticketoffice.web.servlet.POST;

import com.parkhomenko.railwayticketoffice.entitie.Seat;
import com.parkhomenko.railwayticketoffice.entitie.Ticket;
import com.parkhomenko.railwayticketoffice.entitie.User;
import com.parkhomenko.railwayticketoffice.service.SeatService;
import com.parkhomenko.railwayticketoffice.service.TicketService;
import com.parkhomenko.railwayticketoffice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

@WebServlet("/buyTicket")
public class BuyTicket extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(BuyTicket.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");
        String redirect = "main";

        String travelId = req.getParameter("travelId");
        logger.debug("travelId " + travelId);
        String stationFromId = req.getParameter("stationFromId");
        logger.debug("stationFromId " + stationFromId);
        String stationWhereId = req.getParameter("stationWhereId");
        logger.debug("stationWhereId " + stationWhereId);
        String trainId = req.getParameter("trainId");
        logger.debug("trainId " + trainId);
        String status = "1";//1 - actual ticket
        HttpSession session = req.getSession();
        String userLogin = String.valueOf(session.getAttribute("login"));
        try {
            User user = UserService.getUserByLogin(userLogin);
            Seat seat = SeatService.getFreeSeat(trainId, travelId);
            if (user == null) {
                throw new SQLException("Error: cant find user");
            } else if (seat == null) {
                throw new SQLException("Error: there are no free places");
            } else {
                java.util.Date utilDate = new java.util.Date();
                Date purchaseDateTime = new java.sql.Date(utilDate.getTime());
                Ticket newTicket = new Ticket();
                newTicket.setIdTravel(Integer.parseInt(travelId));
                newTicket.setIdStationFrom(Integer.parseInt(stationFromId));
                newTicket.setIdStationWhere(Integer.parseInt(stationWhereId));
                newTicket.setIdUser(user.getId());
                newTicket.setIdSeat(seat.getId());
                newTicket.setPurchaseDateTime(purchaseDateTime);
                newTicket.setStatus(status);
                TicketService.addNewTicket(newTicket);
                redirect = "getUserTickets";
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(redirect);

        logger.debug("Command finished");
    }
}
