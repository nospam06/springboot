package com.ohmyapp.messagebus.producer;

import com.ohmyapp.messagebus.api.PublisherApi;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * author by lip on 1/2/2016.
 */
public class PublisherTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPublish() {
        PublisherApi<String, String> publisherApi = new Publisher<>();
        publisherApi.publish("test", "my key", "my message");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}