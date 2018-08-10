package com.libang.erp.mq;

import com.libang.erp.service.OrderService;
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
 * @date 2018/8/9 17:44
 */
public class OrderStateListener implements SessionAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(OrderStateListener.class);

    @Autowired
    private OrderService orderService;


    @Override
    public void onMessage(Message message, Session session) throws JMSException {

        TextMessage textMessage = (TextMessage) message;
        //从队列中获取json对象
        try {
            String json = textMessage.getText();
            logger.info("接收的队列json数据:{}", json);
            //修该前台订单的状态
            orderService.editState(json);

            //手动签收
            textMessage.acknowledge();

        } catch (JMSException e) {
            e.printStackTrace();
            //设置重试机制
            session.recover();
        }


    }
}
