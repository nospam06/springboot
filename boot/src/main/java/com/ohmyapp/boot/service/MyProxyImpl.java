package com.ohmyapp.boot.service;

import com.ohmyapp.boot.api.MyProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lip on 5/16/2017.
 * proxy impl
 */
@Component("proxy1")
public class MyProxyImpl implements MyProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyProxyImpl.class);

    @Override
    public void doSomething(List<String> input, List<String> output) {
        LOGGER.debug("inside target");
        LOGGER.debug("input {}", input);
        if (!output.isEmpty()) {
            throw new RuntimeException();
        }
        output.add("inside target");
        LOGGER.debug("output {}", output);
    }
}
