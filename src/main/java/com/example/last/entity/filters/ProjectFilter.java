package com.example.last.entity.filters;

import com.example.last.entity.enumeration.ProjectState;
import com.example.last.entity.enumeration.ProjectType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFilter {
    
    private Long id;

    private ProjectType projectType;

    private ProjectState projectState;

    private String sortBy;

    private boolean sortDesc = false; 

    private int page;

    private int pageSize;

}

