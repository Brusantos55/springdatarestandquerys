package com.example.last.entity;

import javax.persistence.*;

import com.example.last.enumeration.ProjectState;
import com.example.last.enumeration.ProjectType;

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