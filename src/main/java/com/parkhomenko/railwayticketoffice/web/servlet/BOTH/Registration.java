package com.parkhomenko.railwayticketoffice.web.servlet.BOTH;

import com.parkhomenko.railwayticketoffice.service.RegistrationService;
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

@WebServlet("/registration")
public class Registration extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Registration.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command GET starts");

        deleteErrorMessageFromSession(req);
        req.getRequestDispatcher(Path.PAGE_REGISTER).forward(req, resp);

        logger.debug("Command GET finished");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Command POST starts");

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String confirmPass = req.getParameter("confirmPass");

        logger.debug("Inserted login - " + login);

        if (!pass.equals(confirmPass)) {
            logger.debug("Registration failed, passwords are not equals");
            addErrorMessageToSession(req, "Пароли не совпадают");
            resp.sendRedirect("registrationWithError");
        }else{
            boolean inserted = false;
            try {
                inserted = RegistrationService.createNewUser(login, pass);
            } catch (SQLException | ClassNotFoundException e) {
                logger.debug("An error occurred while creating new user in DB");
                e.printStackTrace();
            }

            if(inserted){
                logger.debug("Registration successfully passed, redirecting to the authorization page");
                deleteErrorMessageFromSession(req);
                resp.sendRedirect("logIn");
            }else{
                logger.debug("Registration failed, login already in use");
                addErrorMessageToSession(req, "Логин уже испоьзуется");
                resp.sendRedirect("registrationWithError");
            }

            logger.debug("Command POST finished");
        }

    }

    private void addErrorMessageToSession(HttpServletRequest req, String signUpErrorMessage){
        //logger.debug("Add error message to registration page");
        req.getSession().setAttribute("signUpErrorMessage", signUpErrorMessage);
    }

    private void deleteErrorMessageFromSession(HttpServletRequest req){
        //logger.debug("Delete error message from registration page");
        req.getSession().removeAttribute("signUpErrorMessage");
    }
}
