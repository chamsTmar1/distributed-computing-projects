package com.insat.gl3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.time.LocalDateTime;

public class Sender {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            channel.queueDeclare("hello-world", false, false, false, null);

            String message = "is this the matrix?" + LocalDateTime.now();

            channel.basicPublish("", "hello-world", false, null, message.getBytes());

            System.out.println(("!!! message has been sent"));
        } catch (Exception e) {
        }
    }
}
