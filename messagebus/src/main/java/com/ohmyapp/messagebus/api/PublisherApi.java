package com.ohmyapp.messagebus.api;

/**
 * author by lip on 1/2/2016.
 */
public interface PublisherApi<K, V> {
    /**
     * @param topic   message topic
     * @param key     key
     * @param message message
     */
    void publish(String topic, K key, V message);

}