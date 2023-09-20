package com.example.last.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.last.auxiliary.ProjectSpecification;
import com.example.last.entity.Project;
import com.example.last.entity.filters.ProjectFilter;
import com.example.last.repository.ProjectDao;

import lombok.RequiredArgsConstructor;

/**servicio con especificaciones */
@Service
@RequiredArgsConstructor
public class ProjectService {
    
    private final ProjectDao projectRepo; 

    public Page<Project> filterProjectsSp(ProjectFilter filter){
        return projectRepo.findAll(
                ProjectSpecification.filtrado(filter), 
                PageRequest.of(filter.getPage(), filter.getPageSize())
            );
    }
}
