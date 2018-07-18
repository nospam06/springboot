package com.ohmyapp.boot.api;

import java.util.List;

/**
 * Created by lip on 5/16/2017.
 * proxy
 */
public interface MyProxy {
    /**
     *
     * @param input     input
     * @param output    output
     */
    void doSomething(List<String> input, List<String> output);
}
