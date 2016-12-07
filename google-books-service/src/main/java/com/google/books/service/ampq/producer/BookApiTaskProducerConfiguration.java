package com.google.books.service.ampq.producer;

import com.google.books.service.ampq.MessageQueue;
import com.google.books.service.ampq.RabbitMqConfiguration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Producer of tasks that the book service will consume.
 *
 * @author afernandez
 */
@Configuration
public class BookApiTaskProducerConfiguration {

    @Autowired
    private ConnectionFactory cachingConnectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        template.setRoutingKey(MessageQueue.TASKS_RESULT_QUEUE);
        template.setQueue(MessageQueue.TASKS_RESULT_QUEUE);
        template.setMessageConverter(jsonMessageConverterProducer());
        return template;
    }

    @Bean
    public MessageConverter jsonMessageConverterProducer() {
        return new Jackson2JsonMessageConverter();
    }
}
