package com.example.demo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.utils.BookConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;

public class DeleteBookController {

    @FXML
    private Button addButtonField;

    @FXML
    private TextField idField;

    @FXML
    void onDeleteButton(ActionEvent event) {
        // extraction the id from the Text Field
        int id = Integer.parseInt(idField.getText());
        BookConnection con = new BookConnection();

        // Checking if the book exists in the dataBase
        if (con.isBookExists(id)){
            // Deleting
            con.deleteBook(id);

            // Informing the user
            JOptionPane.showMessageDialog(null, "The book deleting operation was carried out successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        else {

            // Error of not finding the student
            JOptionPane.showMessageDialog(null, "Book does not exist in the books DataBase", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


}
