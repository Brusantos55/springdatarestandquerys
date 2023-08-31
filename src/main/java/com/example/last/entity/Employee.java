package com.example.last.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity // mapea a la bd
@Data   // genera metodos Get, Set, ToString, Equals, HashCode y RequiredArgsConstructor
@NoArgsConstructor
public class Employee{

    @Id //primary key en la bd generada en orden \v/
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEmp; // 8 bytes | int 4 bytes

    @Column(nullable = false)
    private String name;

    private LocalDate birthdate;

    private ZonedDateTime lastLogin; // fecha y hora con precision hasta nanosegundo e informacion de la zona horaria

    private Boolean isAdmin; // true, false, null + metodos string <-> boolean

    private double timeLogged; // 8 bytes con dicimales | float 4 bytes

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "employee_project", 
        joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_many_employees"), name = "employee"), 
        inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_many_projects"), name = "project")
    )
    private Set<Project> projects = new HashSet<>();

    @ManyToOne //nullable=false,
    @JoinColumn( foreignKey = @ForeignKey(name = "FK_POSITION"), name = "position")
    private Position position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ADDRESS"), name = "address")
    private Address address;
    
}
// notaciones log spring 