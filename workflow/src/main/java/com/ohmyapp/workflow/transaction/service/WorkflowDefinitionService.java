package com.ohmyapp.workflow.transaction.service;

import com.ohmyapp.workflow.WorkflowResource;
import com.ohmyapp.workflow.transaction.api.WorkflowApi;
import com.ohmyapp.workflow.transaction.dao.DaoWorkflowDefinition;
import com.ohmyapp.workflow.transaction.entity.EntityWorkflowDefinition;
import com.ohmyapp.workflow.utils.WorkflowUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * author by lip on 12/30/2015.
 */
@Transactional
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class WorkflowDefinitionService implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowDefinitionService.class);

    @Autowired
    DaoWorkflowDefinition daoWorkflowDefinition;

    @Autowired
    WorkflowApi workflowService;

    @Override
    public void run(String... args) throws Exception {
        loadResourceToDatabase();
    }

    private void loadResourceToDatabase() {
        for (WorkflowResource resource : WorkflowResource.values()) {
            try {
                String content = WorkflowUtils.loadClassPathResource(resource.getPath());
                if (content.isEmpty()) {
                    continue;
                }
                List<EntityWorkflowDefinition> workflowDefinitions = daoWorkflowDefinition.findByNameAndTenant(resource.name(), "System");
                if (workflowDefinitions.isEmpty()) {
                    EntityWorkflowDefinition definition = new EntityWorkflowDefinition();
                    definition.setTenant("System");
                    definition.setContent(content);
                    saveDefinition(resource, definition);
                    deployWorkflow(definition);
                } else {
                    EntityWorkflowDefinition definition = workflowDefinitions.get(0);
                    //if (!content.equals(definition.getContent())) {
                        definition.setContent(content);
                        saveDefinition(resource, definition);
                        deployWorkflow(definition);
                    //}
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

    private void deployWorkflow(EntityWorkflowDefinition definition) {
        workflowService.deployWorkflowDefinition(definition.getPath(), definition.getContent());
    }

    public void saveDefinition(WorkflowResource resource, EntityWorkflowDefinition definition) {
        definition.setName(resource.name());
        definition.setPath(resource.getPath());
        definition.setUpdateTime(Calendar.getInstance(TimeZone.getTimeZone("GMT-00")));
        daoWorkflowDefinition.save(definition);
    }
}
