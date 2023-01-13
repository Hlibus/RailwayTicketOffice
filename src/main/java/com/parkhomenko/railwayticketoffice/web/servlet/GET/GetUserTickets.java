package com.parkhomenko.railwayticketoffice.web.servlet.GET;

import com.parkhomenko.railwayticketoffice.entitie.Ticket;
import com.parkhomenko.railwayticketoffice.entitie.User;
import com.parkhomenko.railwayticketoffice.service.TicketService;
import com.parkhomenko.railwayticketoffice.service.UserService;
import com.parkhomenko.railwayticketoffice.web.Path;
import com.parkhomenko.railwayticketoffice.web.servlet.POST.BuyTicket;
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
import java.util.List;

@WebServlet("/getUserTickets")
public class GetUserTickets extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(BuyTicket.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        HttpSession session = req.getSession();
        String userLogin = String.valueOf(session.getAttribute("login"));
        List<Ticket> actualTickets = null;
        try {
            User user = UserService.getUserByLogin(userLogin);
            actualTickets = TicketService.getUserActualTickets(user.getId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("actualTickets", actualTickets);
        req.getRequestDispatcher(Path.PAGE_USER_TICKETS).forward(req, resp);

        logger.debug("Command finished");
    }
}
