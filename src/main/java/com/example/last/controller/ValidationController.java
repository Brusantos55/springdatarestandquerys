package com.example.last.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.last.entity.Validation;
import com.example.last.repository.ValidationRepository;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class ValidationController {
    
    private final ValidationRepository validationRepo;

    @PostMapping("/validation")
    Validation addUser(@Valid @RequestBody Validation validation) {
        // persisting the user
        return validationRepo.save(validation);
    }
    
}
