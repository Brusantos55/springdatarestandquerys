package com.example.last.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.last.entity.Project;
import com.example.last.filter.ProjectFilter;
import com.example.last.repository.ProjectRepository;
import com.example.last.specification.ProjectSpecification;

import lombok.RequiredArgsConstructor;

/**servicio con especificaciones */
@Service
@RequiredArgsConstructor
public class ProjectService {
    
    private final ProjectRepository projectRepo; 

    public Page<Project> filterProjectsSp(ProjectFilter filter){
        return projectRepo.findAll(
                ProjectSpecification.filtrado(filter), 
                PageRequest.of(filter.getPage(), filter.getPageSize())
            );
    }
}
