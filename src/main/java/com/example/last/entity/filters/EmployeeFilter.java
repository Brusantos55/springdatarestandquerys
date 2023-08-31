package com.example.last.entity.filters;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

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

    private ZonedDateTime lastLogin; 

    private Boolean isAdmin; 

    private double timeLogged = -10; 

    private Set<Long> projects;// = new HashSet<>()
    
    private Long position;

    private Long address;

    @Override
    public String toString() {
        return "EmployeeFilter [id=" + id + ", name=" + name + ", birthdate=" + birthdate + ", lastLogin=" + lastLogin
                + ", isAdmin=" + isAdmin + ", timeLogged=" + timeLogged + ", projects=" + projects + ", position="
                + position + ", address=" + address + "]";
    }

}
