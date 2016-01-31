package com.ohmyapp.workflow;

import org.springframework.context.ApplicationContext;

/**
 * author by lip on 12/29/2015.
 */
public class WorkflowContext {
    private static ApplicationContext _context;

    private WorkflowContext() {
        // static access only
    }

    public static ApplicationContext getContext() {
        return _context;
    }

    public static void setContext(ApplicationContext context) {
        _context = context;
    }
}
