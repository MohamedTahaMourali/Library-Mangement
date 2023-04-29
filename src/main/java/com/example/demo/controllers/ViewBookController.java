package com.example.demo.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.demo.models.Book;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.demo.utils.BookConnection;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewBookController {

    ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
      TableView<Book> bookTable;
    @FXML
      TableColumn<Book, String> titleColum;
    @FXML
    TableColumn<Book, Integer> idColum;

    @FXML
      TableColumn<Book, String> authorColum;
    @FXML
      TableColumn<Book, String> publisherColum;
    @FXML
      TableColumn<Book, Integer> yearColum;
    @FXML
      TableColumn<Book, String> genreColum;
    @FXML
      TableColumn<Book, Integer> copiesColum;

    @FXML
    void initialize() {
        initCol();
        lanch();

    }
    public void lanch() {
        bookList.clear();
        try {
            BookConnection conn =  new BookConnection();
            ResultSet rs = conn.getAllBooks();
            try {
                System.out.println("pulling from data base");

                while (rs.next()) {
                    Book book = new Book(rs.getInt("book_id"), rs.getString("title"), rs.getString("author"), rs.getString("publisher"), rs.getInt("publication_year"), rs.getString("genre"), rs.getInt("available_copies"));
                    bookList.add(book);
                    System.out.println("pulling");
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                System.out.println("Loaded " + bookList.size() + " books.");
                bookTable.getItems().setAll(bookList);

            }
        } catch (SQLException e) {
            System.out.println(e);

        }
    }


        // prepare the TableView which will contains the data in the interface
        private void initCol() {
            idColum.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            titleColum.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorColum.setCellValueFactory(new PropertyValueFactory<>("author"));
            publisherColum.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            yearColum.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
            genreColum.setCellValueFactory(new PropertyValueFactory<>("genre"));
            copiesColum.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));
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
        public void addWindow(ActionEvent actionEvent) {
            load("/com/example/demo/add-book.fxml", "Add book");
        }
    public void deleteWindow(ActionEvent actionEvent) {
        load("/com/example/demo/delete-book.fxml", "Delete book");
    }
}
