package com.example.last.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.Project;

/**
 * repo con especificaciones
 */
public interface ProjectRepo extends
        PagingAndSortingRepository<Project, Long>,
            JpaSpecificationExecutor<Project>{ 
        
    }
    
