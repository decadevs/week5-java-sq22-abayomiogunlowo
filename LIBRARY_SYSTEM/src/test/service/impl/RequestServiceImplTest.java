package LIBRARY_SYSTEM.src.test.service.impl;

import LIBRARY_SYSTEM.src.main.java.LibraryApp;
import LIBRARY_SYSTEM.src.main.java.enums.Genre;
import LIBRARY_SYSTEM.src.main.java.model.Book;
import LIBRARY_SYSTEM.src.main.java.model.Person;
import LIBRARY_SYSTEM.src.main.java.service.RequestService;
import LIBRARY_SYSTEM.src.main.java.service.impl.RequestServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class RequestServiceImplTest {

    private RequestService requestService;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        requestService = new RequestServiceImpl();
        books = new ArrayList<>();
        books.add(new Book(1234, "Math Basics", "Author A", 3, Genre.EDUCATIONAL));
        books.add(new Book(2345, "Science Facts", "Author B", 1, Genre.EDUCATIONAL));
        books.add(new Book(3456, "Fantastic Fiction", "Author C", 3, Genre.FICTION));
    }

    @Test
    public void testProcessRequest() {
        List<LibraryApp.Request> requests = new ArrayList<>();
        requests.add(new LibraryApp.Request(new Person("Alice", 20), 1234));
        requests.add(new LibraryApp.Request(new Person("Bob"), 2345));
        requests.add(new LibraryApp.Request(new Person("Charlie", 8), 1234));
        requests.add(new LibraryApp.Request(new Person("David", 6), 3456));
        requests.add(new LibraryApp.Request(new Person("Eve"), 3456));
        requests.add(new LibraryApp.Request(new Person("Frank", 7), 3456));

        requestService.processRequest(books, requests);

        // Check the state of books to ensure correct borrowing
        Book mathBasics = books.get(0);
        Book scienceFacts = books.get(1);
        Book fantasticFiction = books.get(2);

        assertEquals(1, mathBasics.getCopies());
        assertEquals(0, scienceFacts.getCopies());
        assertEquals(0, fantasticFiction.getCopies());
    }

    @Test
    public void testRequestBasedOnPriority() {
        Queue<Person> educationalRequests = new LinkedList<>();
        educationalRequests.add(new Person("Alice", 20));
        educationalRequests.add(new Person("Bob"));
        educationalRequests.add(new Person("Charlie", 8));

        requestService.requestBasedOnPriority(educationalRequests, books);

        // Check the state of books to ensure correct borrowing
        Book mathBasics = books.get(0);
        Book scienceFacts = books.get(1);

        assertEquals(1, mathBasics.getCopies());
        assertEquals(0, scienceFacts.getCopies());
    }

    @Test
    public void testRequestBasedOnFIFO() {
        List<Person> fictionRequests = new ArrayList<>();
        fictionRequests.add(new Person("David", 6));
        fictionRequests.add(new Person("Eve"));
        fictionRequests.add(new Person("Frank", 7));

        requestService.requestBasedOnFIFO(fictionRequests, books);

        // Check the state of books to ensure correct borrowing
        Book fantasticFiction = books.get(2);

        assertEquals(0, fantasticFiction.getCopies());
    }
}