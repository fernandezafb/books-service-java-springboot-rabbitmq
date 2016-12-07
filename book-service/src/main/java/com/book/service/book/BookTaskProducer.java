package com.book.service.book;

import com.book.service.ampq.MessageQueue;
import com.book.service.ampq.producer.BookTaskMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private RabbitTemplate rabbitTemplate;

    public void sendNewUpdateBookDescriptionTask(BookTaskMessage bookTaskMessage) {
        rabbitTemplate.convertAndSend(MessageQueue.TASKS_QUEUE, bookTaskMessage);
    }
}
