package com.ohmyapp.workflow.transaction.api;

import java.util.Map;

/**
 * author by lip on 12/29/2015.
 */
public interface TaskApi {
    void execute(String businessKey, Map<String, Object> parms);
}
