package config

import "gopkg.in/confluentinc/confluent-kafka-go.v1/kafka"

var ConfigMap = &kafka.ConfigMap{
	"bootstrap.servers": "192.168.1.222",
	"group.id":          "skyCloud2",
	"auto.offset.reset": "earliest",
}

var Pd *kafka.Producer
