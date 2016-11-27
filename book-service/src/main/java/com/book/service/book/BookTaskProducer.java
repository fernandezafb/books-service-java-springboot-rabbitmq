package com.book.service.book;

import com.book.service.ampq.MessageQueue;
import com.book.service.ampq.producer.BookTaskMessage;
import com.book.service.ampq.producer.BookTaskProducerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Producer of tasks that are sent to the tasks queue.
 *
 * @author afernandez
 */
@Component
public class BookTaskProducer {

    @Autowired
    private BookTaskProducerConfiguration bookTaskProducerConfiguration;

    public void sendNewUpdateBookDescriptionTask(BookTaskMessage bookTaskMessage) {
        bookTaskProducerConfiguration.rabbitTemplate()
                .convertAndSend(MessageQueue.TASKS_QUEUE, bookTaskMessage);
    }
}
