package com.google.books.service.book;

import com.google.books.service.ampq.MessageQueue;
import com.google.books.service.ampq.producer.BookApiTaskMessage;
import com.google.books.service.ampq.producer.BookApiTaskProducerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Producer of tasks that are sent to the tasks result queue.
 *
 * @author afernandez
 */
@Component
public class BookApiTaskProducer {

    @Autowired
    private BookApiTaskProducerConfiguration bookApiTaskProducerConfiguration;

    public void sendUpdatedBook(BookApiTaskMessage taskMessage) {
        bookApiTaskProducerConfiguration.rabbitTemplate()
                .convertAndSend(MessageQueue.TASKS_RESULT_QUEUE, taskMessage);
    }
}
