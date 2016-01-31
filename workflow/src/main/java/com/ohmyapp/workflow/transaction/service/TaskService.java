package com.ohmyapp.workflow.transaction.service;

import com.ohmyapp.workflow.transaction.api.TaskApi;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * author by lip on 12/29/2015.
 */
@Service(value = "defaultTask")
public class TaskService implements TaskApi {
    @Override
    public void execute(String businessKey, Map<String, Object> parms) {

    }
}
