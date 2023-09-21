package com.example.last.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.example.last.entity.Position;

/**
 * repo con queryByExample
 */
public interface PositionRepository extends
        PagingAndSortingRepository<Position, Long>,
            QueryByExampleExecutor<Position>{ 
        
    }
