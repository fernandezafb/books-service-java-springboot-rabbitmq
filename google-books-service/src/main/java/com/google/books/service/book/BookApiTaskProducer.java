package com.google.books.service.book;

import com.google.books.service.ampq.MessageQueue;
import com.google.books.service.ampq.consumer.BookApiTaskResultMessage;
import com.google.books.service.ampq.producer.BookApiTaskMessage;
import com.google.books.service.ampq.producer.BookApiTaskProducerConfiguration;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Producer of tasks that are sent to the tasks result queue.
 *
 * @author afernandez
 */
@Component
public class BookApiTaskProducer {

    private static final int INITIAL_DELAY = 1000;
    private static final Long MAX_RETRIES = 30L;

    @Autowired
    private BookApiTaskProducerConfiguration bookApiTaskProducerConfiguration;

    public void sendUpdatedBook(BookApiTaskMessage taskMessage) {
        bookApiTaskProducerConfiguration.rabbitTemplate()
                .convertAndSend(MessageQueue.TASKS_RESULT_QUEUE, taskMessage);
    }

    public void sendDelayedUpdatedBook(List<HashMap<String, Object>> xDeathHeader, Message message, BookApiTaskResultMessage taskMessage) {
        bookApiTaskProducerConfiguration.rabbitTemplate()
                .convertAndSend(MessageQueue.DELAYED_EXCHANGE, MessageQueue.TASKS_QUEUE, taskMessage,
                        msg -> postProcessMessage(xDeathHeader, message));
    }

    private Message postProcessMessage(List<HashMap<String, Object>> xDeathHeader, Message message) throws AmqpException {
        updateDelay(message.getMessageProperties().getReceivedDelay(), message);
        discardFailedMessage(xDeathHeader);
        return message;
    }

    private void updateDelay(Integer delay, Message message) {
        if (delay == null) {
            message.getMessageProperties().setDelay(INITIAL_DELAY);
        } else {
            message.getMessageProperties().setDelay(Math.abs(delay * 2));
        }
    }

    private void discardFailedMessage(List<HashMap<String, Object>> xDeathHeader) {
        final HashMap<String, Object> headers = xDeathHeader.get(0);
        if (headers.get("count") == MAX_RETRIES) {
            // Send message to trash queue to fix it manually
        }
    }
}
