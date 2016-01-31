package com.ohmyapp.workflow.transaction;

import com.ohmyapp.workflow.Application;
import com.ohmyapp.workflow.WorkflowContext;
import com.ohmyapp.workflow.transaction.api.WorkflowApi;
import com.ohmyapp.workflow.transaction.service.ActivitiWorkflowService;
import com.ohmyapp.workflow.transaction.service.WorkflowDefinitionService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.fail;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(Application.class)
public class ProcessTestMyProcess {
    private static final Logger LOGGER = Logger.getLogger(ProcessTestMyProcess.class);

    //@Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    @Test
    public void deployProcess() {
        String filename = "diagrams/MyProcess.bpmn";
        WorkflowApi workflowApi = Application.getBean(WorkflowApi.class);
        String id = workflowApi.deployWorkflowDefinition(filename);
        Assert.assertNotNull(id);
        LOGGER.info("deployment id " + id);

    }

    @Test
    public void deployByString() {
        ProcessEngine engine = buildProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        String filename = "src/main/resources/diagrams/MyProcess.bpmn";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            line = sb.toString();
        } catch (IOException e) {
            LOGGER.error(e);
            fail();
        }
        Deployment deployment = repositoryService.createDeployment().addString("diagrams/MyProcess.bpmn", line).deploy();
        Assert.assertNotNull(deployment);
        LOGGER.info("deployment id " + deployment.getId());
    }

    private ProcessEngine buildProcessEngine() {
        /*StandaloneProcessEngineConfiguration config = new StandaloneProcessEngineConfiguration();

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
		dataSource.setUrl("jdbc:mysql://localhost:3306/activiti");
		dataSource.setUsername("activiti");
		dataSource.setPassword("activiti");
		dataSource.setDefaultAutoCommit(false);
		config.setDataSource(dataSource);
		ProcessEngine engine = config.buildProcessEngine();
		*/
        processEngineConfiguration = Application.getBean(ProcessEngineConfiguration.class);
        return processEngineConfiguration.buildProcessEngine();
    }

    @Test
    public void startProcess() throws Exception {
        ApplicationContext context = Application.getContext();
        WorkflowContext.setContext(context);
        WorkflowApi workflowService = context.getBean(WorkflowApi.class);
        String businessKey = new SimpleDateFormat("yyyyMMdd.hhmmss.sss").format(System.currentTimeMillis());
        LOGGER.info("Business Key = " + businessKey);

        String id = workflowService.startWorkflow("myProcess", businessKey);
        Assert.assertNotNull(id);
        LOGGER.info("id " + id);
        //
/*
		// send event
		Execution execution = runtimeService.createExecutionQuery()
				.processInstanceBusinessKey(businessKey).singleResult();
		execution = runtimeService.createExecutionQuery()
				.processInstanceId(execution.getProcessInstanceId())
				.activityId("receivetask1").singleResult();
		System.out.println("execution id " + execution.getId());
		runtimeService.signal(execution.getId());
		//execution = runtimeService.createExecutionQuery().processInstanceId(id)
		//		.activityId("receivetask2").singleResult();
		//LOGGER.info("execution id " + execution.getId());
		//runtimeService.signal(execution.getId());
*/
    }

    @Test
    public void finishProcess() throws Exception {
        ProcessEngine engine = buildProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        String businessKey = "1234567";
        Execution execution = runtimeService.createExecutionQuery()
                .processInstanceBusinessKey(businessKey).singleResult();
        execution = runtimeService.createExecutionQuery()
                .processInstanceId(execution.getProcessInstanceId())
                .activityId("receivetask3").singleResult();
        LOGGER.info("execution id " + execution.getId());
        runtimeService.signal(execution.getId());
    }

    @Test
    public void finishProcessByProcessId() throws Exception {
        ProcessEngine engine = buildProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        String businessKey = "125005";
        Execution execution = runtimeService.createExecutionQuery()
                .processInstanceId(businessKey)
                .activityId("receivetask3").singleResult();
        LOGGER.info("execution id " + execution.getId());
        runtimeService.signal(execution.getId());
    }
}