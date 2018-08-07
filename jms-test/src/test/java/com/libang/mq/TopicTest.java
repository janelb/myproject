package com.libang.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/7 20:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-topic.xml")
public class TopicTest {

    @Autowired
    JmsTemplate jmsTemplate;
    public void testSendMessage(){
        // destinationName方法只能向队列中添加消息
        // Destination destination = new ActiveMQTopic("spring-topic");
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage("spring-topic3");
            }
        });

    }

    @Test
    public void testRead() throws IOException {
        System.out.println("spring sart..");
        System.in.read();
    }
}
