package es.ing.tomillo.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

public class User {

    // - nombre (String)
    // - id (int)
    // - librosPrestados (List de Libro)
    private String name;
    private int id;
    private final List<Book> borrowedBooks;
    private static final int MAX_BORROWED_BOOKS = 5;
    private final List<Book> reservedBooks;
    private static final int MAX_RESERVED_BOOKS = 1;

    // Constructor con un maximo de 5 libros prestados
    public User(String name, int id) {
        this.name = name;
        this.id = id;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        System.out.println(book.getTitle()+" is not available");}
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
    public void reserveBook(Book book) {
    }

    // TODO: Implementar método toString para mostrar la información del usuario
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", borrowedBooks=" + borrowedBooks.size() +
                '}';
    }

    // TODO: Implementar método equals para comparar usuarios por ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



