package com.example.last.entity;

import com.example.last.entity.enums.ProjectState;
import com.example.last.entity.enums.ProjectType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProject;

    // @ManyToMany(mappedBy = "project") crear la tabla intermedia
    // private Set<Employee> employee = new HashSet<>();

    private ProjectType projectType;

    private ProjectState state;
}