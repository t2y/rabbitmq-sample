package org.example.rabbitmq.sample;

import com.rabbitmq.client.ConnectionFactory;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        var factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (var connection = factory.newConnection()) {
            var channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] Sent '" + message + "'");
        }
    }
}
