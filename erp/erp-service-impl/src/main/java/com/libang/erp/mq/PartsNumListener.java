package com.libang.erp.mq;

import com.libang.erp.service.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author libang
 * @date 2018/8/9 19:31
 */
public class PartsNumListener implements SessionAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(PartsNumListener.class);

    @Autowired
    private PartService partService;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        try {
            String json = textMessage.getText();
            logger.info("接受队列json数据{}",json);
            partService.subInventory(json);
            textMessage.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
            session.recover();
        }

    }
}
