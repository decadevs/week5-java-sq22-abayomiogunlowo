package LIBRARY_SYSTEM.src.main.java.service;

import LIBRARY_SYSTEM.src.main.java.model.Book;
import LIBRARY_SYSTEM.src.main.java.model.Person;

import java.util.List;
import java.util.Queue;

public interface RequestService {
    void requestBasedOnPriority(Queue<Person> personQueue, List<Book> books);
    void requestBasedOnFIFO(List<Person> personList, List<Book> books);
}