import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/7 17:51
 */
public class TopicTestCase {

    @Test
    public void topicProducerTest() throws JMSException {
        //1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //2. 创建连接 并 开启
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3. 创建Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4. 创建Topic对象(此处于点对点不同)
        Topic topic = session.createTopic("topic1");

        //5. 创建生产者
        MessageProducer producer = session.createProducer(topic);
        //6. 发送消息
        TextMessage textMessage = session.createTextMessage("Hello,Topic-->1");
        producer.send(textMessage);
        //7. 释放资源
        producer.close();
        session.close();
        connection.close();

    }

    @Test
    public void topicCustomerTest() throws JMSException, IOException {
        //1. 创建连接工厂
        ConnectionFactory connectionFactory = new
                ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        //2. 创建并启动连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3. 创建Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4. 创建Topic对象(此处于点对点不同)
        Topic topic = session.createTopic("topic1");

        //5. 创建消费这
        MessageConsumer consumer = session.createConsumer(topic);

        //6.获取信息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                        e.printStackTrace();
                }
            }
        });

        System.in.read();

        //7. 释放资源
        consumer.close();
        session.close();
        connection.close();


    }


}
