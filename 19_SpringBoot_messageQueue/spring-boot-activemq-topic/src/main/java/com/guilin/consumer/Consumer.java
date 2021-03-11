package com.guilin.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "guilin.topic")
    public void receiveTopic(String text) {
        System.out.println("Consumer topic msg : " + text);
    }

}
