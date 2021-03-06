package net.skycloud.kafka.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author: fangcong
 * @description:
 * @create: Created by work on 2021-03-31 15:48
 **/
@RestController
@RequestMapping("/producer")
@CrossOrigin(origins = "*")
public class ProducerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/send")
    public String send(@RequestParam String topic, @RequestParam String message) {
        String[] messages = new String[]{"Welcome", "to", "the", "kafka", "demo", "by", "java and golang", message};
        for (String s : messages) {
            final ListenableFuture send = kafkaTemplate.send(topic, s);
            send.addCallback((res) -> System.out.println("成功了")
                    , (fail) -> System.out.println("失败了")
            );
        }
        return "消息发送中";

    }
}
