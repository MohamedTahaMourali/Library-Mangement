package com.example.demo.utils;


import com.example.demo.models.Book;
import com.example.demo.models.Student;

import java.sql.*;

public class StudentConnection {
    private static String url ="jdbc:mysql://localhost:3306/student?autoReconnect=true&useSSL=false" ;
    private static String utilisateur = "root";
    private static String motPasse = "";
    private static java.sql.Connection cnx;

    public static java.sql.Connection getInstance() {
        try {

            if(cnx==null || cnx.isClosed()) {
                cnx = DriverManager.getConnection(url, utilisateur, motPasse);
                System.out.println("Connected to the users.Student Database Successfully.");}
        } catch (SQLException e) {
            System.out.println("Failed To connect ." + "Please verify that you have added the  jdbc to your  build path");
            System.exit(0);
        }
        return cnx;
    }

    public ResultSet getAllStudents() throws SQLException {
        Connection conn = StudentConnection.getInstance();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT student_id, first_name, last_name,email,borrowed_books FROM `students` ORDER BY `students`.`student_id` ;");
        return rs;
    }
    public boolean isStudentExists(int id) {
        Connection conn = StudentConnection.getInstance();
        String query = "SELECT COUNT(*) FROM `students` WHERE `students`.`student_id`=? ;";
        try  {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addStudent(Student student) {
        Connection conn = StudentConnection.getInstance();
        String insertQuery = "INSERT INTO `students` "  + " (student_id, first_name, last_name, email, password,borrowed_books,isAdmin) VALUES (?, ?, ?, ?, ?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(insertQuery);
            statement.setInt(1, student.getIdStudent());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            statement.setString(5,student.getPassword());
            statement.setString(6,student.getBorrewedBooks());
            statement.setBoolean(7,student.isAdmin());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean deleteStudent(int studentId) {
        Connection conn = StudentConnection.getInstance();
        String deleteQuery = "DELETE FROM `students` WHERE `students`.`student_id`=? ;";
        try {
            PreparedStatement statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1,studentId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}