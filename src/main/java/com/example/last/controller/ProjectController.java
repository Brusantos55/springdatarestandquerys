package com.example.last.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.last.entity.Project;
import com.example.last.filter.ProjectFilter;
import com.example.last.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    
    /**
     * controllador para filtrar mediante especificaciones
     */
    @PostMapping("/project/filterSpecications")
    @Operation(summary = "Filter Projects",
			description = "Retrives all rojects that match the spec filter",
			responses = {@ApiResponse(responseCode = "200", 
            description = "Projects retrived successfully")})
    public Page<Project> filterSpAndPaged(@RequestBody ProjectFilter filter){
        return projectService.filterProjectsSp(filter);
    }
}
