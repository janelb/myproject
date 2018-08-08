package com.libang.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @author libang
 * @date 2018/8/7 19:33
 */
@Component
public class ConsumterListener2 implements SessionAwareMessageListener {

    @JmsListener(destination = "consumer-queue")
    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("------------------>"+textMessage.getText());
            /*进行手动签收*/
            textMessage.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();

            /*设置重试机制*/
            session.recover();

        }

    }
}
