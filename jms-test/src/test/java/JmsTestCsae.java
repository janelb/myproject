import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
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
    public void messaageProducter() throws Exception {
        Connection connection = null;
        MessageProducer messageProducer = null;
        Session session = null;
        try {
            //创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            //创建连接并开启
            connection = connectionFactory.createConnection();
            connection.start();
            //创建session  param1:是否开启手动事务提交，如果是false为自动提交， param2:接收者签收模式：AUTO_ACKNOWLEDGE(自动签收)，CLIENT_ACKNOWLEDGE(手动签收）
            session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);//手动进行签收
            //创建消息目的地
            Destination destination = session.createQueue("helloWord-1");
            //创建生产者
            messageProducer = session.createProducer(destination);
            //设置持久模式（没有被消费的消息，即使系统重新启动也不会消失）
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //发送消息
            TextMessage textMessage = session.createTextMessage("hello1");
            messageProducer.send(textMessage);

            //进行手动提交事务
            session.commit();

        } catch (JMSException e) {

        } finally {
            //关闭资源
            messageProducer.close();
            session.close();
            connection.close();

        }

    }


    @Test
    public void messaegCustomer() throws JMSException, IOException {

        //创建连接工厂
   /*     ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);*/

        //自定义重置参数
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //设置重置次数
        redeliveryPolicy.setMaximumRedeliveries(3);
        //设置初次重置延迟时间，单位毫秒
        redeliveryPolicy.setInitialRedeliveryDelay(2000);
        //设置每次重置延迟时间，单位毫秒
        redeliveryPolicy.setRedeliveryDelay(2000);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);


        //创建并启动连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建session
        final Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);//手动签收消息
        //创建目的地对象
        Destination destination = session.createQueue("helloWord-1");
        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);

        //获取消息
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {

                try {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage.getText());

                    //为使报错添加条件,测试重试机制
                    if("hello1".equals(textMessage.getText())){
                        throw new JMSException("error");
                    }

                    //手动签收消息
                    textMessage.acknowledge();

                } catch (JMSException e) {

                    e.printStackTrace();
                    //重新接受消息
                    try {
                        session.recover();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
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
