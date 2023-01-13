package com.parkhomenko.railwayticketoffice.web.servlet.POST;

import com.parkhomenko.railwayticketoffice.entitie.Travel;
import com.parkhomenko.railwayticketoffice.service.TravelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet("/addNewTravel")
public class AddNewTravel extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddNewTravel.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String routeId = req.getParameter("routeId");
        String trainId = req.getParameter("trainId");
        String departureDate = req.getParameter("departureDate");
        if(routeId != null && trainId != null && departureDate != null){
            Travel travel = new Travel();
            travel.setIdTrain(Integer.parseInt(trainId));
            travel.setIdRoute(Integer.parseInt(routeId));
            travel.setDepartureDateTime(Timestamp.valueOf(departureDate.replace("T", " ")+":00"));
            try {
                TravelService.addNewTravel(travel);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("getTravelSettings");

        logger.debug("Command finished");
    }
}
