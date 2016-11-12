package com.ohmyapp.messagebus.api;

/**
 * author by lip on 1/2/2016.
 */
public interface MessageHandlerApi {
    /**
     * start message handler
     */
    void start();

    /**
     * deliver message to destination
     *
     * @param key     key
     * @param message message
     */
    void handle(byte[] key, byte[] message);

    /**
     * stop message handler
     */
    void stop();
}
