package SOLUTION.src.main.java.service;


import SOLUTION.src.main.java.model.Book;
import SOLUTION.src.main.java.model.Person;

public interface LibraryService {
    void addBook(Book book);
    String requestBook(Person person, String title);
    void returnBook(Person person, String title);
}
