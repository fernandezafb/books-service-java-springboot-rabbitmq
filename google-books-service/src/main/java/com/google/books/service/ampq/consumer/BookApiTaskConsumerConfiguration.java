package com.google.books.service.ampq.consumer;

import com.google.books.service.ampq.RabbitMqConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Consumer of tasks received from the main queue and produced from book service.
 *
 * @author afernandez
 */
@Configuration
public class BookApiTaskConsumerConfiguration extends RabbitMqConfiguration {

    private final String tasksQueue = "tasks.queue";

    @Autowired
    private BookApiTaskConsumerResultHandler resultHandler;

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

    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(this.tasksQueue);
        container.setMessageListener(messageListenerAdapter());
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(resultHandler, jsonMessageConverter());
    }
}
