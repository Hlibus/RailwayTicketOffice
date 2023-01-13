package com.parkhomenko.railwayticketoffice.web.servlet.GET;

import com.parkhomenko.railwayticketoffice.dto.TravelWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.Route;
import com.parkhomenko.railwayticketoffice.entitie.Train;
import com.parkhomenko.railwayticketoffice.entitie.Travel;
import com.parkhomenko.railwayticketoffice.service.RouteService;
import com.parkhomenko.railwayticketoffice.service.TrainService;
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

@WebServlet("/getTravelSettings")
public class GetTravelSettings extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetTravelSettings.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        Object roleId = req.getSession().getAttribute("roleId");
        if(roleId == null || !roleId.equals(2)){
            req.getRequestDispatcher(Path.PAGE_ACCESS_DENIED).forward(req, resp);
        }else{
            List<TravelWithNamesDto> travelWithNames = null;
            List<Route> routes = null;
            List<Train> trains = null;
            try {
                travelWithNames = TravelService.getActualTravelsWithNames();
                routes = RouteService.getAllRoutes();
                trains = TrainService.getAllTrains();
            } catch (SQLException | ClassNotFoundException e) {
                logger.error("Error in getting data from DB");
                e.printStackTrace();
            }
            req.setAttribute("travelWithNames", travelWithNames);
            req.setAttribute("routes", routes);
            req.setAttribute("trains", trains);
            req.getRequestDispatcher(Path.PAGE_TRAVEL_SETTINGS).forward(req, resp);
        }

        logger.debug("Command finished");
    }
}
