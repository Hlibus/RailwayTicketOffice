package com.parkhomenko.railwayticketoffice.web.servlet.GET;

import com.parkhomenko.railwayticketoffice.entitie.Station;
import com.parkhomenko.railwayticketoffice.service.StationService;
import com.parkhomenko.railwayticketoffice.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/getStationEdit")
public class GetStationEdit extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetStationEdit.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        Object roleId = req.getSession().getAttribute("roleId");
        if(roleId == null || !roleId.equals(2)){
            req.getRequestDispatcher(Path.PAGE_ACCESS_DENIED).forward(req, resp);
        }else{
            String stationId = req.getParameter("stationId");
            Station station = null;
            try {
                station = StationService.getStationById(stationId);
            } catch (SQLException | ClassNotFoundException e) {
                logger.error("Error in getting data from DB");
                e.printStackTrace();
            }

            req.setAttribute("station", station);
            req.getRequestDispatcher(Path.PAGE_STATION_EDIT).forward(req, resp);
        }

        logger.debug("Command finished");
    }
}
