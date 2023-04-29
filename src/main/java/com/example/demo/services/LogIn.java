package com.example.demo.services;

import com.example.demo.utils.StudentConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn {
        public static boolean isAdmin(String email, String password) {
        try {


            // Connecting to the database
            Connection conn = StudentConnection.getInstance();

            // Preparing the query
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE email=? AND password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);

            // executing the query
            ResultSet rs = stmt.executeQuery();

            // verifying whether the user is an admin or not
            if (rs.next()) {
                boolean isAdmin = rs.getBoolean("isAdmin");
                return isAdmin;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
