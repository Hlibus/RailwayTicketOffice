package com.parkhomenko.railwayticketoffice.web.servlet.GET;

import com.parkhomenko.railwayticketoffice.dto.RouteStationWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.Station;
import com.parkhomenko.railwayticketoffice.service.RouteStationService;
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
import java.util.List;

@WebServlet("/getRouteEdit")
public class GetRouteEdit extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetRouteEdit.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        Object roleId = req.getSession().getAttribute("roleId");
        if(roleId == null || !roleId.equals(2)){
            req.getRequestDispatcher(Path.PAGE_ACCESS_DENIED).forward(req, resp);
        }else{
            String routeId = req.getParameter("routeId");
            List<RouteStationWithNamesDto> routesStations = null;
            List<Station> stations = null;
            try {
                routesStations = RouteStationService.getRouteStationWithNamesByRouteId(routeId);
                stations = StationService.getAllStations();
            } catch (SQLException | ClassNotFoundException e) {
                logger.error("Error in getting data from DB");
                e.printStackTrace();
            }
            if(routesStations == null || routeId == null || stations == null){
                logger.error("Attribute is null");
                resp.sendRedirect("getRouteSettings");
            }
            req.setAttribute("routesStations", routesStations);
            req.setAttribute("routeId", routeId);
            req.setAttribute("stations", stations);
            req.getRequestDispatcher(Path.PAGE_MANAGER_ROUTE_EDIT).forward(req, resp);
        }

        logger.debug("Command finished");
    }
}
