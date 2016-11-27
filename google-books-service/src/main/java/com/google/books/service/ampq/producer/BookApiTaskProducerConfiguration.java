package com.google.books.service.ampq.producer;

import com.google.books.service.ampq.RabbitMqConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Producer of tasks that the book service will consume.
 *
 * @author afernandez
 */
@Configuration
public class BookApiTaskProducerConfiguration extends RabbitMqConfiguration {

    private final String tasksResultQueue = "tasks.result.queue";

    public String getTasksResultQueue() {
        return tasksResultQueue;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(tasksResultQueue);
        template.setQueue(tasksResultQueue);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public Queue tasksResultQueue() {
        return new Queue(tasksResultQueue);
    }
}
