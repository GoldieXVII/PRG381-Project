package com.mycompany.milestone1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataHandler {

    private static final String dbConnection = "jdbc:postgresql://localhost:5432/Users";
    private static final String dbUser = "postgres";
    private static final String dbPass = "admin";
    private static final String DRIVER = "org.postgresql.Driver";
    Connection con;

    // Method to make a connection to the database
    private Connection getConnection() throws ClassNotFoundException {
        try{
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(dbConnection, dbUser, dbPass);
            if(this.con != null){
                System.out.println("Connected to Database");
            }
        }
        catch(SQLException ex){
            System.out.println("Could not connect " + ex.getMessage());
        }
        return con;
    }

    // Method to check if the username or email already exists
    public boolean isUsernameOrEmailTaken(String username, String email) throws SQLException, ClassNotFoundException {
        String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ? OR email = ?";
        try (Connection connection = getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, username);
            checkStmt.setString(2, email);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to check username or email.");
            e.printStackTrace();
            throw e; 
        }
        return false;
    }

    // Method to insert a new user into the database
    public boolean registerUser(String username, String password, String name, String surname, String phone, String email) throws SQLException, ClassNotFoundException {
        String insertQuery = "INSERT INTO users (username, password, name, surname, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            insertStmt.setString(1, username);
            insertStmt.setString(2, password); 
            insertStmt.setString(3, name);
            insertStmt.setString(4, surname);
            insertStmt.setString(5, phone);
            insertStmt.setString(6, email);
            return insertStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Failed to register user.");
            e.printStackTrace();
            throw e;
        }
    }

    // Method to validate user credentials for login
    public boolean validateUser(String username, String password) throws SQLException, ClassNotFoundException {
        String loginQuery = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement loginStmt = connection.prepareStatement(loginQuery)) {
            loginStmt.setString(1, username);
            loginStmt.setString(2, password);
            try (ResultSet rs = loginStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to validate user.");
            e.printStackTrace();
            throw e;
        }
        return false;
    }
}

