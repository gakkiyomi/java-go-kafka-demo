package net.skycloud.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: fangcong
 * @description:
 * @create: Created by work on 2021-03-31 15:56
 **/
@Component
public class Consumer {

    @KafkaListener(topics = "topic1")//监听topic1
    public void receiveTopic1(String message) {
        System.out.println("java消费者消费topic1中的消息 : " + message);
    }

    @KafkaListener(topics = "topic2")//监听topic2
    public void receiveTopic2(String message) {
        System.out.println("java消费者消费topic2中的消息: " + message);
    }

    @KafkaListener(topics = "topic3")//监听topic2
    public void receiveTopic3(String message) {
        System.out.println("java消费者消费topic3中的消息: " + message);
    }
}
