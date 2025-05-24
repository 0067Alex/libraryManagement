package es.ing.tomillo.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.time.Year;
import java.util.Date;


public class User {

    // - nombre (String)
    // - id (int)
    // - librosPrestados (List de Libro)

    private static int nextUserID = 0;
    private int userID;
    private String DNI;
    private String name;
    private final List<Book> borrowedBooks;
    private static final int MAX_BORROWED_BOOKS = 5;
    private final List<Book> reservedBooks;
    private static final int MAX_RESERVED_BOOKS = 1;

    // Constructor con un maximo de 5 libros prestados
    public User(String DNI, String name) {
        this.userID = nextUserID++;
        this.DNI = DNI;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
    }
//Getters and Setters
//Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//ID
    public int getUserID() {
        return userID;
    }
    //List borrowedBooks
    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);    //Ezequiel me explicó que la lista podría ser modificada desde el exterior, por eso use unmodifiableList//
}
//List reservedbooks
    public List<Book> getReservedBooks() {
        return Collections.unmodifiableList(reservedBooks);
    }
//bookCount
    public int getBookCount() {
        return borrowedBooks.size();
    }

    // TODO: Implementar método prestarLibro según el ejercicio 2
    // Debe añadir un libro a la lista de libros prestados
    public void borrowBook(Book book) {
        if (borrowedBooks.size() >= MAX_BORROWED_BOOKS) {
            System.out.println("Limit of borrowed books reached");
        }
        else if (!book.isAvailable()) {
            System.out.println(book.getTitle()+" is not available");
        }
        else {
            borrowedBooks.add(book);
            book.setAvailable(false);
            System.out.println(book.getTitle() + " borrowed for the next 15 minutes");
        }
    }

    // TODO: Implementar método devolverLibro según el ejercicio 2
    // Debe eliminar un libro a lista  de libros prestados
    public void returnBook(Book book) {
       if (borrowedBooks.contains(book)) {
        borrowedBooks.remove(book);
       System.out.println(book.getTitle()+ " returned");}
    else {
        System.out.println("No book found");
    }
    }
    // TODO: Implementar método reservarLibro según el ejercicio 2
    // Debe permitir reservar libros que no están disponibles
    public String reserveBookIfNotAvailable (Book book) {
        if (reservedBooks.contains(book)) {
            return "You have already reserved the book '" + book.getTitle() + "'.";
        } else if (book.isReserved()) {
            return "The book '" + book.getTitle() + "' is already reserved by another user.";
        } else if (borrowedBooks.contains(book)) {
            return "You have already borrowed the book '" + book.getTitle() + "'. Reservation is not needed.";
        } else if (book.isAvailable()) {
            return "The book '" + book.getTitle() + "' is currently available. You can borrow it instead of reserving.";
        } else if (reservedBooks.size() >= MAX_RESERVED_BOOKS) {
            return "You have reached the maximum reservation limit of " + MAX_RESERVED_BOOKS + " book(s).";
        } else {
            reservedBooks.add(book);
            book.setReserved(true);
            return "The book '" + book.getTitle() + "' has been reserved successfully.";
        }
    }
//Method para ver si el usuario ha pedido prestado el libro en cuestión
    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }
// Method for reserving a book
public String reserveBook(Book book) {
    // Check if the user has reached the maximum reservation limit
    if (reservedBooks.size() >= MAX_RESERVED_BOOKS) {
        return "You have reached the maximum number of reserved books (" + MAX_RESERVED_BOOKS + ")";
    }

    // Check if the book is already reserved by the user
    if (reservedBooks.contains(book)) {
        return "You have already reserved '" + book.getTitle() + "'";
    }

    // Check if the book is reserved by someone else
    if (book.isReserved()) {
        return "The book '" + book.getTitle() + "' is already reserved by someone else.";
    }

    // Check if the user has already borrowed the book
    if (borrowedBooks.contains(book)) {
        return "You have already borrowed '" + book.getTitle() + "', reservation is not allowed.";
    }

    // Check if the book is available and does not need a reservation
    if (book.isAvailable()) {
        return "This book is available for borrowing - no need to reserve.";
    }

    // If all conditions pass, reserve the book
    reservedBooks.add(book);
    book.setReserved(true); // Marks the book as reserved
    return "'" + book.getTitle() + "' has been reserved successfully.";
}


    // Add this method to handle when a book is returned and notify reserved users
public void checkReservedBook(Book book) {
    if (reservedBooks.contains(book) && book.isAvailable()) {
        System.out.println("The book you reserved '" + book.getTitle() + "' is now available!");
        reservedBooks.remove(book);
    }
}

    // TODO: Implementar método toString para mostrar la información del usuario
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userID=" + userID +
                ", borrowedBooks=" + borrowedBooks.size() +
                '}';
    }

    // TODO: Implementar método equals para comparar usuarios por ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }
}