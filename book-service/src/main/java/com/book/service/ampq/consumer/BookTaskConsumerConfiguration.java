package com.book.service.ampq.consumer;

import com.book.service.ampq.MessageQueue;
import com.book.service.ampq.RabbitMqConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Consumer of tasks that Google Books Service will push to the result queue.
 *
 * @author afernandez
 */
@Configuration
public class BookTaskConsumerConfiguration extends RabbitMqConfiguration {

    private final String tasksResultQueue = MessageQueue.TASKS_RESULT_QUEUE;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(tasksResultQueue);
        template.setQueue(tasksResultQueue);
        return template;
    }

    @Bean
    public Queue tasksResultQueue() {
        return new Queue(tasksResultQueue);
    }
}
