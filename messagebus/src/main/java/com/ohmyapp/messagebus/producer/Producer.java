package com.ohmyapp.messagebus.producer;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.HashMap;
import java.util.Map;

/**
 * author by lip on 1/1/2016.
 */
public class Producer {
    public static <K, V> KafkaProducer<K, V> createProducer(K keyClass, V valueClass, Map<String, Object> config) {
        return new KafkaProducer<>(config);
    }

    public static <K, V> KafkaProducer<K, V> createProducer(K keyClass, V valueClass) {
        return createProducer(keyClass, valueClass, createProducerConfig());
    }

    private static Map<String, Object> createProducerConfig() {
        Map<String, Object> map = new HashMap<>();
        map.put("bootstrap.servers", "localhost:9092");
        map.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        map.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return map;
    }
}
