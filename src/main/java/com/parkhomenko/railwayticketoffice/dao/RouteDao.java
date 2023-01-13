package com.parkhomenko.railwayticketoffice.dao;

import com.parkhomenko.railwayticketoffice.entitie.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDao {

    private static final String FIN_ALL_ROUTES = "SELECT * FROM route";
    private static final String FIND_ROUTE_ID_BY_NAME = "SELECT id FROM route WHERE name = ?";
    private static final String CREATE_NEW_ROUTE = "INSERT INTO route (name) VALUES (?)";
    private static final String DELETE_ROUTE = "DELETE FROM route WHERE id = ?";
    private static final String EDIT_ROUTE = "UPDATE route SET name = ? WHERE id = ?";

    public static List<Route> findAll(Connection con) throws SQLException {
        List<Route> routes = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(FIN_ALL_ROUTES);
        extractRoutes(rs, routes);

        return routes;
    }

    public static void edit(Connection con, String name, String id) throws SQLException{
        PreparedStatement pstmt = con.prepareStatement(EDIT_ROUTE);
        pstmt.setString(1, name);
        pstmt.setString(2, id);
        pstmt.executeUpdate();
    }

    public static long addNew(Connection con, String name) throws SQLException{
        long id = -1;

        PreparedStatement pstmt = con.prepareStatement(CREATE_NEW_ROUTE, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, name);
        int affectedRows = pstmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        return id;
    }

    public static void delete(Connection con, String id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(DELETE_ROUTE);
        pstmt.setString(1, id);
        pstmt.executeUpdate();
    }

    public static int findIdByName(Connection con, String name) throws SQLException{
        int id = -1;

        PreparedStatement pstmt = con.prepareStatement(FIND_ROUTE_ID_BY_NAME);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            id = rs.getInt("id");
        }

        return id;
    }

    private static void extractRoutes(ResultSet rs, List<Route> routes) throws SQLException {
        Route route;
        while(rs.next()){
            route = new Route();
            route.setId(rs.getInt("id"));
            route.setName(rs.getString("name"));
            routes.add(route);
        }
    }

}
