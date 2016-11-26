package com.book.service.ampq.consumer;

import com.book.service.ampq.RabbitMqConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Consumer of tasks that Google Books Service will produce.
 *
 * @author afernandez
 */
@Configuration
public class BookTaskConsumerConfiguration extends RabbitMqConfiguration {

    private final String tasksResultQueue = "tasks.result.queue";

    @Autowired
    private BookTaskConsumerResultHandler resultHandler;

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

    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(tasksResultQueue);
        container.setMessageListener(messageListenerAdapter());
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(resultHandler, jsonMessageConverter());
    }
}
