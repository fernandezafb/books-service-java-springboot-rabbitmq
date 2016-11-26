package com.book.service.ampq;

import com.book.service.ampq.consumer.BookTaskResultMessage;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(resultClassMapper());

        return converter;
    }

    @Bean
    public DefaultClassMapper resultClassMapper() {
        DefaultClassMapper mapper = new DefaultClassMapper();
        mapper.setDefaultType(BookTaskResultMessage.class);

        return mapper;
    }
}
