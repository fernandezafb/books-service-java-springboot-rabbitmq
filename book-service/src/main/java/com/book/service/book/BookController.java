package com.book.service.book;

import com.book.service.ampq.producer.BookTaskMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookController.
 *
 * @author afernandez
 */
@RestController
public class BookController {

    @Autowired
    private BookTaskProducer bookTaskProducer;

    @GetMapping("/update/description/{isbn}")
    public String updateBookDescription(@PathVariable String isbn) {
        bookTaskProducer.sendNewUpdateBookDescriptionTask(new BookTaskMessage(isbn));
        return "OK";
    }
}