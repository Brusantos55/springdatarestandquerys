package com.example.last.entity;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.time.ZonedDateTime;

@Entity 
@Data   
@NoArgsConstructor
public class Employee{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id; 
    
    @Column(nullable = false)
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthdate;

    private ZonedDateTime lastLogin; 
    
    private Boolean isAdmin; 

    @NumberFormat
    private Double timeLogged;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn( foreignKey = @ForeignKey(name = "FK_POSITION"), name = "position")
    private Position position;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ADDRESS"), name = "address")
    private Address address;
    
}