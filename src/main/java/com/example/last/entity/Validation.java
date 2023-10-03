package com.example.last.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data   
@NoArgsConstructor
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String noNull;

    @AssertTrue
    private boolean existe;

    // @Size(min = 1, max = 3)
    // private List<Long> size;

    @Min(value = 5, message = "el numero debe estar entre 5 y 10")
    @Max(value = 10, message = "el numero debe estar entre 5 y 10")
    private int rango;

    @Email
    private String email;

    @Past
    private LocalDate pastDate;

    @FutureOrPresent
    private LocalDate futureDate;

    // @NotEmpty 
    // private Collection<String> notEmptyList;

    @NotBlank 
    private String notBlank;

    @PositiveOrZero
    private int positiveOrCero;
    
    @Negative 
    private int negativo;
}
