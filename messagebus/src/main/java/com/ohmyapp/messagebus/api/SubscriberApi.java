package com.ohmyapp.messagebus.api;

/**
 * author by lip on 1/2/2016.
 */
public interface SubscriberApi {
    /**
     * subscribe to message topic
     *
     * @param topic message topic
     */
    void subscribe(String topic, MessageHandlerApi handler);

    /**
     * stop subscriber
     */
    void stop();
}
