package com.example.demo.controllers;

import javafx.event.ActionEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class ManageController {


    @FXML
    void addStudent(){
        // Opening the interface of adding new Student
        load("/com/example/demo/add-student.fxml", "Add Student");
    }
    @FXML
    void deleteStudent(){
        // Opening the interface of deleting Student
        load("/com/example/demo/delete-student.fxml", "Delete Student");
    }
    @FXML
    void viewStudent(){
        // Opening the interface to see the list of the
        load("/com/example/demo/view-student.fxml", "View Student");


    }
    @FXML
    void addBook(){
        // Opening the interface of adding new book
        load("/com/example/demo/add-book.fxml", "Add Book");
    }
    @FXML
    void deleteBook(){
        // Opening the interface of deleting Student
        load("/com/example/demo/delete-book.fxml", "Delete Book");
    }
    @FXML
    void viewBook(){
        load("/com/example/demo/view-book.fxml", "View Book");
    }

    @FXML
    void onBorrowButton(ActionEvent event) {
        // Opening the interface of borrowing a book
        load("/com/example/demo/borrow-book.fxml", "Borrow Book");

    }

    @FXML
    void onReturnBook(ActionEvent event) {
        // Opening the interface of returning the book
        load("/com/example/demo/return-book.fxml", "Return Book");
    }

    private void load(String fileName, String windowTitle) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fileName));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }


}