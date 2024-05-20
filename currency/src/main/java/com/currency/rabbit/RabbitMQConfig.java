package com.currency.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private static final String MY_QUEUE = "rates";

    @Bean
    public Queue myQueue() {
        return new Queue(MY_QUEUE);
    }
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("myExchange");
//    }
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), "myQueue", null);
//    }
    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(new SimpleMessageConverter());
        return rabbitTemplate;
    }
}
