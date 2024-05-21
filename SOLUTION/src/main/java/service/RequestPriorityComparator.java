package SOLUTION.src.main.java.service;


import SOLUTION.src.main.java.model.Person;

public interface RequestPriorityComparator {
    int compare(Person p1, String bookTitle1, Person p2, String bookTitle2);
}
