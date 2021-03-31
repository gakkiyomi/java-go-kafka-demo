package main

import (
	"fmt"

	"github.com/gakkiyomi/kafka-go-demo/api/routers"
	"github.com/gakkiyomi/kafka-go-demo/config"
	"gopkg.in/confluentinc/confluent-kafka-go.v1/kafka"
)

func main() {

	c, err := kafka.NewConsumer(config.ConfigMap)
	if err != nil {
		panic(err)
	}

	p, err := kafka.NewProducer(config.ConfigMap)
	if err != nil {
		panic(err)
	}
	config.Pd = p
	c.SubscribeTopics([]string{"topic1", "topic2"}, nil)

	go func() {
		for {
			msg, err := c.ReadMessage(-1)
			if err == nil {
				fmt.Printf("go消费者消费topic1中的消息 %s: %s\n", msg.TopicPartition, string(msg.Value))
			} else {
				// The client will automatically try to recover from all errors.
				fmt.Printf("Consumer error: %v (%v)\n", err, msg)
			}
		}
	}()

	routers.SetupRouter("0.0.0.0").Run("0.0.0.0:8081")
}
