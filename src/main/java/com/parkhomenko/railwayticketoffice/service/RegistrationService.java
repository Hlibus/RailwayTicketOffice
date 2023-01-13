package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.UserDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationService {

    public static boolean createNewUser(String login, String pass) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        if (!loginExistence(con, login)){
            UserDao.insertUser(con, login, pass);
        }else{
            //SEND MESSAGE ABOUT LOGIN EXISTENCE IN DB
            con.close();
            return false;
        }
        con.close();

        return true;
    }

    private static boolean loginExistence(Connection con, String login) throws SQLException {
        return UserDao.findUserByLogin(con, login);
    }

}
