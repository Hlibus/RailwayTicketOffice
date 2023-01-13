package com.parkhomenko.railwayticketoffice.web.servlet.POST;

import com.parkhomenko.railwayticketoffice.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addNewStation")
public class AddNewStation extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddNewStation.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String stationName = req.getParameter("stationName");
        String stationAddress = req.getParameter("stationAddress");
        String stationCoordinates = req.getParameter("stationCoordinates");
        try {
            StationService.addNewStation(stationName, stationAddress, stationCoordinates);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error in update data in DB");
            e.printStackTrace();
        }

        resp.sendRedirect("getStationSettings");

        logger.debug("Command finished");
    }
}
