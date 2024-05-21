package SOLUTION.src.main.java.service.impl;

import SOLUTION.src.main.java.model.Person;
import SOLUTION.src.main.java.service.RequestPriorityComparator;

import java.util.Comparator;

public class RequestPriorityComparatorImpl implements Comparator<Person>, RequestPriorityComparator {
    @Override
    public int compare(Person p1, String bookTitle1, Person p2, String bookTitle2) {
        if (p1.isTeacher() && !p2.isTeacher()) {
            return -1;
        } else if (!p1.isTeacher() && p2.isTeacher()) {
            return 1;
        } else if (!p1.isTeacher() && !p2.isTeacher()) {
            // Both are students
            return Integer.compare(p2.getGradeLevel(), p1.getGradeLevel());
        }
        return 0;
    }

    @Override
    public int compare(Person p1, Person p2) {
        // Not used
        return 0;
    }
}
