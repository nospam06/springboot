package com.ohmyapp.workflow.transaction.dao;

import com.ohmyapp.workflow.transaction.entity.EntityWorkflowDefinition;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * author by lip on 12/30/2015.
 */
public interface DaoWorkflowDefinition extends PagingAndSortingRepository<EntityWorkflowDefinition, Long> {
    List<EntityWorkflowDefinition> findByNameAndTenant(String name, String tenant);
}
