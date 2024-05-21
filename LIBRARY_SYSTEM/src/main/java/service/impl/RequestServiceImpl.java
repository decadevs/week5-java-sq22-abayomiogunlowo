package LIBRARY_SYSTEM.src.main.java.service.impl;

import LIBRARY_SYSTEM.src.main.java.enums.Genre;
import LIBRARY_SYSTEM.src.main.java.model.Book;
import LIBRARY_SYSTEM.src.main.java.model.Person;
import LIBRARY_SYSTEM.src.main.java.service.PriorityComparator;
import LIBRARY_SYSTEM.src.main.java.service.RequestService;

import java.util.*;

public class RequestServiceImpl implements RequestService {
    private final PriorityComparator priorityComparator;

    public RequestServiceImpl() {
        this.priorityComparator = new PriorityComparatorImpl();
    }

    @Override
    public void requestBasedOnPriority(Queue<Person> personQueue, List<Book> books) {
        PriorityQueue<Map.Entry<Person, String>> priorityQueue = new PriorityQueue<>(
                (e1, e2) -> priorityComparator.compare(e1.getKey(), e1.getValue(), e2.getKey(), e2.getValue())
        );

        Map<String, Book> bookMap = new HashMap<>();
        for (Book book : books) {
            if (book.getGenre() == Genre.EDUCATIONAL) {
                bookMap.put(book.getTitle(), book);
            }
        }

        for (Person person : personQueue) {
            for (Book book : bookMap.values()) {
                priorityQueue.offer(Map.entry(person, book.getTitle()));
            }
        }

        Set<Person> processedPersons = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Map.Entry<Person, String> entry = priorityQueue.poll();
            Person person = entry.getKey();
            if (processedPersons.contains(person)) {
                continue;
            }

            Book book = bookMap.get(entry.getValue());
            if (book.getCopies() > 0) {
                book.borrowBook();
                System.out.println(person.getName() + " borrowed " + book.getTitle());
                processedPersons.add(person);
            }
        }
    }

    @Override
    public void requestBasedOnFIFO(List<Person> personList, List<Book> books) {
        Map<String, Book> bookMap = new HashMap<>();
        for (Book book : books) {
            if (book.getGenre() == Genre.FICTION) {
                bookMap.put(book.getTitle(), book);
            }
        }

        for (Person person : personList) {
            for (Book book : bookMap.values()) {
                if (book.getCopies() > 0) {
                    book.borrowBook();
                    System.out.println(person.getName() + " borrowed " + book.getTitle());
                    break; // Ensure each person borrows only one book
                }
            }
        }
    }
}
