package net.skycloud.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class SpringbootKafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafkaDemoApplication.class, args);
    }

}
