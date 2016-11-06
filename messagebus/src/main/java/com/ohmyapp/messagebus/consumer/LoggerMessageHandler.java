package com.ohmyapp.messagebus.consumer;

import com.ohmyapp.messagebus.api.MessageHandlerApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * author by lip on 1/2/2016.
 */
public class LoggerMessageHandler implements MessageHandlerApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMessageHandler.class);

    @Override
    public void start() {
        // no startup needed
    }

    @Override
    public void handle(byte[] key, byte[] message) {
        LOGGER.info("key = " + new String(key, StandardCharsets.UTF_8));
        LOGGER.info("message = " + new String(message, StandardCharsets.UTF_8));
    }

    @Override
    public void stop() {
        // no stop needed
    }
}
