package com.ohmyapp.messagebus.api;

/**
 * author by lip on 1/2/2016.
 */
public interface ConsumerApi {
    /**
     * start consumer
     */
    void start();

    /**
     * subscribe to message topic
     *
     * @param topic          message topic
     * @param messageHandler message handler
     */
    void subscribe(String topic, MessageHandlerApi messageHandler);

    /**
     * stop consumer
     */
    void stop();
}
