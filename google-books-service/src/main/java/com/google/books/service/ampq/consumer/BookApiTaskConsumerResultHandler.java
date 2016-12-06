package com.google.books.service.ampq.consumer;

import com.google.books.service.ampq.MessageQueue;
import com.google.books.service.ampq.producer.BookApiTaskMessage;
import com.google.books.service.book.BookApiTaskProducer;
import com.google.books.service.book.BookDescriptionUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Listener implementation to handle the received message.
 *
 * @author afernandez
 */
@Component
public class BookApiTaskConsumerResultHandler {
    private static final Logger logger = LoggerFactory.getLogger(BookApiTaskConsumerResultHandler.class);

    @Autowired
    private BookDescriptionUpdater bookDescriptionUpdater;
    @Autowired
    private BookApiTaskProducer bookApiTaskProducer;

    @RabbitListener(queues = MessageQueue.TASKS_QUEUE)
    public void receiveMessage(final BookApiTaskResultMessage resultMessage) {
        try {
            final String description = bookDescriptionUpdater.updateDescriptionFromIsbn(resultMessage.getIsbn());
            bookApiTaskProducer.sendUpdatedBook(new BookApiTaskMessage(resultMessage.getId(), resultMessage.getIsbn(), description));
        } catch (Exception ex) {
            logger.debug("Task processing failed with ID: {} || ISBN: {}", resultMessage.getId(), resultMessage.getIsbn());
            throw new AmqpRejectAndDontRequeueException("Rejected for failure.");
        }
    }

    @RabbitListener(queues = MessageQueue.TASKS_DELAYED_QUEUE)
    public void receiveDelayedMessage(@Header(required = false, name = "x-death") List<HashMap<String, Object>> xDeathHeader, Message message,
                                      BookApiTaskResultMessage payload) {
        logger.debug("Thread name: {}", Thread.currentThread().getName());
        bookApiTaskProducer.sendDelayedUpdatedBook(xDeathHeader, message, payload);
    }
}