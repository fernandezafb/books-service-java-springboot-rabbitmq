package com.book.service.book;

import com.book.service.ampq.producer.BookTaskMessage;
import com.book.service.ampq.producer.BookTaskProducerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BookService.
 *
 * @author afernandez
 */
@Component
public class BookTaskProducer {

    @Autowired
    private BookTaskProducerConfiguration bookTaskProducerConfiguration;

    public void sendNewUpdateBookDescriptionTask(BookTaskMessage bookTaskMessage) {
        bookTaskProducerConfiguration.rabbitTemplate()
                .convertAndSend(bookTaskProducerConfiguration.getTasksQueue(), bookTaskMessage);
    }
}
