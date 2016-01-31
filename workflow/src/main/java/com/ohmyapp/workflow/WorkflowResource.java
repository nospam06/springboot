package com.ohmyapp.workflow;

/**
 * author by lip on 12/30/2015.
 */
public enum WorkflowResource {

    myProcess("/diagrams/MyProcess.bpmn");

    private String path;

    WorkflowResource(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
