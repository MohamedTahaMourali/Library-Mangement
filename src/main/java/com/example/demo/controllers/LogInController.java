package com.example.demo.controllers;

import javafx.stage.StageStyle;
import com.example.demo.services.LogIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


import java.util.logging.Level;
import java.util.logging.Logger;


public class LogInController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameField;


    @FXML
    void onLogInClick(ActionEvent event){
        // pulling data from user
        String username = userNameField.getText();
        String password = passwordField.getText();

        // check if the user is an admin or not
        if (LogIn.isAdmin(username, password)) {
            // open the manage interface
            loadWindow("/com/example/demo/admin-interface.fxml", "Manage");
        }
        else{
            JOptionPane.showMessageDialog(null, "Not an Admin", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        // this methode will open the window of the file named fileName
        // windowTitle is the title will be putted in title bar of the window
        private void loadWindow(String fileName, String windowTitle) {
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


