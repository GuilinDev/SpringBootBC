package com.guilin.rabbit.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuilinSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "spirng boot guilin queue" + " ****** " + i;
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("guilin", context);
    }

}
