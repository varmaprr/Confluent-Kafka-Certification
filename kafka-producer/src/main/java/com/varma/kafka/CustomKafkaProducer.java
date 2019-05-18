package com.varma.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.net.InetAddress;
import java.util.Properties;
import java.util.concurrent.Future;

public class CustomKafkaProducer {

    org.apache.kafka.clients.producer.KafkaProducer<String, String> producer;

    public CustomKafkaProducer() {
        super();
        this.producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(getProperties());
    }


    private Properties getProperties(){
        Properties config = new Properties();
        config.put("client.id", "sample-client-id");
        config.put("bootstrap.servers", "host1:9092,host2:9092");
        config.put("acks", "all");
        return config;
    }

    public boolean produce(String topic, String key, String value){
        final ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
        Future<RecordMetadata> future = producer.send(record);
        return true;
    }

}
