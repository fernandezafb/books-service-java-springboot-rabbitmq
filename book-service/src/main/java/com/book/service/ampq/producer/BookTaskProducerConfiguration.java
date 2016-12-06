package com.book.service.ampq.producer;

import com.book.service.ampq.MessageQueue;
import com.book.service.ampq.RabbitMqConfiguration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Producer of tasks that Google Books Service will consume.
 *
 * @author afernandez
 */
@Configuration
public class BookTaskProducerConfiguration extends RabbitMqConfiguration {

    @Autowired
    private ConnectionFactory cachingConnectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        template.setRoutingKey(MessageQueue.TASKS_QUEUE);
        template.setQueue(MessageQueue.TASKS_QUEUE);
        template.setMessageConverter(jsonMessageConverterProducer());
        return template;
    }

    @Bean
    public MessageConverter jsonMessageConverterProducer() {
        return new Jackson2JsonMessageConverter();
    }
}
