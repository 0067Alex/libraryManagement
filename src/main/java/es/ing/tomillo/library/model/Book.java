package es.ing.tomillo.library.model;

import java.util.List;
import java.util.Objects;
public class Book {
    // TODO: Implementar los atributos según el ejercicio 1
    // - title (String)
    // - author (String)
    // - isbn (String)
    // - available (boolean)
    private static int nextID = 0;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int bookID;
    private int quantity;
    private boolean available;

    // TODO: Implementar constructor según el ejercicio 1
    public Book(String title, String author, String isbn, String publisher) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.bookID = nextID++;;
        }
    // TODO: Implementar getters y setters según el ejercicio 1
    //Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    //Author
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    //ISBN
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    //Publisher
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    //Availability
    public boolean setAvailable(boolean available) {
        this.available = available;
    }
        public boolean isAvailable() {
        return available;
    }

    //Book ID (only Get)
    public int getBookID() {
        return bookID;
    }
    // TODO: Implementar método toString según el ejercicio 1

    // TODO: Implementar método equals para comparar libros por ISBN

}

