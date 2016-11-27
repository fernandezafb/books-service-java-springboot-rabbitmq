package com.book.service.ampq.consumer;

import com.book.service.ampq.MessageQueue;
import com.book.service.book.Book;
import com.book.service.book.BookRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitListener(queues = MessageQueue.TASKS_RESULT_QUEUE)
    public void receiveMessage(final BookTaskResultMessage resultMessage) {
        final Book book = bookRepository.findOne(resultMessage.getId());
        book.setDescription(parseDescription(resultMessage.getDescription()));

        bookRepository.save(book);
    }

    private String parseDescription(String description) {
        if (description.length() > 250) {
            return description.substring(0, 249);
        }
        return description;
    }
}
