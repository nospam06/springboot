package com.ohmyapp.workflow.transaction.service;

import com.ohmyapp.workflow.transaction.api.WorkflowApi;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * author by lip on 12/22/2015.
 */
@Service
@Transactional
public class ActivitiWorkflowService implements WorkflowApi {
    @Autowired
    private ProcessEngineConfiguration _processEngineConfiguration;

    private ProcessEngine _engine;

    private static final Logger LOGGER = Logger.getLogger(ActivitiWorkflowService.class);

    public ActivitiWorkflowService() {
        // default constructor
    }

    public ActivitiWorkflowService(ProcessEngineConfiguration processEngineConfiguration) {
        this._processEngineConfiguration = processEngineConfiguration;
    }

    @PostConstruct
    public void buildProcessEngine() {
        _engine = _processEngineConfiguration.buildProcessEngine();
    }

    @Override
    public String deployWorkflowDefinition(String classpathResource) {
        RepositoryService repositoryService = _engine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment().addClasspathResource(classpathResource).deploy();

        LOGGER.debug("deployment id " + deployment.getId());
        return deployment.getId();
    }

    @Override
    public String deployWorkflowDefinition(String processName, String bpmnString) {
        RepositoryService repositoryService = _engine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment().addString(processName, bpmnString).deploy();

        LOGGER.debug("deployment id " + deployment.getId());
        return deployment.getId();
    }

    @Override
    public String startWorkflow(String processName, String businessKey) {
        RuntimeService runtimeService = _engine.getRuntimeService();
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("name", "Activiti");
        LOGGER.debug("Business Key = " + businessKey);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName, businessKey,
                variableMap);
        String id = processInstance.getId();
        LOGGER.debug("id " + id + " " + processInstance.getProcessDefinitionId());
        return id;
    }
}
