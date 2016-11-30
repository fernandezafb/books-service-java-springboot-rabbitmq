package com.google.books.service.ampq.consumer;

import com.google.books.service.ampq.MessageQueue;
import com.google.books.service.ampq.RabbitMqConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Consumer of tasks received from the main queue and produced from book service.
 *
 * @author afernandez
 */
@Configuration
public class BookApiTaskConsumerConfiguration extends RabbitMqConfiguration {

    private final String tasksQueue = MessageQueue.TASKS_QUEUE;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(tasksQueue);
        template.setQueue(tasksQueue);
        return template;
    }

    @Bean
    public Queue tasksQueue() {
        return QueueBuilder.durable(tasksQueue)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", MessageQueue.TASKS_DELAYED_QUEUE)
                .build();
    }
}
