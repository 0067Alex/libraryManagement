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
    private final int bookID;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private Year publicationYear; //Importé java.time.Year
    private Date addedToLibrary;     //Importé java.util.Date
    private boolean available;

    // TODO: Implementar constructor según el ejercicio 1
    public Book(String title, String author, String publisher, String isbn, Year publicationYear) {
        this.bookID = nextID++;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.addedToLibrary = new Date();
        this.available = true;
    }

    // TODO: Implementar getters y setters según el ejercicio 1
    //Book ID (only Get)
    public int getBookID() {
        return bookID;
    }
    //Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
        this.available = true;
        return available;
    }

    public boolean isAvailable() {
        return available;
    }
    //Added to library Date
    public Date getAddedToLibrary() {
        return addedToLibrary;
    }


    // TODO: Implementar método toString según el ejercicio 1
    @Override
    public String toString() {
        return "Book{" +
                "ID=" + bookID +
                ", Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", Publisher='" + publisher + '\'' +
                ", ISBN='" + isbn + '\'' +
                ", Year=" + publicationYear +
                ", Available=" + available +
                '}';
    }
    // TODO: Implementar método equals para comparar libros por ISBN
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return isbn != null && isbn.equals(other.isbn);}
}
