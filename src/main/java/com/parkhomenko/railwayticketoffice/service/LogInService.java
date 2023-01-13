package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.UserDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.entitie.User;

import java.sql.Connection;
import java.sql.SQLException;

public class LogInService {

    public static User findByLoginPass(String login, String pass) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        User user = UserDao.findUser(con, login, pass);
        con.close();
        return user;
    }

}
