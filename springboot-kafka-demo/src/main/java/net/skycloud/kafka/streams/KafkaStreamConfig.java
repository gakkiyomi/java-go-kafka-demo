package net.skycloud.kafka.streams;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: fangcong
 * @description:
 * @create: Created by work on 2021-04-01 11:19
 **/
@Configuration
public class KafkaStreamConfig {

    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder){ //2
        KStream<String, String> stream = streamsBuilder.stream("topic1");//从topic1中获取流
        stream.map((key, value) -> {
            value = value.toUpperCase();  //将value转成大写
            return new KeyValue<>(key,value);
        }).to("topic2"); //发送给topic2
        return stream;
    }

    @Bean
    public KStream<String, String> kStream2(StreamsBuilder streamsBuilder){ //2
        KStream<String, String> stream = streamsBuilder.stream("topic2");//从topic1中获取流
        stream.map((key, value) -> {
            value = value.toLowerCase()+" fangcong";  //将value转成小写并尾加fangcong字符串
            return new KeyValue<>(key,value);
        }).to("topic3"); //发送给topic2
        return stream;
    }
}
