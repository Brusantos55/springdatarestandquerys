package com.example.last.entity;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.time.ZonedDateTime;

@Entity // mapea a la bd
@Data   // genera metodos Get, Set, ToString, Equals, HashCode y RequiredArgsConstructor
@NoArgsConstructor
public class Employee{

    @Id //primary key en la bd generada en orden \v/
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id; // 8 bytes | int 4 bytes

    @Column(nullable = false)
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthdate;

    private ZonedDateTime lastLogin; // fecha y hora con precision hasta nanosegundo e informacion de la zona horaria

    private Boolean isAdmin; // true, false, null + metodos string <-> boolean

    @NumberFormat
    private Double timeLogged; // 8 bytes con dicimales | float 4 bytes

    @ManyToOne(fetch = FetchType.LAZY) //nullable=false, cascade = CascadeType.ALL
    @JoinColumn( foreignKey = @ForeignKey(name = "FK_POSITION"), name = "position")
    private Position position;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ADDRESS"), name = "address")
    private Address address;
    
}