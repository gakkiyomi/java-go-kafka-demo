package controllers

import (
	"fmt"
	"net/http"

	"github.com/gakkiyomi/kafka-go-demo/config"
	"github.com/gin-gonic/gin"
	"gopkg.in/confluentinc/confluent-kafka-go.v1/kafka"
)

func Send(c *gin.Context) {
	topic := c.DefaultQuery("topic", "df")
	fmt.Println(topic)
	message := c.DefaultQuery("message", "")
	fmt.Println(message)

	// 轮询检测消息投递情况
	go func() {
		for e := range config.Pd.Events() {
			switch ev := e.(type) {
			case *kafka.Message:
				if ev.TopicPartition.Error != nil {
					fmt.Printf("Delivery failed: %v\n", ev.TopicPartition)
				} else {
					fmt.Printf("Delivered message to %v\n", ev.TopicPartition)
				}
			}
		}
	}()

	// Produce messages to topic (asynchronously)
	for _, word := range []string{"Welcome", "to", "the", "kafka", "demo", "by", "golang and java", message} { //message为自定义消息
		config.Pd.Produce(&kafka.Message{
			TopicPartition: kafka.TopicPartition{Topic: &topic, Partition: kafka.PartitionAny},
			Value:          []byte(word),
		}, nil)
	}

	// Wait for message deliveries before shutting down
	config.Pd.Flush(15 * 1000)

	c.String(http.StatusOK, "消息发送中")
}
