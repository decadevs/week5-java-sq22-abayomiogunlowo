package SOLUTION.src.main.java.service.impl;


import SOLUTION.src.main.java.model.Book;
import SOLUTION.src.main.java.model.Person;
import SOLUTION.src.main.java.service.LibraryService;

import java.util.*;

public class LibraryServiceImpl implements LibraryService {
    private Map<String, Book> books;
    private Queue<Map.Entry<Person, String>> requestQueue;

    public LibraryServiceImpl(boolean prioritize) {
        books = new HashMap<>();
        if (prioritize) {
            requestQueue = new PriorityQueue<>(new Comparator<Map.Entry<Person, String>>() {
                private final SOLUTION.src.main.java.service.impl.RequestPriorityComparatorImpl comparator = new SOLUTION.src.main.java.service.impl.RequestPriorityComparatorImpl();

                @Override
                public int compare(Map.Entry<Person, String> e1, Map.Entry<Person, String> e2) {
                    return comparator.compare(e1.getKey(), e1.getValue(), e2.getKey(), e2.getValue());
                }
            });
        } else {
            requestQueue = new LinkedList<>();
        }
    }

    @Override
    public void addBook(Book book) {
        books.put(book.getTitle(), book);
    }

    @Override
    public String requestBook(Person person, String title) {
        if (!books.containsKey(title)) {
            return "Book not found";
        }

        Book book = books.get(title);

        if (book.getCopies() > 0) {
            book.borrowBook();
            return "Book borrowed";
        } else {
            requestQueue.add(new AbstractMap.SimpleEntry<>(person, title));
            return "Added to waiting list";
        }
    }

    @Override
    public void returnBook(Person person, String title) {
        if (!books.containsKey(title)) {
            throw new IllegalArgumentException("Book not found");
        }

        Book book = books.get(title);
        book.returnBook();

        processNextRequest(title);
    }

    private void processNextRequest(String title) {
        for (Map.Entry<Person, String> entry : requestQueue) {
            if (entry.getValue().equals(title)) {
                Book book = books.get(title);
                if (book.getCopies() > 0) {
                    book.borrowBook();
                    requestQueue.remove(entry);
                    System.out.println(entry.getKey().getName() + " has been allocated the book: " + title);
                    break;
                }
            }
        }
    }
}
