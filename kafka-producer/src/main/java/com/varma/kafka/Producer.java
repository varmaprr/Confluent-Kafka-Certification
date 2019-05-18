package com.varma.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {

    private static Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("Running kafka producer...");
        CustomKafkaProducer customKafkaProducer = new CustomKafkaProducer();
        customKafkaProducer.produce("sample-topic","1", "This is a sample message");
        LOGGER.info("Running kafka producer completed!! ");
    }
}
