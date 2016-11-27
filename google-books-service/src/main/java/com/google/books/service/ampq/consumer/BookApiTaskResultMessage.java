package com.google.books.service.ampq.consumer;

/**
 * The messaged received in the consumer.
 *
 * @author afernandez
 */
public class BookApiTaskResultMessage {

    private long id;
    private String isbn;

    public BookApiTaskResultMessage() {
    }

    public BookApiTaskResultMessage(long id, String isbn) {
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
