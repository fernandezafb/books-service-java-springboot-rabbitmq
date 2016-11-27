package com.google.books.service.ampq.producer;

/**
 * The book data to push to the queue.
 *
 * @author afernandez
 */
public class BookApiTaskMessage {

    private long id;
    private String isbn;
    private String description;

    public BookApiTaskMessage() {
    }

    public BookApiTaskMessage(long id, String isbn, String description) {
        this.id = id;
        this.isbn = isbn;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
