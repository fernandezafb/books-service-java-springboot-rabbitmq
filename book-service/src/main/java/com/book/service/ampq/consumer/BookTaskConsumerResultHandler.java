package com.book.service.ampq.consumer;

import com.book.service.book.Book;
import com.book.service.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Listener implementation to handle the received message.
 *
 * @author afernandez
 */
@Component
public class BookTaskConsumerResultHandler {

    @Autowired
    private BookRepository bookRepository;

    public void handleMessage(BookTaskResultMessage resultMessage) {
        final Book book = bookRepository.findOne(resultMessage.getId());
        book.setDescription(resultMessage.getDescription());

        bookRepository.save(book);
    }
}
