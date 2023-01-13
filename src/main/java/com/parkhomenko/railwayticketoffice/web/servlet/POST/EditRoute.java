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

@WebServlet("/editRoute")
public class EditRoute extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(EditRoute.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        String name = req.getParameter("routeName");
        String id = req.getParameter("routeId");
        try {
            RouteService.editRoute(name, id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("getRouteSettings");

        logger.debug("Command finished");
    }
}
