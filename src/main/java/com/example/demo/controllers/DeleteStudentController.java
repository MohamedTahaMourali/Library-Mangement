package com.example.demo.controllers;


import com.example.demo.models.Student;
import com.example.demo.utils.BookConnection;
import com.example.demo.utils.StudentConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;

public class DeleteStudentController {

    @FXML
    private Button deleteButton;

    @FXML
    private TextField idField;

    @FXML
    void onDeleteButton(ActionEvent event) {
        // extraction the id from the Text Field
        int id = Integer.parseInt(idField.getText());

        StudentConnection con = new StudentConnection();

        // Checking if the book exists in the dataBase
        if (con.isStudentExists(id)){
            // Deleting
            con.deleteStudent(id);

            // Informing the user
            JOptionPane.showMessageDialog(null, "The Student deleting operation was carried out successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            // Error of not finding the student
            JOptionPane.showMessageDialog(null, "Student does not exist in the books DataBase", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
