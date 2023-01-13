package com.parkhomenko.railwayticketoffice.web.servlet.GET;

import com.parkhomenko.railwayticketoffice.dto.RouteStationWithNamesDto;
import com.parkhomenko.railwayticketoffice.dto.TrainWithInfoDto;
import com.parkhomenko.railwayticketoffice.dto.TravelWithNamesDto;
import com.parkhomenko.railwayticketoffice.entitie.Travel;
import com.parkhomenko.railwayticketoffice.service.RouteStationService;
import com.parkhomenko.railwayticketoffice.service.TicketService;
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
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/getTravelPage")
public class GetTravelPage extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetTravelPage.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String travelId = req.getParameter("travelId");
        TravelWithNamesDto travelWithNames = null;
        Travel travel;
        TrainWithInfoDto trainWithInfoDto = null;
        List<RouteStationWithNamesDto> routeStationWithNames = null;
        int loosePlaces = 0;

        double travelGeneralPrice = 0;
        Time travelGeneralTime = null;
        try {
            travelWithNames = TravelService.getTravelWithNamesById(travelId);
            travel = TravelService.getTravelById(travelId);
            trainWithInfoDto = TrainService.getTrainWithInfoById(String.valueOf(travel.getIdTrain()));
            routeStationWithNames = RouteStationService.getRouteStationWithNamesByRouteId(String.valueOf(travel.getIdRoute()));
            loosePlaces = TicketService.getCountOfLoosePlaces(String.valueOf(trainWithInfoDto.getId()), travelId);

            Date dt0;
            dt0 = new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01");
            final DateFormat dt = new SimpleDateFormat("HH:mm:ss");
            final Calendar c = Calendar.getInstance();
            c.setTimeInMillis(0);
            if (routeStationWithNames.size() != 0) {
                for(RouteStationWithNamesDto routeStationWithNamesDto : routeStationWithNames){
                    travelGeneralPrice += routeStationWithNamesDto.getPrice();
                    c.add(Calendar.MILLISECOND, (int) dt.parse(routeStationWithNamesDto.getTravelTime().toString()).getTime());
                    c.add(Calendar.MILLISECOND, (int)-dt0.getTime());
                }
            }
            c.add(Calendar.MILLISECOND, (int)dt0.getTime());
            travelGeneralTime = Time.valueOf(new SimpleDateFormat("HH:mm:ss").format(c.getTime()));
        } catch (ParseException | SQLException | ClassNotFoundException e) {
            logger.error("Error in getting data from DB");
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(travelWithNames != null ? travelWithNames.getDepartureDateTime() : null);
        calendar.add(Calendar.HOUR_OF_DAY, travelGeneralTime.getHours());
        calendar.add(Calendar.MINUTE, travelGeneralTime.getMinutes());
        calendar.add(Calendar.SECOND, travelGeneralTime.getSeconds());
        //calendar.add(Calendar.HOUR_OF_DAY, 3);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date travelArrivalTime = calendar.getTime();

        req.setAttribute("travelWithNames", travelWithNames);
        req.setAttribute("trainWithInfoDto", trainWithInfoDto);
        req.setAttribute("routeStationWithNames", routeStationWithNames);
        req.setAttribute("travelGeneralPrice", travelGeneralPrice);
        req.setAttribute("travelGeneralTime", travelGeneralTime);
        req.setAttribute("travelArrivalTime", sdf.format(travelArrivalTime));
        req.setAttribute("LoosePlaces", loosePlaces);

        //logger.debug(travel);
        req.getRequestDispatcher(Path.PAGE_USER_TRAVEL).forward(req, resp);

        logger.debug("Command finished");
    }

}
