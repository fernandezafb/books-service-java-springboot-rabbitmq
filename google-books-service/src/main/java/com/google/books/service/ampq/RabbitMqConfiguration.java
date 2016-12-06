package com.google.books.service.ampq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * General configuration for RabbitMQ.
 *
 * @author afernandez
 */
@Configuration
public class RabbitMqConfiguration {

    @Bean
    Exchange exchange() {
        return ExchangeBuilder.directExchange(MessageQueue.DELAYED_EXCHANGE)
                .durable()
                .delayed()
                .build();
    }

    @Bean
    Queue tasksQueue() {
        return QueueBuilder.durable(MessageQueue.TASKS_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", MessageQueue.TASKS_DELAYED_QUEUE)
                .build();
    }

    @Bean
    Queue tasksResultQueue() {
        return new Queue(MessageQueue.TASKS_RESULT_QUEUE);
    }

    @Bean
    Queue tasksDelayedQueue() {
        return QueueBuilder.durable(MessageQueue.TASKS_DELAYED_QUEUE)
                .build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(tasksQueue()).to((DirectExchange) exchange()).with(MessageQueue.TASKS_QUEUE);
    }
}
