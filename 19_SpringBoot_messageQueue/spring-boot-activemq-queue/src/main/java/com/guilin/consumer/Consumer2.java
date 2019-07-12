package com.guilin.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 {

    @JmsListener(destination = "guilin.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer2 queue msg : "+text);
    }
}