package com.parkhomenko.railwayticketoffice.web.servlet.GET;

import com.parkhomenko.railwayticketoffice.dto.TravelWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.Station;
import com.parkhomenko.railwayticketoffice.service.StationService;
import com.parkhomenko.railwayticketoffice.service.TravelService;
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
import java.util.List;

@WebServlet("/getSelectedTravels")
public class GetSelectedTravels extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetSelectedTravels.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String stationFrom = req.getParameter("stationFrom");
        String stationWhere = req.getParameter("stationWhere");
        List<TravelWithNamesDto> travels = null;
        List<Station> stations = null;
        try {
            travels = TravelService.getActualTravelsWithNames();
            stations = StationService.getAllStations();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error in getting data from DB");
            e.printStackTrace();
        }
        req.setAttribute("travels", travels);
        req.setAttribute("stations", stations);
        req.getRequestDispatcher(Path.PAGE_MAIN).forward(req, resp);

        logger.debug("Command finished");
    }

}
