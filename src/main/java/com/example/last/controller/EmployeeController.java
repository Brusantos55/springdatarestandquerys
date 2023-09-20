package com.example.last.controller;

import java.util.List;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.last.entity.Employee;
import com.example.last.entity.Project;
import com.example.last.entity.filters.EmployeeFilter;
import com.example.last.exception.RespStaException;
import com.example.last.service.EmployeeService;
import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/customEmployees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    
    /**
     * controlador de querydls
     * url example: ?name=contains(a)&name=or(contains(o))&sort=name,desc&page=1&size=2
     */
    @GetMapping("/api/employees")
    public Iterable<Employee> findAllByQuerydsl(@QuerydslPredicate(root = Employee.class) Predicate predicate) {
        return employeeService.findAllByWebQuerydsl(predicate);
    }
    
    /**
     * 
     * Saves the number of employees specified in the path
     * @param quantity numero de elementos que se van a intentar crear
     * @return devuelve un mensaje segun se desarrolle el proceso
     */
    @PostMapping("{quantity}")
    @Operation(summary = "Saves up to 5 Employees",
			description = "Saves the number of employees specified in the path",
			responses = {@ApiResponse(responseCode = "200", 
            description = "Employees saved successfully")})
    public String getPlayerById(@PathVariable int quantity){
        return employeeService.createRamdomEmployees(quantity);
    }

    /**
     * ejercicio de control de excepciones
     */
    @DeleteMapping()
    @Operation(summary = "error",
			description = "exception controll demo")
    public void error(){
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Error!!", new RespStaException());
    }
    
    /** controlador filtro con criteria */
    @PostMapping("/filterCriteria")
    @Operation(summary = "Filter Employees",
    description = "Retrives all employees that match the criteria filter",
    responses = {@ApiResponse(responseCode = "200", 
    description = "Employees retrived successfully")})
    public List<Employee> filterEmployees(@RequestBody EmployeeFilter filter){
        return employeeService.filterEmployees(filter);
    }
    
    @GetMapping("/projects/{id}")
    @Operation(summary = "get Employee Projects by empId",
            description = "Retrives all projects of the employee id specified",
            responses = {@ApiResponse(responseCode = "200", 
            description = "Projects retrived successfully")})
    public List<Project> getProjectsById(@PathVariable Long id){
        return employeeService.findProjectsByEmpId(id);
    }
    
    
    @GetMapping("/sort/{field}")
    @Operation(summary = "Retrives all Employees sorted by field",
            description = "Retrives all Employees sorted by the field especified on the path",
            responses = {@ApiResponse(responseCode = "200", description = "Employees retrived successfully")})
    public Iterable<Employee> sortEmployeesByField(@PathVariable String field){
        return employeeService.sortEmployeesByField(field);
    }

    @GetMapping("/pagination/{page}/{pageSize}")
    @Operation(summary = "Retrives all Employees paged",
            description = "Retrives all Employees paged as especified on the path",
            responses = {@ApiResponse(responseCode = "200", description = "Employees retrived successfully")})
    public Iterable<Employee> pagedEmployees(@PathVariable int page, @PathVariable int pageSize){
        return employeeService.pagedEmployees(page, pageSize);
    }

    @GetMapping("/pageSort/{page}/{pageSize}/{field}")
    @Operation(summary = "Retrives all Employees paged and sorted",
            description = "Retrives all Employees paged and sorted as especified on the path",
            responses = {@ApiResponse(responseCode = "200", description = "Employees retrived successfully")})
    public Iterable<Employee> pagedAndSortedEmployees(@PathVariable int page, @PathVariable int pageSize, @PathVariable String field){
        return employeeService.pagedAndSortedEmployees(page, pageSize, field);
    }
    
} 
