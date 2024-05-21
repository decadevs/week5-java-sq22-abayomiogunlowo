package LIBRARY_SYSTEM.src.main.java.model;

import LIBRARY_SYSTEM.src.main.java.enums.Genre;

public class Book {
    private String title;
    private String author;
    private int copies;
    private Genre genre;

    public Book(String title, String author, int copies, Genre genre) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopies() {
        return copies;
    }

    public Genre getGenre() {
        return genre;
    }

    public void borrowBook() {
        if (copies > 0) {
            copies--;
        } else {
            throw new IllegalStateException("No copies available");
        }
    }

    public void returnBook() {
        copies++;
    }
}