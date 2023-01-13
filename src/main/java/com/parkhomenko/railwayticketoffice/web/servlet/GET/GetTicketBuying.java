package com.parkhomenko.railwayticketoffice.web.servlet.GET;

import com.parkhomenko.railwayticketoffice.dao.WagonDao;
import com.parkhomenko.railwayticketoffice.dto.TicketFullInfoDto;
import com.parkhomenko.railwayticketoffice.entitie.Seat;
import com.parkhomenko.railwayticketoffice.entitie.Ticket;
import com.parkhomenko.railwayticketoffice.entitie.Wagon;
import com.parkhomenko.railwayticketoffice.service.SeatService;
import com.parkhomenko.railwayticketoffice.service.TicketService;
import com.parkhomenko.railwayticketoffice.service.TravelService;
import com.parkhomenko.railwayticketoffice.service.WagonService;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/getTicketBuyingPage")
public class GetTicketBuying extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetTicketBuying.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String stationFromId = req.getParameter("stationFromId");
        String stationWhereId = req.getParameter("stationWhereId");
        String travelId = req.getParameter("travelId");
        TicketFullInfoDto ticketFullInfo = null;
        //Seat seat = null;
        try {
            long routeId = TravelService.getRouteId(travelId);
            ticketFullInfo = TicketService.getTicketFullInfo(travelId, stationFromId, stationWhereId, routeId);
            //seat = SeatService.getFreeSeat(ticketFullInfo.getTrainId(), Integer.parseInt(travelId));
            //List<Wagon> wagons = WagonService.getLooseWagons(String.valueOf(ticketFullInfo.getTrainId()), travelId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(ticketFullInfo != null){
            Time travelGeneralTime = ticketFullInfo.getTimeInTravel();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ticketFullInfo.getDepartureDateTime());
            calendar.add(Calendar.HOUR_OF_DAY, travelGeneralTime.getHours());
            calendar.add(Calendar.MINUTE, travelGeneralTime.getMinutes());
            calendar.add(Calendar.SECOND, travelGeneralTime.getSeconds());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date travelArrivalTime = calendar.getTime();
            ticketFullInfo.setArrivalDateTime(sdf.format(travelArrivalTime));

            req.setAttribute("ticketFullInfo", ticketFullInfo);
            req.getRequestDispatcher(Path.PAGE_USER_TICKET_BUYING_PAGE).forward(req, resp);
        }

        logger.debug("Command finished");
    }
}
