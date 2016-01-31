package com.ohmyapp.workflow.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
// this is a singleton created by activiti
// therefore must be thread safe
public class Task2 implements JavaDelegate {

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
