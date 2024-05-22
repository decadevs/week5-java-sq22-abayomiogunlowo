package LIBRARY_SYSTEM.src.test.service.impl;

import LIBRARY_SYSTEM.src.main.java.model.Person;
import LIBRARY_SYSTEM.src.main.java.service.PriorityComparator;
import LIBRARY_SYSTEM.src.main.java.service.impl.PriorityComparatorImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class PriorityComparatorImplTest {

    private PriorityComparator priorityComparator;

    @BeforeEach
    void setUp() {
        priorityComparator = new PriorityComparatorImpl();
    }

    @Test
    public void testCompare_TeacherVsStudent() {
        Person teacher = new Person("Teacher");
        Person student = new Person("Student", 10);

        int result = priorityComparator.compare(teacher, "Book1", student, "Book2");
        assertEquals(-1, result);
    }

    @Test
    public void testCompare_StudentVsTeacher() {
        Person student = new Person("Student", 10);
        Person teacher = new Person("Teacher");

        int result = priorityComparator.compare(student, "Book1", teacher, "Book2");
        assertEquals(1, result);
    }

    @Test
    public void testCompare_BothTeachers() {
        Person teacher1 = new Person("Teacher1");
        Person teacher2 = new Person("Teacher2");

        int result = priorityComparator.compare(teacher1, "Book1", teacher2, "Book2");
        assertEquals(0, result);
    }

    @Test
    public void testCompare_StudentsDifferentGrades() {
        Person student1 = new Person("Student1", 10);
        Person student2 = new Person("Student2", 12);

        int result = priorityComparator.compare(student1, "Book1", student2, "Book2");
        assertEquals(1, result);  // student2 has higher priority (higher grade)
    }

    @Test
    public void testCompare_StudentsSameGrades() {
        Person student1 = new Person("Student1", 10);
        Person student2 = new Person("Student2", 10);

        int result = priorityComparator.compare(student1, "Book1", student2, "Book2");
        assertEquals(0, result);  // same grade, same priority
    }
}