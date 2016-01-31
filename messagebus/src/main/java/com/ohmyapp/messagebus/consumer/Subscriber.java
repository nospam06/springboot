package com.ohmyapp.messagebus.consumer;

import com.ohmyapp.messagebus.api.MessageHandlerApi;
import com.ohmyapp.messagebus.api.SubscriberApi;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author by fedora on 11/24/15.
 */
public class Subscriber implements SubscriberApi {
    private static final Logger LOGGER = Logger.getLogger(Subscriber.class);
    private ConsumerConnector _connector;
    private volatile boolean _run = true;

    public Subscriber(ConsumerConnector connector) {
        _connector = connector;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public void subscribe(String topic, MessageHandlerApi messageHandler) {
        messageHandler.start();

        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = _connector
                .createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        // now create an object to consume the messages
        //
        for (final KafkaStream stream : streams) {
            ConsumerIterator it = stream.iterator();
            while (_run && it.hasNext()) {
                MessageAndMetadata<byte[], byte[]> next = it.next();
                byte[] key = next.key();
                byte[] message = next.message();
                LOGGER.debug(new String(key, StandardCharsets.UTF_8)  + " = " + new String(message, StandardCharsets.UTF_8));

                messageHandler.handle(key, message);
                _connector.commitOffsets(true);
            }
        }
        _connector.shutdown();
        messageHandler.stop();
    }

    @Override
    public void stop() {
        _run = false;
    }
}
