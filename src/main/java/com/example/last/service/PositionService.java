package com.example.last.service;

import org.springframework.data.domain.Example;
// import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.last.entity.Position;
import com.example.last.repository.PositionRepo;

import lombok.RequiredArgsConstructor;

/** servicio con queryByExample */
@Service
@RequiredArgsConstructor
public class PositionService {
    
    private final PositionRepo positionRepo;

    public Iterable<Position> filterByExample(Position ex){ 
        // ExampleMatcher matcher = ExampleMatcher.matchingAll().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return positionRepo.findAll(Example.of(ex)); // , matcher
    }
}
