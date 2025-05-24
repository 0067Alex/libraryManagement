package es.ing.tomillo.library.service;

import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Library {
    // Lista de usuarios
    private final List<User> users;
    // Lista de libros
    private final List<Book> books;

    //Getter para listas
    public List<User> getUsers() {
        return users;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Library() {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    // Mostrar por pantalla todos los usuarios registrados en la biblioteca
    public void listUsers() {
        for (User user : users) {
            System.out.println("ID: " + user.getUserID());
            System.out.println("Nombre: " + user.getName());
            System.out.println("Número de libros reservados: " + user.getBookCount());
        }
    }

    //Añadir usuario a la biblioteca
    public void addUser(String userName, String DNI) {
        User user = new User(userName, DNI);
        users.add(user);
        System.out.println("User added succesfully");
        System.out.println("Assigned User ID: " + user.getUserID());
    }

    //Añadir libro a la biblioteca
    public void addBook(String title, String author, String publisher, String isbn, Year publicationYear) {
        Book book = new Book(title, author, publisher, isbn, publicationYear);
        books.add(book);
        System.out.println("Book added successfully.");
        System.out.println("Assigned Book ID: " + book.getBookID());
        System.out.println("Entry date: " + book.getAddedToLibrary());
    }

    // TODO: Implementar método prestarLibro según el ejercicio 3
    public void borrowBook(User userWhoBorrows, Book borrowedBook) {
        // Check if the book is already borrowed
        if (borrowedBook.isBorrowed()) {
            System.out.println("Sorry, the book '" + borrowedBook.getTitle() +
                    "' is already borrowed by " + borrowedBook.getBorrowedBy().getName() + ".");
            return;
        }
        // Mark the book as borrowed by the user
        borrowedBook.borrowBook(userWhoBorrows);

        // Confirm the borrowing action
        System.out.println("The book '" + borrowedBook.getTitle() +
                "' has been successfully borrowed by " + userWhoBorrows.getName() + ".");
    }

    // TODO: Implementar método devolverLibro según el ejercicio 3
    public void returnBook(User userThatReturns, Book bookThatIsReturned) {
        if (!userThatReturns.hasBorrowed(bookThatIsReturned)) {
            System.out.println("Error: The user has not borrowed this book.");
            return;
        }
        // Remove the book from the user's borrowed list
        userThatReturns.returnBook(bookThatIsReturned);

        // Mark the book as available in the library (if maintaining an availability status)
        bookThatIsReturned.setAvailable(true);

        // Print confirmation message
        System.out.println("The book '" + bookThatIsReturned.getTitle() + "' was successfully returned by " + userThatReturns.getName() + ".");
    }

    // TODO: Implementar método buscarLibroPorTitulo según el ejercicio 4
    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    // TODO: Implementar método buscarLibroPorAutor según el ejercicio 4
    public List<Book> searchBookByAuthor(String author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    // TODO: Implementar método listarLibrosDisponibles según el ejercicio 5
    // Debe mostrar por pantalla todos los libros que están disponibles (isAvailable = true)
    public void listAvailableBooks() {
        System.out.println("Available books:");
        boolean foundAny = false;

        for (Book book : books) {
            if (book.isAvailable()) {
                foundAny = true;
                // Print every field you’ve defined in Book.java
                System.out.println(
                        "ID: " + book.getBookID() +
                                ", Title: " + book.getTitle() +
                                ", Author: " + book.getAuthor() +
                                ", Publisher: " + book.getPublisher() +
                                ", ISBN: " + book.getIsbn() +
                                ", Year: " + book.getPublicationYear() +
                                ", Added: " + book.getAddedToLibrary() +
                                ", Reserved: " + book.isReserved()
                );
            }
        }

        if (!foundAny) {
            System.out.println("  No books are currently available.");
        }
    }

    public User getUserById(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("\n\n\nMade by Sir Alex\n\n\n");
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        String title;
        String author;
        String publisher;
        String isbn;
        Year publicationYear;
        Book book;
        User user = null;
        int userID = 0;


        System.out.println("-------------------\n   Menu Options:");
        System.out.println("1. Add Book");
        System.out.println("2. Add User");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Search Book by Title");
        System.out.println("6. Search Book by Author");
        System.out.println("7. List Available Books");
        System.out.println("8. List Users");
        System.out.println("Q. Quit");
        System.out.println("H. Need more help? use: help");
        System.out.print("--> Don't know how to choose an option? use: tutorial\n");
        while (!quit) {
            System.out.print("Choose an option: ");

            String option = scanner.nextLine().toLowerCase().replaceAll("[^a-z0-9]", ""); // para que automáticamente pase todo a minúscula y a no tener espacios ni puntos//
            switch (option) {
                case "1", "addbook", "1addbook":
                    //Title
                    title = "";
                    while (title.isBlank()) {
                        System.out.print("Enter book title: ");
                        title = scanner.nextLine().trim();
                        if (title.isEmpty()) {
                            System.out.println("Title cannot be empty.");
                        }
                    }
                    //Author
                    author = "";
                    while (author.isBlank()) {
                        System.out.print("Enter book author: ");
                        author = scanner.nextLine().trim();
                        if (author.isEmpty()) {
                            System.out.println("Author cannot be empty.");
                            continue;
                        }
                        int letterCount = author.replaceAll("[^\\p{L}]", "").length();
                        if (letterCount < 2) {
                            System.out.println("Author name must contain at least 2 letters.");
                            author = "";
                            continue;
                        }
                        if (author.matches(".*\\d.*")) {
                            System.out.println("Author name cannot contain numbers. Please enter a valid name.");
                            author = "";
                        }
                    }
                    //Publisher
                    publisher = "";
                    while (publisher.isBlank()) {
                        System.out.print("Enter book publisher: ");
                        publisher = scanner.nextLine().trim();
                        if (publisher.isEmpty()) {
                            System.out.println("Publisher cannot be empty.");
                        }
                        int letterCount = publisher.replaceAll("[^\\p{L}]", "").length();
                        if (letterCount < 4) {
                            System.out.println("Publisher must contain at least 4 characters. Not only numbers");
                            publisher = "";
                            continue;
                        }
                        //ISBN
                        isbn = "";
                        while (isbn.isBlank()) {
                            System.out.print("Enter book ISBN: ");
                            isbn = scanner.nextLine().trim();
                            if (isbn.isEmpty()) {
                                System.out.println("ISBN cannot be empty.");
                                continue;
                            }
                            String cleanedIsbn = isbn.replaceAll("-", "");
                            boolean isValidIsbn10 = Pattern.matches("\\d{9}[\\dX]", cleanedIsbn);
                            boolean isValidIsbn13 = Pattern.matches("\\d{13}", cleanedIsbn);

                            if (!isValidIsbn10 && !isValidIsbn13) {
                                System.out.println("Invalid ISBN format. Enter a valid ISBN.\nFor example:\nISBN 10: 0-19-852663-6" + "\nISBN 13: 978-3-16-148410-0");
                                isbn = ""; // Forzar repetir el bucle
                            }
                        }
                        //PublicationYear
                        publicationYear = null;
                        while (publicationYear == null) {
                            System.out.print("Enter book publication year (yyyy): ");
                            String yearInput = scanner.nextLine().trim();
                            if (!yearInput.matches("\\d{4}")) {
                                System.out.println("Invalid year. Please enter a 4-digit year (e.g. 2022).");
                                continue;
                            }
                            try {
                                publicationYear = Year.parse(yearInput);
                            } catch (Exception e) {
                                System.out.println("Error parsing year. Please try again.");
                            }
                        }
                        // new book
                        book = new Book(title, author, publisher, isbn, publicationYear);
                        library.addBook(title, author, publisher, isbn, publicationYear);
                        System.out.println((book.getTitle()) + " by " + (book.getAuthor()) + " added successfully.");
                        System.out.println("Assigned Book ID: " + book.getBookID());
                        System.out.println("Entry date: " + book.getAddedToLibrary());
                    }
                    break;
                //
                case "2", "adduser", "2adduser":
                    String name = "";
                    while (name.isBlank()) {
                        System.out.print("Enter user name (letters only, max 30 characters): ");
                        name = scanner.nextLine().trim();

                        if (name.isEmpty()) {
                            System.out.println("Name cannot be empty.");
                            continue;
                        }

                        if (name.length() > 30) {
                            System.out.println("Name cannot be longer than 30 characters.");
                            name = "";
                            continue;
                        }

                        if (!Pattern.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+", name)) {
                            System.out.println("Name can only contain letters and spaces. Please enter a valid name.");
                            name = "";
                        }
                    }
                    String DNI;
                    while (true) {
                        System.out.print("Enter user DNI (8 digits + 1 letter): ");
                        DNI = scanner.nextLine().trim();

                        if (Pattern.matches("\\d{8}[A-Za-z]", DNI)) {
                            break;
                        } else {
                            System.out.println("Invalid DNI. It must have exactly 8 digits followed by 1 letter. Example: 12345678A");
                        }
                    }
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine().trim();
                    User newUser = new User(DNI, userName);
                    library.addUser (DNI, userName);
                    System.out.println("Added user: " + newUser.getName() +
                            " (ID: " + newUser.getUserID() + ")");


            case "3", "borrowbook", "3borrowbook":{
                    System.out.print("Enter user ID: ");
                    int borrowUserId = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Enter book title: ");
                    String borrowTitle = scanner.nextLine().trim();
                    User borrower = library.getUserById(borrowUserId);
                    Book toBorrow = library.searchBookByTitle(borrowTitle);
                    if (borrower != null && toBorrow != null) {
                        library.borrowBook(borrower, toBorrow);
                    } else {
                        System.out.println("User or book not found.");
                    }
                }

        case "4", "returnbook", "4returnbook":{
                    System.out.print("Enter user ID: ");
                    int returnUserId = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Enter book title: ");
                    String returnTitle = scanner.nextLine().trim();
                    User returner = library.getUserById(returnUserId);
                    Book toReturn = library.searchBookByTitle(returnTitle);
                    if (returner != null && toReturn != null) {
                        library.returnBook(returner, toReturn);
                    } else {
                        System.out.println("User or book not found.");
                    }
                }

                case "5", "searchbookbytitle", "5searchbookbytitle":{
                    System.out.print("Enter book title: ");
                    String searchTitle = scanner.nextLine().trim();
                    Book found = library.searchBookByTitle(searchTitle);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Book not found.");
                    }
                }

                case "6", "searchbookbyauthor", "6searchbookbyauthor":{
                    System.out.print("Enter book author: ");
                    String searchAuthor = scanner.nextLine().trim();
                    List<Book> list = library.searchBookByAuthor(searchAuthor);
                    if (!list.isEmpty()) {
                        System.out.println("Books by " + searchAuthor + ":");
                        for (Book b : list) {
                            System.out.println(" - " + b);
                        }
                    } else {
                        System.out.println("No books found for author: " + searchAuthor);
                    }
                }

                case "7", "listavailablebooks", "7listavailablebooks":{
                    library.listAvailableBooks();
                }

                case "8", "listusers", "8listusers":{
                    library.listUsers();
                }

                case "q", "quit", "qquit":{
                    quit = true;
                    System.out.println("Goodbye!");
                }

                case "h", "help", "hhelp", "morehelp", "tutorial":
                    System.out.println("""
                            Menu commands:
                             1 / addbook — Add a new book
                             2 / adduser — Register a new user
                             3 / borrowbook — Borrow a book
                             4 / returnbook — Return a book
                             5 / searchbookbytitle — Find a book by its title
                             6 / searchbookbyauthor — List all books by an author
                             7 / listavailablebooks — Show all available books
                             8 / listusers — List all users
                             Q / quit — Exit the application
                            """);
                default: {
                    System.out.println("Invalid option. Type 'help' to see available commands.");
                }
            }
        }
    }
}