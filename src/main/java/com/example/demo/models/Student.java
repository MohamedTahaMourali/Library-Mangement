package com.example.demo.models;



public class Student {
    private int idStudent;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String borrewedBooks;

    private boolean isAdmin;
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }



    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBorrewedBooks(String borrewedBooks) {
        this.borrewedBooks = borrewedBooks;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBorrewedBooks() {
        return borrewedBooks;
    }

    public Student(int idStudent, String firstName, String lastName, String email,String password ,String borrewedBooks, boolean isAdmin) {
        this.idStudent = idStudent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password=password;
        this.borrewedBooks = borrewedBooks;
        this.isAdmin = isAdmin;
    }
    public Student(int idStudent, String firstName, String lastName, String email, String borrowedBook ){
        this.idStudent = idStudent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.borrewedBooks = borrowedBook;
    }

}
