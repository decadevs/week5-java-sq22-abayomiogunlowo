package LIBRARY_SYSTEM.src.main.java.service;

import LIBRARY_SYSTEM.src.main.java.model.Person;

public interface PriorityComparator {
    int compare(Person p1, String bookTitle1, Person p2, String bookTitle2);
}
