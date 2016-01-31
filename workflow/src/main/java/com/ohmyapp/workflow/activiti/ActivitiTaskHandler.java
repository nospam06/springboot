package com.ohmyapp.workflow.activiti;

import com.ohmyapp.workflow.WorkflowContext;
import com.ohmyapp.workflow.transaction.api.TaskApi;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.context.ApplicationContext;

import java.util.Map;

// this is a singleton created by activiti
// therefore must be thread safe
public class ActivitiTaskHandler implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println(arg0.getCurrentActivityId());
		System.out.println(arg0.getCurrentActivityName());
		System.out.println(arg0.getProcessBusinessKey());
		System.out.println(arg0.getProcessInstanceId());
		System.out.println(arg0.getVariable("name"));
		System.out.println(this.getClass().getName());
		Map<String, Object> variables = arg0.getVariables();
		ApplicationContext context = WorkflowContext.getContext();
		TaskApi task = context.getBean(arg0.getCurrentActivityId(), TaskApi.class);
		task.execute(arg0.getProcessBusinessKey(), variables);
	}

}
