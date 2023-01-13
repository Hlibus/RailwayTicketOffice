package com.parkhomenko.railwayticketoffice.web.servlet.POST;

import com.parkhomenko.railwayticketoffice.entitie.RouteStation;
import com.parkhomenko.railwayticketoffice.service.RouteStationService;
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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/editRouteStation")
public class EditRouteStation extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(EditRoute.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        RouteStation routeStation;
        String routeStationsId = req.getParameter("routeStationsId");
        String stationFromName = req.getParameter("stationFromName");
        String stationWhereName = req.getParameter("stationWhereName");
        String price = req.getParameter("price");
        String time = req.getParameter("time");
        String number = req.getParameter("number");
        String routeId = req.getParameter("routeId");

        try {
            int nextId = RouteStationService.hasNextStations(routeId, number);


            int stationFromId = StationService.findStationIdByName(stationFromName);
            int stationWhereId = StationService.findStationIdByName(stationWhereName);
            logger.debug("фром"+stationFromName);
            logger.debug("фромАйди"+stationFromId);
            logger.debug("вер"+stationWhereName);
            logger.debug("верАйди"+stationWhereId);
            routeStation = setAllData(routeStationsId, stationFromId, stationWhereId, price, time);
            if(stationFromId != -1 && stationWhereId != -1 && routeStationsId != null && price != null && time != null){
                if(nextId != -1){
                    RouteStationService.updateRouteStationWithNext(routeStation, nextId);
                }else{
                    RouteStationService.updateRouteStation(routeStation);
                }
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("getRouteSettings");

        logger.debug("Command finished");
    }

    private RouteStation setAllData(String routeStationsId, int stationFromId, int stationWhereId,
                                    String price, String time) throws ParseException {
        RouteStation routeStation = new RouteStation();

        routeStation.setId(Integer.parseInt(routeStationsId));
        routeStation.setIdStationFrom(stationFromId);
        routeStation.setIdStationWhere(stationWhereId);
        routeStation.setPrice(Double.parseDouble(price));
        SimpleDateFormat sdf = new java.text.SimpleDateFormat ("HH:mm");
        long ms = sdf.parse(time).getTime();
        routeStation.setTravelTime(new Time(ms));

        return routeStation;
    }
}
