package com.ohmyapp.messagebus.consumer;

import com.ohmyapp.messagebus.api.MessageHandlerApi;
import org.junit.Test;

/**
 * author by lip on 1/2/2016.
 */
public class ConsumerTest {
    @Test
    public void testConsumer() {
        Consumer consumer = new Consumer();
        MessageHandlerApi messageHandler = new LoggerMessageHandler();
        consumer.start();
        consumer.subscribe("test", messageHandler);
        try {
            Thread.sleep(30000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        consumer.stop();
    }

}