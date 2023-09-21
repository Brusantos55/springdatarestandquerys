package com.example.last.repository;

import java.util.List;

import com.example.last.entity.Employee;
import com.example.last.filter.EmployeeFilter;

public interface EmployeeCriteriaRepository {
    List<Employee> filterEmployees(EmployeeFilter filter); 
}
