package com.example.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.models.Book;
import com.example.demo.models.Student;
import com.example.demo.utils.BookConnection;
import com.example.demo.utils.StudentConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewStudentController {
    ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Student, String> bookColum;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<Student, String> emailColum;

    @FXML
    private TableColumn<Student, String> firstNameColum;

    @FXML
    private TableColumn<Student, Integer> idColum;

    @FXML
    private TableColumn<Student, String> lastNameColum;

    @FXML
    void addWindow(ActionEvent event) {
        load("/com/example/demo/add-student.fxml", "Add Student");
    }

    @FXML
    void deleteWindow(ActionEvent event) {
        load("/com/example/demo/delete-student.fxml", "Delete Student");

    }
    @FXML
    void initialize() {
        initCol();
        lanch();
    }

    private void lanch() {
        studentList.clear();
        try {
            StudentConnection conn =  new StudentConnection();
            ResultSet rs = conn.getAllStudents();
            try {
                System.out.println("pulling from data base");

                while (rs.next()) {
                    Student student = new Student(rs.getInt("student_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("borrowed_books"));
                    studentList.add(student);
                    System.out.println("pulling");
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                System.out.println("Loaded " + studentList.size() + " Students.");
                studentTable.getItems().setAll (studentList);

            }
        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    private void initCol() {
        idColum.setCellValueFactory(new PropertyValueFactory<>("idStudent"));
        firstNameColum.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColum.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColum.setCellValueFactory(new PropertyValueFactory<>("email"));
        bookColum.setCellValueFactory(new PropertyValueFactory<>("borrewedBooks"));


    }
    private void load(String s, String windowTitle) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(s));
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
