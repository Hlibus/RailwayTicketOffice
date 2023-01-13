package com.parkhomenko.railwayticketoffice.service;

import com.parkhomenko.railwayticketoffice.dao.UserDao;
import com.parkhomenko.railwayticketoffice.db.DBManager;
import com.parkhomenko.railwayticketoffice.entitie.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    public static User getUserByLogin(String login) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getMySQLConnection();
        User user = UserDao.getUserByLogin(con, login);
        con.close();
        return user;
    }

}
