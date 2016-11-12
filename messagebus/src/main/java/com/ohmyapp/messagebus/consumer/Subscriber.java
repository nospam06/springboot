package com.ohmyapp.messagebus.consumer;

import com.ohmyapp.messagebus.api.MessageHandlerApi;
import com.ohmyapp.messagebus.api.SubscriberApi;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author by fedora on 11/24/15.
 */
public class Subscriber implements SubscriberApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);
    private ConsumerConnector connector;
    private volatile boolean run = true;

    public Subscriber(ConsumerConnector connector) {
        this.connector = connector;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public void subscribe(String topic, MessageHandlerApi messageHandler) {
        messageHandler.start();

        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = connector
                .createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        // now create an object to consume the messages
        //
        for (final KafkaStream stream : streams) {
            ConsumerIterator it = stream.iterator();
            while (run && it.hasNext()) {
                MessageAndMetadata<byte[], byte[]> next = it.next();
                byte[] key = next.key();
                byte[] message = next.message();
                LOGGER.debug("{} = {}", new String(key, StandardCharsets.UTF_8),
                        new String(message, StandardCharsets.UTF_8));

                messageHandler.handle(key, message);
                connector.commitOffsets(true);
            }
        }
        connector.shutdown();
        messageHandler.stop();
    }

    @Override
    public void stop() {
        run = false;
    }
}
