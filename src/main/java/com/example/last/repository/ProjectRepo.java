package com.example.last.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.Project;

public interface ProjectRepo extends
        PagingAndSortingRepository<Project, Long>{ 
        
    }
    
