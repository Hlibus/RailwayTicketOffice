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

@WebServlet("/editStation")
public class EditStation extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(EditStation.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String id = req.getParameter("id");
        String stationName = req.getParameter("stationName");
        String stationAddress = req.getParameter("stationAddress");
        String stationCoordinates = req.getParameter("stationCoordinates");

        try {
            StationService.update(id, stationName, stationAddress, stationCoordinates);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error in update data in DB");
            e.printStackTrace();
        }

        resp.sendRedirect("getStationSettings");

        logger.debug("Command finished");
    }
}
