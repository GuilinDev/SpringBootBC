package com.guilin.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "guilin.queue", containerFactory = "queueListenerFactory")
    /**
     * 使用注解 @JmsListener(destination = "guilin.queue")，表示此方法监控了名为 guilin.queue 的队列。
     * 当队列 guilin.queue 中有消息发送时会触发此方法的执行，text 为消息内容
     */
    public void receiveQueue(String text) {
        System.out.println("Consumer queue msg : "+text);
    }

    @JmsListener(destination = "guilin.topic", containerFactory = "topicListenerFactory")
    public void receiveTopic(String text) {
        System.out.println("Consumer topic msg : "+text);
    }

}
