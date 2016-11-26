package com.book.service.ampq.producer;

/**
 * Message to send book data to the queue.
 *
 * @author afernandez
 */
public class BookTaskMessage {

    private String isbn;

    public BookTaskMessage(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
