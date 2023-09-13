package com.example.last.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.last.entity.EmployeeProjectFK;
import java.util.List;
import com.example.last.entity.Employee;

/**
 * repo excluido de los endpoints autogenerados de spring data rest
 */
@RepositoryRestResource(exported = false)
public interface EmpProjectFKRepo extends
    PagingAndSortingRepository<EmployeeProjectFK, Long>{
    
        List<EmployeeProjectFK> findAllByEmployee(Employee employee);
}