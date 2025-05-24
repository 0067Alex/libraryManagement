package es.ing.tomillo.library.model;

import java.time.Year;
import java.util.Date;

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
    private Year publicationYear; //Importé java.time.Year
    private int bookID;
    private Date addedToLibrary;     //Importé java.util.Date
    private boolean available;

    // TODO: Implementar constructor según el ejercicio 1
    public Book(String title, String author, String isbn, String publisher, Year publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.bookID = nextID++;
        this.addedToLibrary = new Date();
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
    //Publication Year
    public Year getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }
    //Availability
    public boolean setAvailable(boolean available) {
        this.available = available;
        return available;
    }
        public boolean isAvailable() {
        return available;
    }
    //Book ID (only Get)
    public int getBookID() {
        return bookID;
    }
    //Added to library Date
    public Date getAddedToLibrary() {
        return addedToLibrary;
    }
}

    // TODO: Implementar método toString según el ejercicio 1

    // TODO: Implementar método equals para comparar libros por ISBN


