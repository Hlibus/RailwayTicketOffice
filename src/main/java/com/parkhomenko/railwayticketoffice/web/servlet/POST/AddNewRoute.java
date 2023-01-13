package com.parkhomenko.railwayticketoffice.web.servlet.POST;

import com.parkhomenko.railwayticketoffice.entitie.RouteStation;
import com.parkhomenko.railwayticketoffice.service.RouteWithRouteStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addNewRoute")
public class AddNewRoute extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddNewRoute.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String routeName = req.getParameter("routeName");
        logger.debug(routeName);
        String selectedStationsInfo = req.getParameter("selectedStationsInfoWithId");
        logger.debug(selectedStationsInfo);
        try {
            List<RouteStation> routeStationWithNamesDtos = parseSelectedStationsInfo(selectedStationsInfo);
            RouteWithRouteStationService.addNewRouteAndRouteStations(routeName, routeStationWithNamesDtos);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error while update data from DB");
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("getRouteSettings");

        logger.debug("Command finished");
    }

    private List<RouteStation> parseSelectedStationsInfo(String selectedStationsInfo) throws ParseException {
        logger.debug("parseSelectedStationsInfo st");
        List<RouteStation> routeStations = new ArrayList<>();

        String[] rows = selectedStationsInfo.split("\n");
        String[] info;
        RouteStation routeStation;
        int numberCount = 1;
        for(String row : rows){
            logger.debug("count for - "+numberCount);
            info = row.split(" ");
            if(info.length == 4){
                routeStation = new RouteStation();
                logger.debug("info[0] - "+info[0]);
                routeStation.setIdStationFrom(Integer.parseInt(info[0]));
                logger.debug("info[1] - "+info[1]);
                routeStation.setIdStationWhere(Integer.parseInt(info[1]));
                logger.debug("info[2] - "+info[2]);
                routeStation.setPrice(Double.parseDouble(info[2]));
                logger.debug("info[3] - "+info[3]);
                SimpleDateFormat sdf = new java.text.SimpleDateFormat ("HH:mm");
                long ms = sdf.parse(info[3]).getTime();
                routeStation.setTravelTime(new Time(ms));
                routeStation.setNumber(numberCount++);
                routeStations.add(routeStation);
            }
        }
        logger.debug("parseSelectedStationsInfo fin");
        return routeStations;
    }
}
