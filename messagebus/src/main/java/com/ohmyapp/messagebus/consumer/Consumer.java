package com.ohmyapp.messagebus.consumer;

import com.ohmyapp.messagebus.api.ConsumerApi;
import com.ohmyapp.messagebus.api.MessageHandlerApi;
import com.ohmyapp.messagebus.api.SubscriberApi;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author by fedora on 11/24/15.
 */
public class Consumer implements ConsumerApi {
    private ExecutorService executorService;
    private Map<String, SubscriberApi> subscriberMap = new HashMap<>();

    public static ConsumerConnector createConnector() {
        return createConnector("localhost:2181", "default");
    }

    public static ConsumerConnector createConnector(String zookeeper, String groupId) {
        return kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig(zookeeper, groupId));
    }

    private static ConsumerConfig createConsumerConfig(String zookeeper, String groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeper);
        props.put("group.id", groupId);
        props.put("zookeeper.session.timeout.ms", "30000");
        props.put("zookeeper.sync.time.ms", "2000");
        props.put("auto.commit.enable", "false");
        return new ConsumerConfig(props);
    }

    @Override
    public void start() {
        if (executorService == null) {
            executorService = Executors.newSingleThreadExecutor();
            subscriberMap.clear();
        }
    }

    @Override
    public void subscribe(String topic, MessageHandlerApi messageHandler) {
        Subscriber subscriber = new Subscriber(createConnector());
        SubscriberCallable callable = new SubscriberCallable(subscriber, topic, messageHandler);
        executorService.submit(callable);
        subscriberMap.put(topic, subscriber);
    }

    @Override
    public void stop() {
        for (Map.Entry<String, SubscriberApi> entry : subscriberMap.entrySet()) {
            SubscriberApi subscriber = entry.getValue();
            subscriber.stop();
        }
        subscriberMap.clear();
        executorService.shutdown();
    }
}
