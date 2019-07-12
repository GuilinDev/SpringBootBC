package com.guilin.send;

import com.guilin.producer.Producer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleActiveMqTests {

    @Rule
    /**
     * OutputCapture 是 Spring Boot 提供的一个测试类，它能捕获 System.out 和 System.err 的输出
     * 可以利用这个特性来判断程序中的输出是否执行
     */
    public OutputCapture outputCapture = new OutputCapture();

    @Autowired
    //注入创建好的消息生产者
    private Producer producer;

    @Test
    public void sendSimpleQueueMessage() throws InterruptedException {
        for (int i = 0;i < 100; i++){
            this.producer.sendQueue("Test queue message"+i);
        }
        Thread.sleep(1000L);
//		assertThat(this.outputCapture.toString().contains("Test message")).isTrue();
    }

    @Test
    public void sendSimpleTopicMessage() throws InterruptedException {
        for (int i = 0;i < 100; i++){
            this.producer.sendTopic("Test Topic message"+i);
        }
        Thread.sleep(1000L);
    }
}
