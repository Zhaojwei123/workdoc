package rabbitmq.commom.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeoutException;
import java.util.logging.SimpleFormatter;

/**
 * Created by ZhaoJiwei on 2018/4/27.
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        final Log_Exception log_exception = new Log_Exception();
        factory.setUsername("guestboost");
        factory.setPassword("guestboost");
        factory.setHost("10.10.1.151");
        factory.setPort(5672);
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        final Channel channel = conn.createChannel();
        //声明交换器
        String exchangeName = "boostexchange";
        channel.exchangeDeclare(exchangeName, "direct", true);
        //声明队列
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("queueName:"+queueName);
        String routingKey = "meterdatacollect";
        //绑定队列，通过键 meterdatacollect 将队列和交换器绑定起来
        channel.queueBind(queueName, exchangeName, routingKey);

        while(true) {
            //消费消息
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    String bodyStr = new String(body, "UTF-8");
                    System.out.println(bodyStr);
                    log_exception.writeEror_to_txt("D:/shoudiandataLog.txt",bodyStr);
                }
            });
        }
    }

}
