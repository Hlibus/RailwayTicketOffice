package com.parkhomenko.railwayticketoffice.web.servlet.BOTH;

import com.parkhomenko.railwayticketoffice.entitie.User;
import com.parkhomenko.railwayticketoffice.service.LogInService;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.parkhomenko.railwayticketoffice.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/logIn")
public class LogIn extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LogIn.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        logger.debug("Command GET starts");

        deleteErrorMessageFromSession(req);
        req.getRequestDispatcher(Path.PAGE_LOGIN).forward(req, resp);

        logger.debug("Command GET finished");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command POST starts");

        String login  = req.getParameter("login");
        String pass  = req.getParameter("pass");
        logger.debug("Inserted user login - " + login);

        User user = null;
        try {
            logger.debug("Search user in DB...");
            user = LogInService.findByLoginPass(login, pass);
        } catch (SQLException | ClassNotFoundException e) {
            logger.debug("An error occurred while searching user in DB");
            e.printStackTrace();
        }

        if(user != null){
            logger.debug("Authorization successfully passed, redirecting to the main page");
            int roleId = user.getIdRole();
            HttpSession session = req.getSession();
            //session.setAttribute("user", user);
            session.setAttribute("login", user.getLogin());
            session.setAttribute("roleId", roleId);
            logger.debug("User roleId - " + roleId);
            resp.sendRedirect("main");
        }else{
            logger.debug("Authorization failed, redirect to authorization page");
            addErrorMessageToSession(req, "Перевірте логін та пароль");
            resp.sendRedirect("loginWithError");
        }

        logger.debug("Command POST finished");
    }

    private void addErrorMessageToSession(HttpServletRequest req, String logInErrorMessage){
        //logger.debug("Add error message to authorization page");
        req.getSession().setAttribute("logInErrorMessage", logInErrorMessage);
    }

    private void deleteErrorMessageFromSession(HttpServletRequest req){
        //logger.debug("Delete error message from authorization page");
        req.getSession().removeAttribute("logInErrorMessage");
    }

}