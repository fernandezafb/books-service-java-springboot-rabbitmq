package com.book.service.ampq.producer;

import com.book.service.ampq.RabbitMqConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Producer of tasks that Google Books Service will consume.
 *
 * @author afernandez
 */
@Configuration
public class BookTaskProducerConfiguration extends RabbitMqConfiguration {

    private final String tasksQueue = "tasks.queue";

    public String getTasksQueue() {
        return tasksQueue;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(this.tasksQueue);
        template.setQueue(this.tasksQueue);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public Queue tasksQueue() {
        return new Queue(this.tasksQueue);
    }
}
