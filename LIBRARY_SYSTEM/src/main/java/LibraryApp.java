package LIBRARY_SYSTEM.src.main.java;

import LIBRARY_SYSTEM.src.main.java.enums.Genre;
import LIBRARY_SYSTEM.src.main.java.model.Book;
import LIBRARY_SYSTEM.src.main.java.model.Person;
import LIBRARY_SYSTEM.src.main.java.service.RequestService;
import LIBRARY_SYSTEM.src.main.java.service.impl.RequestServiceImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LibraryApp {
    public static void main(String[] args) {
        // Create some books
        List<Book> books = new ArrayList<>();
        books.add(new Book("Math Basics", "Author A", 3, Genre.EDUCATIONAL));
        books.add(new Book("Science Facts", "Author B", 1, Genre.EDUCATIONAL));
        books.add(new Book("Fantastic Fiction", "Author C", 3, Genre.FICTION));

        // Create some people
        Queue<Person> educationalRequests = new LinkedList<>();
        educationalRequests.add(new Person("Alice", 20)); // Student
        educationalRequests.add(new Person("Bob")); // Teacher
        educationalRequests.add(new Person("Charlie", 8)); // Student

        List<Person> fictionRequests = new ArrayList<>();
        fictionRequests.add(new Person("David", 6)); // Student
        fictionRequests.add(new Person("Eve")); // Teacher
        fictionRequests.add(new Person("Frank", 7)); // Student

        // Request service
        RequestService requestService = new RequestServiceImpl();

        // Handle requests based on genre
        System.out.println("Handling Educational Requests Based on Priority:");
        requestService.requestBasedOnPriority(educationalRequests, books);

        System.out.println("\nHandling Fiction Requests Based on FIFO:");
        requestService.requestBasedOnFIFO(fictionRequests, books);
    }
}