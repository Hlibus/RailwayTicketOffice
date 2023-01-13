package com.parkhomenko.railwayticketoffice.web.servlet.POST;

import com.parkhomenko.railwayticketoffice.service.TravelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteTravel")
public class DeleteTravel extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddNewTravel.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String travelId = req.getParameter("travelId");
        if(travelId != null){
            try {
                TravelService.deleteTravel(Integer.parseInt(travelId));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("getTravelSettings");

        logger.debug("Command finished");
    }
}
