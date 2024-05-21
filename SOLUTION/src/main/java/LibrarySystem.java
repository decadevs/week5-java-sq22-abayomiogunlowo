package SOLUTION.src.main.java;


import SOLUTION.src.main.java.model.Book;
import SOLUTION.src.main.java.model.Person;
import SOLUTION.src.main.java.service.LibraryService;
import SOLUTION.src.main.java.service.impl.LibraryServiceImpl;

public class LibrarySystem {
    public static void main(String[] args) {
        LibraryService library = new LibraryServiceImpl(true);

        Book book1 = new Book("Java Programming", "Author A", 2);
        Book book2 = new Book("Python Programming", "Author B", 1);

        library.addBook(book1);
        library.addBook(book2);

        Person teacher = new Person("Mr. Smith");
        Person student1 = new Person("Alice", 10);
        Person student2 = new Person("Bob", 12);

        System.out.println(library.requestBook(teacher, "Java Programming")); // Book borrowed
        System.out.println(library.requestBook(student2, "Java Programming")); // Book borrowed
        System.out.println(library.requestBook(student1, "Java Programming")); // Added to waiting list

        library.returnBook(teacher, "Java Programming");
        System.out.println(library.requestBook(student1, "Java Programming")); // Book borrowed
    }
}