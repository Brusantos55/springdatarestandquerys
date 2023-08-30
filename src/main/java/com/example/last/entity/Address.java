package com.example.last.entity;

import com.example.last.entity.enums.StreetTypeEnum;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAddress;

    private StreetTypeEnum streetType;

    private String streetName;

    private int number;

    private String state;
    
    private String city;

    private int zipCode;
}