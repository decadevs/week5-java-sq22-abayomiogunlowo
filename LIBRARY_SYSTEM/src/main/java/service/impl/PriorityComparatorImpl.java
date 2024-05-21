package LIBRARY_SYSTEM.src.main.java.service.impl;

import LIBRARY_SYSTEM.src.main.java.model.Person;
import LIBRARY_SYSTEM.src.main.java.service.PriorityComparator;

public class PriorityComparatorImpl implements PriorityComparator {
    @Override
    public int compare(Person p1, String bookTitle1, Person p2, String bookTitle2) {
        // Check if one is a teacher and the other is not
        if (p1.isTeacher() && !p2.isTeacher()) {
            return -1;
        } else if (!p1.isTeacher() && p2.isTeacher()) {
            return 1;
        }

        // If both are students, compare by grade level
        if (!p1.isTeacher() && !p2.isTeacher()) {
            return Integer.compare(p2.getGradeLevel(), p1.getGradeLevel());
        }

        // If both are teachers, they are considered equal in priority
        return 0;
    }
}