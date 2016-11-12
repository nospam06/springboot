package com.ohmyapp.messagebus.consumer;

import com.ohmyapp.messagebus.api.MessageHandlerApi;
import com.ohmyapp.messagebus.api.SubscriberApi;

import java.util.concurrent.Callable;

/**
 * author by lip on 1/2/2016.
 */
public class SubscriberCallable implements Callable<Boolean> {
    private SubscriberApi subscriber;
    private String topic;
    private MessageHandlerApi messageHandlerApi;

    /**
     * constructor
     * @param subscriber        subscriber
     * @param topic             topic
     * @param messageHandler    messageHandler
     */
    public SubscriberCallable(SubscriberApi subscriber, String topic, MessageHandlerApi messageHandler) {
        this.subscriber = subscriber;
        this.topic = topic;
        messageHandlerApi = messageHandler;
    }

    @Override
    public Boolean call() throws Exception {
        subscriber.subscribe(topic, messageHandlerApi);
        return true;
    }

}
