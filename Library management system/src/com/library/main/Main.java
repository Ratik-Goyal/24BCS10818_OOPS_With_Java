package com.library.main;

import com.library.books.Book;
import com.library.service.LibraryService;
import com.library.exception.BookNotFoundException;
import com.library.exception.BookNotAvailableException;

public class Main {
    public static void main(String[] args) {
        LibraryService library = new LibraryService();

        System.out.println("=== Library Management System ===");

        // Add books
        System.out.println("\n1. Adding Books");
        library.addBook(new Book(101, "Java Programming", "James Gosling", 5));
        library.addBook(new Book(102, "Python Basics", "Guido van Rossum", 3));
        library.addBook(new Book(103, "Data Structures", "Robert Sedgewick", 2));

        // View all books
        System.out.println("\n2. Viewing All Books");
        library.viewBooks();

        // Issue a book
        System.out.println("\n3. Issuing Book (ID: 101)");
        try {
            library.issueBook(101);
        } catch (BookNotFoundException | BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // View books after issuing
        System.out.println("\n4. Viewing Books After Issue");
        library.viewBooks();

        // Return a book
        System.out.println("\n5. Returning Book (ID: 101)");
        try {
            library.returnBook(101);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Try to issue a book that doesn't exist
        System.out.println("\n6. Trying to Issue Non-Existent Book (ID: 999)");
        try {
            library.issueBook(999);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Issue all copies and try to issue one more
        System.out.println("\n7. Issuing All Copies of Book (ID: 103)");
        try {
            library.issueBook(103);
            library.issueBook(103);
            System.out.println("\n8. Trying to Issue When No Copies Available");
            library.issueBook(103);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Final view
        System.out.println("\n9. Final Book Status");
        library.viewBooks();
    }
}
