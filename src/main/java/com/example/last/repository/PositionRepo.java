package com.example.last.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.Position;

public interface PositionRepo extends
        PagingAndSortingRepository<Position, Long>,
        QuerydslPredicateExecutor<Position>{ 
        
    }
