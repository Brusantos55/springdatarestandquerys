package com.example.last.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.EmployeeProjectFK;
import java.util.List;
import com.example.last.entity.Employee;


public interface EmpProjectFKRepo extends
    PagingAndSortingRepository<EmployeeProjectFK, Long>{
    
        List<EmployeeProjectFK> findAllByEmployee(Employee employee);
}