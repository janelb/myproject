package com.libang.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author libang
 * @date 2018/8/7 19:33
 */
@Component
public class TopicListenerQueue {


    @JmsListener(destination = "spring-queue",containerFactory ="jmsQueueListenerContainerFactory" )
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("=============>"+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
