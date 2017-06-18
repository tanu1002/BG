package com.bg.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bg.messaging.Listener.QUEUE_NAME;


@Component
public class Sender {

	@Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        this.rabbitTemplate.convertAndSend(QUEUE_NAME, message);
    }

}