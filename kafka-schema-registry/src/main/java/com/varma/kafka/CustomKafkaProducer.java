package com.varma.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

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
        config.put("bootstrap.servers", "13.233.142.162:9092,13.233.142.162:29092");
        config.put("acks", "all");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return config;
    }

    public boolean produce(String topic, String key, String value){
        final ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
        Future<RecordMetadata> future = producer.send(record);
        return true;
    }

}
