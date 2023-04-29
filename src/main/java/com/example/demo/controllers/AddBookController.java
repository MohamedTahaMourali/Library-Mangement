package com.example.demo.controllers;


import com.example.demo.models.Book;
import com.example.demo.utils.BookConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;

public class AddBookController {

    @FXML
    private TextField idField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField copiesField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField publisherField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @FXML
    void onAddButtonClick() {

        // extracting data from the graphical interface
        int id =Integer.parseInt(idField.getText());
        String title = titleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        int year = Integer.parseInt(yearField.getText());
        String genre = genreField.getText();
        int nbrCopies = Integer.parseInt(copiesField.getText());

        //Creating the book which will be added to the DataBase
        Book book = new Book (id, title, author, publisher, year, genre, nbrCopies);

        //add the book to the DataBase
        addBook(book);


    }
    private void addBook(Book book ){
        BookConnection con = new BookConnection();

        //check if the book already exists in the DataBase
        if (con.isBookExists(book.getBookId())) {
            // Inform the user with the error of an existing book
            JOptionPane.showMessageDialog(null, "Book Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            // add the book to the DataBase
            con.addBook(book);

            // Inform the user that the book has been added
            JOptionPane.showMessageDialog(null, "The book addition operation was carried out successfully", "Information", JOptionPane.INFORMATION_MESSAGE);

        }

    }

}
