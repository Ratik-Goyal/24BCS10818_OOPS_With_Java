package com.library.service;

import com.library.books.Book;
import com.library.exception.BookNotFoundException;
import com.library.exception.BookNotAvailableException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private static final String FILE_NAME = "books.txt";
    private List<Book> books = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
        saveToFile();
        System.out.println("Book added successfully!");
    }

    public void viewBooks() {
        loadFromFile();
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n=== Library Books ===");
        for (Book book : books) {
            book.displayBook();
        }
    }

    public void issueBook(int bookId) throws BookNotFoundException, BookNotAvailableException {
        loadFromFile();
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        if (book.getAvailableCopies() == 0) {
            throw new BookNotAvailableException("Book '" + book.getTitle() + "' is not available.");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        saveToFile();
        System.out.println("Book issued successfully! Remaining copies: " + book.getAvailableCopies());
    }

    public void returnBook(int bookId) throws BookNotFoundException {
        loadFromFile();
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        saveToFile();
        System.out.println("Book returned successfully! Available copies: " + book.getAvailableCopies());
    }

    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                writer.write(book.getBookId() + "," + book.getTitle() + "," + 
                           book.getAuthor() + "," + book.getAvailableCopies());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        books.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    int copies = Integer.parseInt(parts[3]);
                    books.add(new Book(id, title, author, copies));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
