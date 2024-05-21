package SOLUTION.src.main.java.model;

public class Book {
    private String title;
    private String author;
    private int copies;

    public Book(String title, String author, int copies) {
        this.title = title;
        this.author = author;
        this.copies = copies;
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