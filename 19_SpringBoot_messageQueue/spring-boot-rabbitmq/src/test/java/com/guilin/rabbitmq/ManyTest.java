package com.guilin.rabbitmq;

import com.guilin.rabbit.many.GuilinSender;
import com.guilin.rabbit.many.GuilinSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {
    @Autowired
    private GuilinSender guilinSender;

    @Autowired
    private GuilinSender2 guilinSender2;

    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            guilinSender.send(i);
        }
        Thread.sleep(10000l);
    }

    @Test
    public void manyToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            guilinSender.send(i);
            guilinSender2.send(i);
        }
        Thread.sleep(10000l);
    }

}
