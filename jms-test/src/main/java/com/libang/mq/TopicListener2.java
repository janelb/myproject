package com.libang.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author libang
 * @date 2018/8/7 19:33
 */
@Component
public class TopicListener2 {

    @JmsListener(destination = "spring-topic")
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("=============>"+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
