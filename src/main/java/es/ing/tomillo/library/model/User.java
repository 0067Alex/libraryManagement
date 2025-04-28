package es.ing.tomillo.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    // - nombre (String)
    // - id (int)
    // - librosPrestados (List de Libro)
    private String name;
    private int id;
    private final List<Book> borrowedBooks;
    private static final int MAX_BORROWED_BOOKS = 5;

    // Constructor con un maximo de 5 libros prestados
    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getBookCount() {
        return borrowedBooks.size();
    }

    // TODO: Implementar método prestarLibro según el ejercicio 2
    // Debe añadir un libro al array de libros prestados
    public void borrowBook(Book book) {
        if (borrowedBooks.size() < MAX_BORROWED_BOOKS) {
            borrowedBooks.add(book);
            book.setAvailable(false);
        } else {
            System.out.println("No se pueden prestar más libros. Límite alcanzado.");
        }
    }

    // TODO: Implementar método devolverLibro según el ejercicio 2
    // Debe eliminar un libro del array de libros prestados
    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
        } else {
            System.out.println("Este libro no estaba prestado a este usuario.");
        }
    }

    // TODO: Implementar método reservarLibro según el ejercicio 2
    // Debe permitir reservar libros que no están disponibles
    public void reserveBook(Book book) {
        if (!book.isAvailable()) {
            System.out.println("El libro ya está reservado.");
        } else {
            System.out.println("El libro está disponible para préstamo.");
        }
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


