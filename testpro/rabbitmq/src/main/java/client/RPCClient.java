package client;

import com.rabbitmq.client.*;

/**
 * Created by ZhaoJiwei on 2018/5/24.
 */
public class RPCClient {

    private static final String RPC_QUEUE_NAME = "rpc_queue_zjw";

    private Connection connection;
    private Channel channel;
    private String replyQueueName;
    private QueueingConsumer consumer;

    public RPCClient() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        // 设置MabbitMQ所在主机ip或者主机名
        factory.setHost("localhost");
        // 创建一个连接
        connection = factory.newConnection();
        // 创建一个频道
        channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

        //为每一个客户端获取一个随机的回调队列
        replyQueueName = channel.queueDeclare().getQueue();
        //为每一个客户端创建一个消费者（用于监听回调队列，获取结果）
        consumer = new QueueingConsumer(channel);
        //消费者与队列关联
        channel.basicConsume(replyQueueName, true, consumer);
    }

    /**
     * 获取斐波列其数列的值
     *
     * @param message
     * @return
     * @throws Exception
     */
    public String call(String message) throws Exception{
        String response = null;
        String corrId = java.util.UUID.randomUUID().toString();

        //设置replyTo和correlationId属性值
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();

        //发送消息到rpc_queue队列
        channel.basicPublish("", RPC_QUEUE_NAME, props, message.getBytes());

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response = new String(delivery.getBody(),"UTF-8");
                break;
            }
        }

        return response;
    }

    public static void main(String[] args) throws Exception {
        RPCClient fibonacciRpc = new RPCClient();
        String result = fibonacciRpc.call("4");
        System.out.println( "fib(4) is " + result);
    }
}