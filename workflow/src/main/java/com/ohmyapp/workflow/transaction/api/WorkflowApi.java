package com.ohmyapp.workflow.transaction.api;

/**
 * author by lip on 12/29/2015.
 */
public interface WorkflowApi {
    String deployWorkflowDefinition(String classpathResource);

    String deployWorkflowDefinition(String processName, String bpmnString);

    String startWorkflow(String processName, String businessKey);
}
