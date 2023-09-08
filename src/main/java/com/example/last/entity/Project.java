package com.example.last.entity;

import com.example.last.entity.enums.ProjectState;
import com.example.last.entity.enums.ProjectType;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ProjectType projectType;

    private ProjectState state;
}