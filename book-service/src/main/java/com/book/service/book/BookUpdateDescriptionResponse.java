package com.book.service.book;

/**
 * Response returned after updating the description, clicking on Sync button in the UI.
 *
 * @author afernandez
 */
public class BookUpdateDescriptionResponse {

    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";

    private String status;
    private String isbn;

    public BookUpdateDescriptionResponse() {
    }

    public BookUpdateDescriptionResponse(String status, String isbn) {
        this.status = status;
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
