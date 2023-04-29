package com.example.demo.models;


public class Book {

    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String genre;
    private int availableCopies;


    // Constructeur
    public Book (String title,String author ){this.title=title;this.author=author;}
    public Book(int bookId, String title, String author, String publisher, int publicationYear, String genre, int availableCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.availableCopies = availableCopies;
    }

    /*@Override
    public String toString() {
        return title+author+publisher+publicationYear+genre;
    }*/

    public Book(String title, String author, String publisher, int publication_year, String genre) {
        this.title=title;this.author=author;this.publisher=publisher;this.publicationYear=publication_year;this.genre=genre;
    }


    // Getters et Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    /*public void addBook() {
        String query = "INSERT INTO books (title, author, publisher, publication_year, genre, available_copies) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = BookConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            //stmt.setString(1, (String)bookId);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, publisher);
            stmt.setInt(4, publicationYear);
            stmt.setString(5, genre);
            stmt.setInt(6, availableCopies);

            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }*/

}

