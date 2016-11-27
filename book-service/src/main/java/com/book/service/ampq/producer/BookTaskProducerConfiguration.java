package com.book.service.ampq.producer;

import com.book.service.ampq.MessageQueue;
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

    private final String tasksQueue = MessageQueue.TASKS_QUEUE;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(tasksQueue);
        template.setQueue(tasksQueue);
        template.setMessageConverter(jsonMessageConverterProducer());
        return template;
    }

    @Bean
    public Queue tasksQueue() {
        return new Queue(tasksQueue);
    }
}
