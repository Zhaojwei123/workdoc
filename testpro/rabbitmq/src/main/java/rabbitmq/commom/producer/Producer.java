package rabbitmq.commom.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Created by ZhaoJiwei on 2018/4/27.
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUsername("boostadmin");
//        factory.setPassword("boostpwd");
//        factory.setHost("125.70.226.244");
//        factory.setPort(5672);

        factory.setUsername("guestboost");
        factory.setPassword("guestboost");
        factory.setHost("10.10.1.151");


        Connection conn = factory.newConnection();

        Channel channel = conn.createChannel();

        String exchangeName = "boostexchange";
        channel.exchangeDeclare(exchangeName, "direct", true);

        while (true) {
            int randomInt = 0;
            try {
                Random random = new Random();
                randomInt = random.nextInt(1000);
                Thread.sleep(randomInt+1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String routingKey;
            String json;
            if(randomInt<500){
                json = "over date";
                routingKey = "meterdatacollectovertest";
            }else {
                routingKey = "meterdatacollect";
                json = "test:{" +
                        "\"devAdrr\": \"20124123\"," +
                        "\"devData\": [{" +
                        "\"dataName\": \"p\"," +
                        "\"dataItem\": \"1\"," +
                        "\"dataValue\": {" +
                        "\"P\":\"" + randomInt + "\"" +
                        "}" +
                        "}]" +
                        "}";
            }
            byte[] messageBodyBytes = (json).getBytes();
            channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);
        }
    }

}
