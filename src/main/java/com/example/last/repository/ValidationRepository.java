package com.example.last.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.Validation;

public interface ValidationRepository extends
    PagingAndSortingRepository<Validation, Long>{
}
