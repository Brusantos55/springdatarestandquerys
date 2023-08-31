package com.example.last.repository;

import java.util.List;

import com.example.last.entity.Employee;
import com.example.last.entity.filters.EmployeeFilter;

public interface EmployeeRepoCustom {
    List<Employee> filterEmployees(EmployeeFilter filter); 
}
