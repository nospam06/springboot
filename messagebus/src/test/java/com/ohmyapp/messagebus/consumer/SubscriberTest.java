package com.ohmyapp.messagebus.consumer;

import com.ohmyapp.messagebus.api.MessageHandlerApi;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author by lip on 1/2/2016.
 */
public class SubscriberTest {

    @Test
    public void testSubscribe() {
        Subscriber subscriber = new Subscriber(Consumer.createConnector());
        MessageHandlerApi messageHandler = new LoggerMessageHandler();
        SubscriberCallable callable = new SubscriberCallable(subscriber, "test", messageHandler);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            executorService.submit(callable);
            Thread.sleep(30000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        subscriber.stop();
        executorService.shutdown();
    }
}