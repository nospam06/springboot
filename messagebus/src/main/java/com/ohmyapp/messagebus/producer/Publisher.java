package com.ohmyapp.messagebus.producer;

import com.ohmyapp.messagebus.api.PublisherApi;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;

/**
 * author by lip on 1/1/2016.
 */
public class Publisher<K, V> implements PublisherApi<K,V> {
    private static final Logger LOGGER = Logger.getLogger(Publisher.class);
    private KafkaProducer<K, V> _producer;

    @PostConstruct
    public void initProducer(K key, V value) {
        _producer = Producer.createProducer(key, value);
    }

    @Override
    public void publish(final String topic, final K key, final V message) {
        if (_producer == null) {
            initProducer(key, message);
        }
        _producer.send(new ProducerRecord<>(topic, key, message), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    LOGGER.debug("message " + message + " published to topic " + topic);
                } else {
                    LOGGER.error("message " + message + " cannot be published to topic " + topic, exception);
                }
            }
        });
    }
}
