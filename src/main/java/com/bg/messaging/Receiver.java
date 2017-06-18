package com.bg.messaging;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class Receiver {

    private LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        messages.offer(message);
    }

    public String getLatest() throws InterruptedException {
        return messages.poll(1, TimeUnit.SECONDS);
    }

}