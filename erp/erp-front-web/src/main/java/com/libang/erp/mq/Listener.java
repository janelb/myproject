package com.libang.erp.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author libang
 * @date 2018/8/8 14:20
 */
public class Listener {

    @Autowired
    private JmsTemplate jmsTemplate;



}
