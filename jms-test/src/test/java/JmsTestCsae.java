import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/7 12:25
 */
public class JmsTestCsae {


    /**
     * 生产者
     *
     * @throws JMSException
     */
    @Test
    public void messaageProducter() throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建连接并开启
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建session  param1:是否开启手动事务提交，如果是false为自动提交， param2:接收者签收模式：AUTO_ACKNOWLEDGE(自动签收)，CLIENT_ACKNOWLEDGE(手动签收）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建消息目的地
        Destination destination = session.createQueue("helloWord-1");
        //创建生产者
        MessageProducer messageProducer = session.createProducer(destination);
        //发送消息
        TextMessage textMessage = session.createTextMessage("hello1");
        messageProducer.send(textMessage);

        //关闭资源
        messageProducer.close();
        session.close();
        connection.close();

    }

    @Test
    public void messaegCustomer() throws JMSException, IOException {

        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        //创建并启动连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地对象
        Destination destination = session.createQueue("helloWord-1");
        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);

        //获取消息
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;

                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //一直处于监视状态
        System.in.read();
        //7. 释放资源
        messageConsumer.close();
        session.close();
        connection.close();

    }


}
