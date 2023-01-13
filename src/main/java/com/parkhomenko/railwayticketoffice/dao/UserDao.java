package com.parkhomenko.railwayticketoffice.dao;

import com.parkhomenko.railwayticketoffice.entitie.User;

import java.sql.*;

public class UserDao {

    private static final String FIND_USER = "SELECT * FROM user WHERE login = ? AND password = ?";
    private static final String INSERT_USER = "INSERT INTO user (login, password, id_role) VALUES (?, ?, 1)";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String DELETE_USER = "DELETE FROM user WHERE login = ?";

//    private JdbcTemplate jdbcTemplate;
//
//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public static User findUser(Connection con, String login, String pass) throws SQLException {
        User user = null;

        PreparedStatement pstmt = con.prepareStatement(FIND_USER);
        pstmt.setString(1, login);
        pstmt.setString(2, pass);

        ResultSet rs = pstmt.executeQuery();
        user = extractUser(rs);

        return user;
    }

    public static boolean findUserByLogin(Connection con, String login) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(FIND_USER_BY_LOGIN);
        pstmt.setString(1, login);
        ResultSet rs = pstmt.executeQuery();

        return rs.next();
    }

    public static User getUserByLogin(Connection con, String login) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(FIND_USER_BY_LOGIN);
        pstmt.setString(1, login);
        ResultSet rs = pstmt.executeQuery();

        return extractUser(rs);
    }

    public static void insertUser(Connection con, String login, String pass) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(INSERT_USER);
        pstmt.setString(1, login);
        pstmt.setString(2, pass);

        if(pstmt.executeUpdate() > 0){
            //System.out.println("successful insert");
        }else{
            //System.out.println("unsuccessful insert");
        }

    }

    private static User extractUser(ResultSet rs) throws SQLException {
        User user = null;
        if(rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setIdRole(rs.getInt("id_role"));
        }

        return user;
    }

}
