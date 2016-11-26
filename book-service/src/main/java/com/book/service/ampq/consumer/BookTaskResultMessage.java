package com.book.service.ampq.consumer;

/**
 * The messaged received in the consumer.
 *
 * @author afernandez
 */
public class BookTaskResultMessage {

    private long id;
    private String isbn;
    private String description;

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
