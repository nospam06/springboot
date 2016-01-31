package com.ohmyapp.messagebus.consumer;

import com.ohmyapp.messagebus.api.MessageHandlerApi;
import com.ohmyapp.messagebus.api.SubscriberApi;

import java.util.concurrent.Callable;

/**
 * author by lip on 1/2/2016.
 */
public class SubscriberCallable implements Callable<Boolean> {
    private SubscriberApi _subscriber;
    private String _topic;
    private MessageHandlerApi _messageHandler;

    public SubscriberCallable(SubscriberApi subscriber, String topic, MessageHandlerApi messageHandler) {
        _subscriber = subscriber;
        _topic = topic;
        _messageHandler = messageHandler;
    }

    @Override
    public Boolean call() throws Exception {
        _subscriber.subscribe(_topic, _messageHandler);
        return true;
    }

}
