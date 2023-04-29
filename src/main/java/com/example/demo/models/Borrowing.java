package com.example.demo.models;

import java.time.LocalDate;
import java.util.Date;

public class Borrowing {
    private Student student;
    private Book book ;
    private LocalDate dateBorrow;
    private LocalDate dateRetour;

    public Borrowing(Student student, Book book, LocalDate dateBorrow, LocalDate dateRetour) {
        this.student = student;
        this.book = book;
        this.dateBorrow = dateBorrow;
        this.dateRetour = dateRetour;
    }

    public Student getStudent() {
        return student;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDateBorrow() {
        return dateBorrow;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }
}
