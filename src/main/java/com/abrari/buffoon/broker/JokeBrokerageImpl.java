package com.abrari.buffoon.broker;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class JokeBrokerageImpl implements JokeBrokerage {

    @Value("${fube.rabbitmq.jokes.queue}")
    private String QUEUE_NAME;

    @Value("${fube.rabbitmq.jokes.jokes-topic}")
    private String TOPIC_NAME;

    @Value("${fube.rabbitmq.jokes.exchange-name}")
    private String EXCHANGE_NAME;

    @Value("${fube.rabbitmq.jokes.routing.delete}")
    private String DELETE_ROUTING_KEY;

    @NonNull
    final private RabbitTemplate rabbitTemplate;


    @Override
    public void emitUserDeleted(UUID guid) {

        rabbitTemplate.convertAndSend(
            TOPIC_NAME,
            DELETE_ROUTING_KEY,
            guid.toString().getBytes(StandardCharsets.UTF_8)
        );
    }
}
