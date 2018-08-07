package com.libang.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author libang
 * @date 2018/8/7 19:33
 */
public class TopicListener implements MessageListener {


    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("=============>"+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
