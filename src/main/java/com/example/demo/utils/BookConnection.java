package com.example.demo.utils;

import com.example.demo.models.Book;

import java.sql.*;

public class BookConnection {
    private static String url ="jdbc:mysql://localhost:3306/book?autoReconnect=true&useSSL=false" ;
    private static String utilisateur = "root";
    private static String motPasse = "";
    private static java.sql.Connection cnx;

    public BookConnection(){

    }
    public static java.sql.Connection getInstance() {
        try {

            if(cnx==null || cnx.isClosed()) {
                cnx = DriverManager.getConnection(url, utilisateur, motPasse);
                System.out.println("Connected to the Library Database Successfully.");}
        } catch (SQLException e) {
            System.out.println("Failed To connect ." + "Please verify that you have added the  jdbc to your  build path");
            System.exit(0);
        }
        return cnx;
    }

    public static void close() {
        try {
            if (cnx != null && !cnx.isClosed())
                cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getAllBooks() throws SQLException {
        // Chargement du pilote JDBC pour MySQL
        Connection conn = BookConnection.getInstance();

        // Exécution de la requête SQL pour récupérer les données de la table "books"
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `books` ORDER BY `books`.`book_id`");
        return rs;
    }
    public boolean isBookExists(int id) {
        Connection conn = BookConnection.getInstance();
        String query = "SELECT COUNT(*) FROM `books` WHERE `books`.`book_id`=? ;";
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
    public boolean addBook(Book book) {
        Connection conn = BookConnection.getInstance();
        String insertQuery = "INSERT INTO `books` "  + " (book_id, title, author, publisher, publication_year,genre,available_copies) VALUES (?, ?, ?, ?, ?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(insertQuery);
            statement.setInt(1, book.getBookId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            statement.setInt(5,book.getPublicationYear());
            statement.setString(6,book.getGenre());
            statement.setInt(7,book.getAvailableCopies());
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
    public boolean deleteBook(int bookId) {
        Connection conn = BookConnection.getInstance();
        String deleteQuery = "DELETE FROM `books` WHERE `books`.`book_id`=? ;";
        try {
            PreparedStatement statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1,bookId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}