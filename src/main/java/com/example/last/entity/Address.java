package com.example.last.entity;

import javax.persistence.*;

import com.example.last.entity.enumeration.StreetTypeEnum;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private StreetTypeEnum streetType;

    private String streetName;

    private int number;

    private String state;
    
    private String city;

    private int zipCode;
}
