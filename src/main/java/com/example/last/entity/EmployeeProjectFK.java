package com.example.last.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EmployeeProjectFK {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( nullable=false,
        foreignKey = @ForeignKey(name = "FK_EMPLOYEE"))
    private Employee employee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( nullable=false,
        foreignKey = @ForeignKey(name = "FK_PROJECT"))
    private Project project;
}
