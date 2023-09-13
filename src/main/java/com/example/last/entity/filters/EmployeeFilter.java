package com.example.last.entity.filters;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFilter {
    
    private Long id; 

    private String name;

    private LocalDate birthdate;

    private LocalDate birthdate2;

    private ZonedDateTime lastLogin; 

    private Boolean isAdmin; 

    private Double timeLogged; 

    private Long project;
    
    private Long position;

    private Long address;

    private String sortBy;

    private boolean sortDesc = false; 

    private int page;

    private int pageSize;

}
