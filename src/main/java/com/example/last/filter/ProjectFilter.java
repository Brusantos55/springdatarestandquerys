package com.example.last.filter;

import com.example.last.enumeration.ProjectState;
import com.example.last.enumeration.ProjectType;

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

