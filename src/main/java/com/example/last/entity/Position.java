package com.example.last.entity;

import com.example.last.entity.enums.PositionEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class Position {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPosition;

    private PositionEnum positionType;
    
    private int salary;

    private Boolean benefits; 
}
