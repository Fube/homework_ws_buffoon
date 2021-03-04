package com.abrari.buffoon.config;

import lombok.val;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig{

    @Value("${fube.rabbitmq.jokes.queue}")
    private String QUEUE_NAME;

    @Value("${fube.rabbitmq.jokes.jokes-topic}")
    private String TOPIC_NAME;

    @Value("${fube.rabbitmq.jokes.exchange-name}")
    private String EXCHANGE_NAME;

    @Bean
    public Declarables topicBindings() {

         val topicQueue = new Queue(QUEUE_NAME, false);
         val topicExchange = new TopicExchange(EXCHANGE_NAME);

        return new Declarables(
                topicQueue,
                topicExchange,
                BindingBuilder.bind(topicQueue).to(topicExchange).with(TOPIC_NAME)
        );
    }
}
