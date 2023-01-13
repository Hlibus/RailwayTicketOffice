package com.parkhomenko.railwayticketoffice.web.servlet.POST;

import com.parkhomenko.railwayticketoffice.service.RouteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteRoute")
public class DeleteRoute extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteRoute.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String routeId = req.getParameter("routeId");
        if (routeId != null) {
            try {
                RouteService.deleteRoute(routeId);
            } catch (SQLException | ClassNotFoundException e) {
                logger.error("Error in update data in DB");
                e.printStackTrace();
            }
        }else{
            logger.error("routeId is null");
        }
        resp.sendRedirect("getRouteSettings");

        logger.debug("Command finished");
    }
}
