package com.example.last.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.last.entity.Position;
import com.example.last.service.PositionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PositionController {
    
    private final PositionService positionService;

    /**
     * controlador de queryByExample
     */
    @PostMapping("/position/queryByExample")
    public Iterable<Position> filterByExample(@RequestBody Position ex){
        return positionService.filterByExample(ex);
    }

}
