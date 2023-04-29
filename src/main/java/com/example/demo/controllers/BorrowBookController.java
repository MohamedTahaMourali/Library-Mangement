package com.example.demo.controllers;


import com.example.demo.models.Student;
import com.example.demo.utils.BookConnection;
import com.example.demo.utils.StudentConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class BorrowBookController {



    @FXML
    private DatePicker dateBorrowField;

    @FXML
    private DatePicker dateReturnField;

    @FXML
    private TextField idBookField;

    @FXML
    private TextField idStudentField;

    @FXML
    void onConfirmButtonClick(ActionEvent event) {
        // pulling data from the user
        int idStudent = Integer.parseInt(idStudentField.getText());
        int idBook = Integer.parseInt(idBookField.getText());
        LocalDate dateBorrow = dateBorrowField.getValue();
        LocalDate dateReturn = dateReturnField.getValue();

        // borrow the book
        addBorrowing(idStudent, idBook,dateBorrow,dateReturn);

    }

    public boolean addBorrowing(int studentId, int bookId, LocalDate dateBorrowed, LocalDate dateReturned) {
        BookConnection bC = new BookConnection();
        StudentConnection sC = new StudentConnection();
        // check the existence of the book and the student
        if ((bC.isBookExists(bookId)) && (sC.isStudentExists(studentId))) {

            // adding a new borrowing act : in the table borrowings we are going to add a new ligne
            try (Connection connection = BookConnection.getInstance(); // the connection to the dataBase
                 // preparing the query
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO borrowings (student_id, book_id, date_borrowed, date_returned) VALUES (?, ?, ?, ?) ;")) {
                //adding the parameters : teh student ID ,The Book ID and the dates of the borrowing and the returning
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, bookId);
                preparedStatement.setDate(3, Date.valueOf(dateBorrowed));
                preparedStatement.setDate(4, Date.valueOf(dateReturned));
                // execute the update to the dataBase
                int rs = preparedStatement.executeUpdate();
                System.out.println("number of line modified : " + rs);
                String Message = "";
                String bookName = "";

                // Updating the row available_copies in the table books : decrement the value of the available_copies
                try (PreparedStatement modfBook = connection.prepareStatement("UPDATE books SET books.available_copies = (SELECT available_copies FROM books WHERE books.book_id=?)-1 WHERE books.book_id=? ;")) {
                    modfBook.setInt(1, bookId);
                    modfBook.setInt(2, bookId);
                    int modb = modfBook.executeUpdate();
                    System.out.println("number of line modified : " + modb);
                } catch (SQLException e) {
                    System.out.println("Error modifing number of copies : " + e.getMessage());
                }

                // pulling the name of the borrowed book for the information later
                try (PreparedStatement selectBook = connection.prepareStatement("SELECT title FROM `books` WHERE `books`.`book_id`=? ORDER BY `books`.`book_id` ;")) {
                    selectBook.setInt(1, bookId);
                    ResultSet resultSet = selectBook.executeQuery();
                    if (resultSet.next()) {
                        System.out.println("book found" + resultSet.getString("title"));
                        bookName += resultSet.getString("title");
                        Message += "The " + bookName;
                    }
                } catch (SQLException e) {
                    System.out.println("Error detecting book name: " + e.getMessage());
                }

                String studentName = "";


                // pulling the name of the student for the information later
                try (Connection conn = StudentConnection.getInstance();
                     PreparedStatement selectStudent = conn.prepareStatement("SELECT first_name, last_name FROM `students` WHERE `students`.`student_id`=? ORDER BY `students`.`student_id` ;")) {
                    selectStudent.setInt(1, studentId);
                    ResultSet resultSetStudent = selectStudent.executeQuery();
                    if (resultSetStudent.next()) {
                        System.out.println("student found" + resultSetStudent.getString("first_name") + " " + resultSetStudent.getString("last_name"));
                        studentName += resultSetStudent.getString("first_name") + " " + resultSetStudent.getString("last_name");
                    }

                    // Updating the row borrowed_books in the table students : adding the book to this row
                    try (PreparedStatement modfStd = conn.prepareStatement("UPDATE students SET students.borrowed_books =? WHERE students.student_id=?")) {
                        modfStd.setString(1, bookName);
                        modfStd.setInt(2, studentId);
                        int nbrlin = modfStd.executeUpdate();
                        System.out.println("number of student modified : " + nbrlin);
                    } catch (SQLException e) {
                        System.out.println("Error modifying student: " + e.getMessage());
                    }
                } catch (SQLException e) {
                    System.out.println("Error detecting student: " + e.getMessage());
                }

                Message += " is Borrowed To " + studentName;

                // informing the user with the name of the student and the name of the book
                JOptionPane.showMessageDialog(null, Message, "Information", JOptionPane.INFORMATION_MESSAGE);
                return (rs > 0);
            } catch (SQLException e) {
                System.out.println("Error inserting borrowing: " + e.getMessage());
                return false;
            }
        }

        else {
            // Error of not finding the student or the book
            JOptionPane.showMessageDialog(null, "verify the Book Id or The SStudent ID ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


}
