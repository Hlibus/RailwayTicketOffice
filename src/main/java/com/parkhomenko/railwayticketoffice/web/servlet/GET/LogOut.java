package com.parkhomenko.railwayticketoffice.web.servlet.GET;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logOut")
public class LogOut extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LogOut.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        logger.debug("log out from user account with login - " + req.getSession().getAttribute("login"));
        req.getSession().removeAttribute("login");
        req.getSession().removeAttribute("roleId");
        req.getRequestDispatcher("main").forward(req, resp);

        logger.debug("Command finished");
    }
}
