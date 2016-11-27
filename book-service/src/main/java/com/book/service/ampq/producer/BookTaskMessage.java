package com.book.service.ampq.producer;

/**
 * Message to send book data to the queue.
 *
 * @author afernandez
 */
public class BookTaskMessage {

    private long id;
    private String isbn;

    public BookTaskMessage() {
    }

    public BookTaskMessage(long id, String isbn) {
        this.id = id;
        this.isbn = isbn;
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
}
