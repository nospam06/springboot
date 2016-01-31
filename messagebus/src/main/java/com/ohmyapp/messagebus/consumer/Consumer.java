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
    private ExecutorService _executorService;
    private Map<String, SubscriberApi> _subscriberMap = new HashMap<>();

    public static ConsumerConnector createConnector() {
        return createConnector("localhost:2181", "default");
    }

    public static ConsumerConnector createConnector(String a_zookeeper, String a_groupId) {
        return kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig(a_zookeeper, a_groupId));
    }

    private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", a_zookeeper);
        props.put("group.id", a_groupId);
        props.put("zookeeper.session.timeout.ms", "10000");
        props.put("zookeeper.sync.time.ms", "2000");
        props.put("auto.commit.enable", "false");
        return new ConsumerConfig(props);
    }

    @Override
    public void start() {
        if (_executorService == null) {
            _executorService = Executors.newSingleThreadExecutor();
            _subscriberMap.clear();
        }
    }

    @Override
    public void subscribe(String topic, MessageHandlerApi messageHandler) {
        Subscriber subscriber = new Subscriber(createConnector());
        SubscriberCallable callable = new SubscriberCallable(subscriber, topic, messageHandler);
        _executorService.submit(callable);
        _subscriberMap.put(topic, subscriber);
    }

    @Override
    public void stop() {
        for (Map.Entry<String, SubscriberApi> entry : _subscriberMap.entrySet()) {
            SubscriberApi subscriber = entry.getValue();
            subscriber.stop();
        }
        _subscriberMap.clear();
        _executorService.shutdown();
    }
}
