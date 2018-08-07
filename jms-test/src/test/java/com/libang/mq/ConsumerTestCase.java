package com.libang.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/7 19:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-consumer.xml")
public class ConsumerTestCase {

    @Autowired
    JmsTemplate jmsTemplate;

    @Test
    public void testSendMessage() throws InterruptedException {
        /*可以自己设置内容，否者就为默认值*/
        Destination destination = new ActiveMQQueue("consumer-queue");
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("spring-mq"+System.currentTimeMillis());
            }
        });
        Thread.sleep(1000);
    }


    @Test
    public void testReadMessage() throws IOException {
        System.out.println("spring start....");
        System.in.read();
    }


}
