package com.google.books.service.ampq.consumer;

import com.google.books.service.ampq.producer.BookApiTaskMessage;
import com.google.books.service.book.BookApiTaskProducer;
import com.google.books.service.book.BookDescriptionUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Listener implementation to handle the received message.
 *
 * @author afernandez
 */
@Component
public class BookApiTaskConsumerResultHandler {

    @Autowired
    private BookDescriptionUpdater bookDescriptionUpdater;
    @Autowired
    private BookApiTaskProducer bookApiTaskProducer;

    public void handleMessage(BookApiTaskResultMessage resultMessage) {
        final String description = bookDescriptionUpdater.updateDescriptionFromIsbn(resultMessage.getIsbn());
        bookApiTaskProducer.sendUpdatedBook(new BookApiTaskMessage(resultMessage.getId(), resultMessage.getIsbn(), description));
    }
}
