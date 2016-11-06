package com.ohmyapp.messagebus.producer;

import com.ohmyapp.messagebus.MessageBusBaseTest;
import com.ohmyapp.messagebus.api.PublisherApi;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.fail;

/**
 * author by lip on 1/2/2016.
 */
public class PublisherTest extends MessageBusBaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherTest.class);

    @Test
    public void testPublish() {
        try {
            PublisherApi<String, String> publisherApi = new Publisher<>();
            publisherApi.publish("test", "my key", "my message");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            fail();
        }
    }
}