package com.ohmyapp.workflow.activiti;

import com.ohmyapp.workflow.Application;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.transaction.annotation.Transactional;

// this is a singleton created by activiti
// therefore must be thread safe
@Transactional
public class Task1 implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println(arg0.getCurrentActivityId());
		System.out.println(arg0.getCurrentActivityName());
		System.out.println(arg0.getProcessBusinessKey());
		System.out.println(arg0.getProcessInstanceId());
		System.out.println(arg0.getVariable("name"));
		System.out.println(this.getClass().getName());
	}

}
