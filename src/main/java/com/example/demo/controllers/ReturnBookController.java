package com.example.demo.controllers;

import com.example.demo.utils.BookConnection;
import com.example.demo.utils.StudentConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnBookController   {

    @FXML
    private Button confirmButtonField;

    @FXML
    private TextField idBookField;

    @FXML
    private TextField idStudentField;
    public void onConfirmButtonClick(ActionEvent actionEvent) {
        //pulling data from user
        int idStudent = Integer.parseInt(idStudentField.getText());
        int idBook = Integer.parseInt(idBookField.getText());
        //returning the book
        System.out.println(returnBook(idStudent,idBook));
    }
    private boolean returnBook(int idStudent,int idBook){
        BookConnection bookConnection = new BookConnection();
        StudentConnection studentConnection=new StudentConnection();
        String bookName="";
        String studentName="";
        // check if the book and the students exist
        if ((bookConnection.isBookExists(idBook)) && (studentConnection.isStudentExists(idStudent))){

           // delete the line in the table borrwings
            try(PreparedStatement delBor = bookConnection.getInstance().prepareStatement("DELETE FROM borrowings WHERE borrowings.student_id=? and borrowings.book_id=? ;") ) {
                delBor.setInt(1, idStudent);
                delBor.setInt(2, idBook);
                int lgDet = delBor.executeUpdate();
                System.out.println("deleted lines : " + lgDet);

                // increment the number of the available copies in the dataBase
                try (PreparedStatement modBok = bookConnection.getInstance().prepareStatement("UPDATE books SET books.available_copies = (SELECT available_copies FROM books WHERE books.book_id = ? ) + 1 WHERE books.book_id=? ;")) {
                    modBok.setInt(1, idBook);
                    modBok.setInt(2, idBook);
                    int lgMod = modBok.executeUpdate();
                    System.out.println("Line Modified : " + lgMod);
                } catch (SQLException e) {
                    System.out.println("Error modifying available copies : " + e.getMessage());
                }
                // get the book's name
                try (PreparedStatement selectBook = bookConnection.getInstance().prepareStatement("SELECT title FROM `books` WHERE `books`.`book_id`=? ORDER BY `books`.`book_id` ;")) {
                    selectBook.setInt(1, idBook);
                    ResultSet resultSet = selectBook.executeQuery();
                    if (resultSet.next()) {
                        System.out.println("book found" + resultSet.getString("title"));
                        bookName += resultSet.getString("title");
                    }
                } catch (SQLException e) {
                    System.out.println("Error detecting the book name : " + e.getMessage());
                }

                // get the student's name
                try (Connection conn = StudentConnection.getInstance();
                     PreparedStatement selectStudent = conn.prepareStatement("SELECT first_name, last_name FROM `students` WHERE `students`.`student_id`=? ORDER BY `students`.`student_id` ;")) {
                    selectStudent.setInt(1, idStudent);
                    ResultSet resultSetStudent = selectStudent.executeQuery();
                    if (resultSetStudent.next()) {
                        System.out.println("student found" + resultSetStudent.getString("first_name") + " " + resultSetStudent.getString("last_name"));
                        studentName += resultSetStudent.getString("first_name") + " " + resultSetStudent.getString("last_name");
                    }
                } catch (SQLException e) {
                    System.out.println("Error detecting the student name " + e.getMessage());
                }

                //add the book to the list of borrowed books in the database
                try (PreparedStatement delStd = studentConnection.getInstance().prepareStatement("UPDATE students SET students.borrowed_books = NULL WHERE students.student_id=?")) {
                    delStd.setInt(1, idStudent);
                    int delLin = delStd.executeUpdate();
                    System.out.println("number of line deleted in students : " + delLin);
                } catch (SQLException e) {
                    System.out.println("Error deleting the book from the student : " + e.getMessage());
                }
                // inform the user with th success of the operation
                JOptionPane.showMessageDialog(null, studentName + " had returned the "+bookName, "Information", JOptionPane.INFORMATION_MESSAGE);

                return true;
            }


            catch (SQLException  e){

                System.out.println("error deleting Borrwing : "+e.getMessage());
                return false;
            }
        }
        else{
            // Error of finding the book or the student
            JOptionPane.showMessageDialog(null, "verify the Book Id or The SStudent ID ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
