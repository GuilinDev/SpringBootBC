package com.guilin.rabbit.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "guilin")
public class GuilinReceiver2 {

    @RabbitHandler
    public void process(String guilin) {
        System.out.println("Receiver 2: " + guilin);
    }

}
