package com.example.last.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.last.entity.Project;
import com.example.last.entity.especificaciones.ProjectSpecifications;
import com.example.last.entity.filters.ProjectFilter;
import com.example.last.repository.ProjectRepo;

import lombok.RequiredArgsConstructor;

/**servicio con especificaciones */
@Service
@RequiredArgsConstructor
public class ProjectService {
    
    private final ProjectRepo projectRepo; 

    public Page<Project> filterProjectsSp(ProjectFilter filter){
        return projectRepo.findAll(
                ProjectSpecifications.filtrado(filter), 
                PageRequest.of(filter.getPage(), filter.getPageSize())
            );
    }
}
