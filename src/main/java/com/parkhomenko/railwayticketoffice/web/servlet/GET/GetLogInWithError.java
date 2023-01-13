package com.parkhomenko.railwayticketoffice.web.servlet.GET;

import com.parkhomenko.railwayticketoffice.web.Path;
import com.parkhomenko.railwayticketoffice.web.servlet.BOTH.LogIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginWithError")
public class GetLogInWithError extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetLogInWithError.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command starts");

        req.getRequestDispatcher(Path.PAGE_LOGIN).forward(req, resp);

        logger.debug("Command finished");
    }
}
