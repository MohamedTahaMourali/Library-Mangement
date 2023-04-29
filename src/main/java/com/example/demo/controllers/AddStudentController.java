package com.example.demo.controllers;



import com.example.demo.models.Student;
import com.example.demo.utils.StudentConnection;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javax.swing.*;

public class AddStudentController {

    @FXML
    private TextField idFienld;

    @FXML
    private TextField emailField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField nameFienld;

    @FXML
    private TextField passwordField;

    private void addStudent(Student student){
        StudentConnection con = new StudentConnection();
        //check if the student already exists in the DataBase
        if (con.isStudentExists(student.getIdStudent())){
            // Inform the user with the error of an existing student
            JOptionPane.showMessageDialog(null, "Student Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            // add the book to the DataBase
            System.out.println(con.addStudent(student));

            // Inform the user that the book has been added
            JOptionPane.showMessageDialog(null, "The student addition operation was carried out successfully", "Information", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    @FXML
    public void onAddButton(javafx.event.ActionEvent actionEvent) {

        // extracting data from the graphical interface
        int id = Integer.parseInt(idFienld.getText());
        String firstName = nameFienld.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        //Creating the student which will be added to the DataBase
        Student student = new Student(id,firstName,lastName,email,password,null,false);

        //add the student to the DataBase
        addStudent(student);
    }
}
