package com.varma.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.net.InetAddress;
import java.util.Properties;
import java.util.concurrent.Future;

public class Producer {
    public static void main(String[] args) throws Exception {
        CustomKafkaProducer customKafkaProducer = new CustomKafkaProducer();
        customKafkaProducer.produce("sample-topic",null, "This is a sample message");
    }
}
